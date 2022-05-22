package com.sametsisman.secondtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sametsisman.secondtask.R
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val sharedPreferences = getSharedPreferences("questionShared", MODE_PRIVATE)
        val name = sharedPreferences.getString("name","")
        val questionListSize = sharedPreferences.getInt("questionListSize",0)
        val correctAnswers =  sharedPreferences.getInt("trueAnswer",0)

        usernameText.text = name
        resultText.text = "$correctAnswers / $questionListSize"

        tryAgainButton.setOnClickListener {
            val intent = Intent(this,QuizActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        anaMenuButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

}