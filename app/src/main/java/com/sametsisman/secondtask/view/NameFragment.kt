package com.sametsisman.secondtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.sametsisman.secondtask.R
import kotlinx.android.synthetic.main.fragment_name.*

class NameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        anaMenuButton.setOnClickListener {
            if (nameEditText.text.toString() != ""){
                val name = nameEditText.text.toString()
                val sharedPreferences = this.activity?.getSharedPreferences("questionShared", AppCompatActivity.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("name",name)
                editor?.apply()

                val action = NameFragmentDirections.actionNameFragmentToThemeFragment()
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(context,"Please enter name",Toast.LENGTH_SHORT).show()
            }
        }
    }
}