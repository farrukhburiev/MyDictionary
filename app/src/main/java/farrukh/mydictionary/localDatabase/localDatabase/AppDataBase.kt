package uz.ictschool.mywords.localDatabse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.ictschool.mywords.localDatabse.dao.WordDao
import uz.ictschool.mywords.localDatabse.entity.Word


@Database(entities = [Word::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getWordDao(): WordDao


    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java, "app_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}