package uz.ictschool.mywords.localDatabse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val translation: String,
    val defination:String
    )