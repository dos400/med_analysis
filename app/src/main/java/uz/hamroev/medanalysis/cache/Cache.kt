package uz.hamroev.medanalysis.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "medanalysys"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var language: String?
        get() = sharedPreferences.getString("language", "ru")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("language", value)
            }
        }

    var name: String?
        get() = sharedPreferences.getString("filtername", "")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("filtername", value)
            }
        }

    var countAll: Int?
        get() = sharedPreferences.getInt("countall", 0)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putInt("countall", value)
            }
        }




   }

