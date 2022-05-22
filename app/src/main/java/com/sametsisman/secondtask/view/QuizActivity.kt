package com.sametsisman.secondtask.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.sametsisman.secondtask.R
import com.sametsisman.secondtask.model.Question
import com.sametsisman.secondtask.service.QuestionDao
import com.sametsisman.secondtask.service.QuestionDatabase
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList

class QuizActivity : AppCompatActivity(){
    private lateinit var db : QuestionDatabase
    private lateinit var questiondao : QuestionDao
    private lateinit var questionSpacelist : List<Question>
    private lateinit var questionFoodlist : List<Question>
    private lateinit var questionList : List<Question>
    private lateinit var editor : SharedPreferences.Editor
    private var trueAnswer : Int = 0
    private var i : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        db = Room.databaseBuilder(applicationContext,QuestionDatabase::class.java,"Questions").build()
        questiondao = db.questionDao()
        val sharedPreferences = getSharedPreferences("questionShared", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val isLoad = sharedPreferences.getInt("isLoad",0)

        if (isLoad.equals(0)){
            loadQuestion()
            editor.putInt("isLoad",1)
            editor.apply()
            startActivity(intent)
        }

        val theme = sharedPreferences.getString("theme","")

        if (theme.equals("space")){
            lifecycleScope.launch {
                questionSpacelist = (questiondao.getSpaceQuestions("space"))
                getQuestions(questionSpacelist)
            }

        }else if(theme.equals("food")){
            lifecycleScope.launch {
                questionFoodlist = questiondao.getSpaceQuestions("food")
                getQuestions(questionFoodlist)
            }
        }


    }

    private fun getQuestions(questionlist : List<Question>) {
        A_Button.background = resources.getDrawable(R.drawable.rounded_corner)
        B_Button.background = resources.getDrawable(R.drawable.rounded_corner)
        C_Button.background = resources.getDrawable(R.drawable.rounded_corner)
        openClick()
        questionList = questionlist
        val question = questionlist[i]
        if(question.numberOfChoice!!.equals(2)){
            C_Button.visibility = View.GONE
        }else if (question.numberOfChoice!!.equals(3)){
            C_Button.visibility = View.VISIBLE
        }
        questionText.text = question.title
        A_Button.text = question.A_Choice
        B_Button.text = question.B_Choice
        C_Button.text = question.C_Choice

        A_Button.setOnClickListener {
            closeClick()
            if(question.correctAnswer.equals("A")){
                lifecycleScope.launch {
                    A_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    trueAnswer++
                    finishOrResume()
                }


            }else if (question.correctAnswer.equals("B")){
                lifecycleScope.launch {
                    A_Button.background = resources.getDrawable(R.drawable.rounded_corner_red)
                    B_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    finishOrResume()
                }
            }else if (question.correctAnswer.equals("C")){
                lifecycleScope.launch {
                    A_Button.background = resources.getDrawable(R.drawable.rounded_corner_red)
                    C_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    finishOrResume()
                }
            }
        }
        B_Button.setOnClickListener {
            closeClick()
            if(question.correctAnswer.equals("B")){
                lifecycleScope.launch {
                    B_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    trueAnswer++
                    finishOrResume()
                }
            }else if (question.correctAnswer.equals("A")){
                lifecycleScope.launch {
                    B_Button.background = resources.getDrawable(R.drawable.rounded_corner_red)
                    A_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    finishOrResume()
                }
            }else if (question.correctAnswer.equals("C")){
                lifecycleScope.launch {
                    B_Button.background = resources.getDrawable(R.drawable.rounded_corner_red)
                    C_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    finishOrResume()
                }
            }
        }
        C_Button.setOnClickListener {
            closeClick()
            if(question.correctAnswer.equals("C")){
                lifecycleScope.launch {
                    C_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    trueAnswer++
                    finishOrResume()
                }
            }else if (question.correctAnswer.equals("B")){
                lifecycleScope.launch {
                    C_Button.background = resources.getDrawable(R.drawable.rounded_corner_red)
                    B_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    finishOrResume()
                }
            }else if (question.correctAnswer.equals("A")){
                lifecycleScope.launch {
                    C_Button.background = resources.getDrawable(R.drawable.rounded_corner_red)
                    A_Button.background = resources.getDrawable(R.drawable.rounded_corner_green)
                    delay(2000)
                    i++
                    finishOrResume()
                }
            }
        }
    }


    private fun loadQuestion() {
        val question1 = Question("space","About how many stars are in the Milky Way?",3,"50-100 billion","150-250 billion","350-550 billion","B")
        val question2 = Question("space","Which planet is closest to Earth",3,"Mercury","Venus","Mars","A")
        val question3 = Question("space","What is the largest planet in our solar system?",3,"Saturn","Venus","Jupiter","C")
        val question4 = Question("space","Which planet has a day that lasts almost eight months on Earth?",3,"Venus","Mercury","Pluton","A")
        val question5 = Question("space","What was the first animal to go into orbit?",3,"A dog","A cat","A frog","A")
        val question6 = Question("space","How many Earths can fit inside the sun?",3,"0.7 million","1.0 million","1.3 million","C")
        val question7 = Question("food","What food serves as the base for guacamole?",3,"Zucchini","Cucumber","Avocado","C")
        val question8 = Question("food","What food is the most ordered in America?",3,"Fried chicken","Hamburger","Pizza","A")
        val question9 = Question("food","What is the world record for number of hotdogs eaten in one sitting?",3,"56","74","82","B")
        val question10 = Question("food","What contains more sugar, strawberries or lemons?",2,"Lemons","Strawberries","","A")
        val question11 = Question("food","Can you name the largest chocolate manufacturer in the United States?",2,"Hershey’s.","Willy Wonka","","A")


        setQuestion(question1)
        setQuestion(question2)
        setQuestion(question3)
        setQuestion(question4)
        setQuestion(question5)
        setQuestion(question6)
        setQuestion(question7)
        setQuestion(question8)
        setQuestion(question9)
        setQuestion(question10)
        setQuestion(question11)

    }

    private fun setQuestion(question: Question){
        lifecycleScope.launch {
            db.questionDao().insert(question)
        }
    }

    private fun finishOrResume(){
        if(i<questionList.size){
            getQuestions(questionList)
        }else if(i>=questionList.size){
            editor.putInt("questionListSize",questionList.size)
            editor.putInt("trueAnswer",trueAnswer)
            editor.apply()
            val intent = Intent(this@QuizActivity,FinishActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    private fun closeClick(){
        A_Button.isClickable = false
        B_Button.isClickable = false
        C_Button.isClickable = false
    }

    private fun openClick(){
        A_Button.isClickable = true
        B_Button.isClickable = true
        C_Button.isClickable = true
    }
}