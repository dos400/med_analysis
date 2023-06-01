package uz.hamroev.medanalysis.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ResultEntity::class], version = 1)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        private var instance: ResultDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ResultDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    "results.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }


}