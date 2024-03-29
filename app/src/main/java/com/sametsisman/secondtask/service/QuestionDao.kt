package com.sametsisman.secondtask.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sametsisman.secondtask.model.Question

@Dao
interface QuestionDao {

    @Insert
    suspend fun insert(question: Question)

    @Query("SELECT * FROM question WHERE theme = :space")
    suspend fun getSpaceQuestions(space : String) : List<Question>

    @Query("SELECT * FROM question WHERE theme = :food")
    suspend fun getFoodQuestions(food : String) : List<Question>

    @Query("DELETE FROM question")
    suspend fun deleteAll()
}