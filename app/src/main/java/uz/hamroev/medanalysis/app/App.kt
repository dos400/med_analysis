package uz.hamroev.medanalysis.app

import android.app.Application
import uz.hamroev.medanalysis.cache.Cache
import uz.hamroev.medanalysis.database.ResultDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Cache.init(this)
        ResultDatabase.getInstance(this)
    }
}