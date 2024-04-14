package farrukh.mydictionary.screens.home

import android.content.Context
import uz.ictschool.mywords.localDatabse.AppDataBase
import uz.ictschool.mywords.localDatabse.entity.Word

class HomeModel(context: Context) {
    private val db = AppDataBase.getInstance(context)
    private val wordDao = db.getWordDao()

    fun getAllWords():MutableList<Word>{
        return wordDao.getAllWords()
    }
}