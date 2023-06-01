package uz.hamroev.medanalysis.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ResultEntity {

    @PrimaryKey(autoGenerate = true)  var id: Long? = null

    var date: String? = null
    var fio: String? = null
    var diagnos: String? = null
    var countBall: String? = null

    var diagnos2: String? = null
    var diagnos3: String? = null


}