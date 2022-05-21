package com.sametsisman.secondtask.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.sametsisman.secondtask.R
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener {
            val action = ThemeFragmentDirections.actionThemeFragmentToNameFragment()
            Navigation.findNavController(view).navigate(action)
        }

        spaceButton.setOnClickListener {
            val sharedPreferences = this.activity?.getSharedPreferences("questionShared", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putString("theme","space")
            editor?.apply()
            val intent = Intent(activity?.applicationContext, QuizActivity::class.java)
            startActivity(intent)
        }

        foodButton.setOnClickListener {
            val sharedPreferences = this.activity?.getSharedPreferences("questionShared", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putString("theme","food")
            editor?.apply()
            val intent = Intent(activity?.applicationContext, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}