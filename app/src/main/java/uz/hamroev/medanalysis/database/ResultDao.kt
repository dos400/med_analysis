package uz.hamroev.medanalysis.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {

    @Query("select * from resultentity")
    fun getAllResults(): List<ResultEntity>

    @Insert
    fun addResult(resultEntity: ResultEntity)
}