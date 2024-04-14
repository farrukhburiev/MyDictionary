package uz.ictschool.mywords.localDatabse.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.ictschool.mywords.localDatabse.entity.Word


@Dao
interface WordDao {

    @Insert
    fun addWord(word: Word)

    @Update
    fun updateWord(word: Word)

    @Delete
    fun deleteWord(word: Word)

    @Query("SELECT * FROM word")
    fun getAllWords():MutableList<Word>

    @Query("SELECT * FROM WORD WHERE id = :id")
    fun getWordById(id: Int):Word

}