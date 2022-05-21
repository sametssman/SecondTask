package com.sametsisman.secondtask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @ColumnInfo(name = "theme")
    val theme : String?,

    @ColumnInfo(name = "title")
    val title : String?,

    @ColumnInfo(name = "numberOfChoice")
    val numberOfChoice : Int?,

    @ColumnInfo(name = "A_Choice")
    val A_Choice : String?,

    @ColumnInfo(name = "B_Choice")
    val B_Choice : String?,

    @ColumnInfo(name = "C_Choice")
    val C_Choice : String?,

    @ColumnInfo(name = "correctAnswer")
    val correctAnswer : String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}