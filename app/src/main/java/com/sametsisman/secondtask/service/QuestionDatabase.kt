package com.sametsisman.secondtask.service

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sametsisman.secondtask.model.Question

@Database(entities = [(Question::class)], version = 1)
abstract class QuestionDatabase : RoomDatabase(){
    abstract fun questionDao() : QuestionDao
}