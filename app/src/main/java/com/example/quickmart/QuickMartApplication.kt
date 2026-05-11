package com.example.quickmart

import android.app.Application
import androidx.room.Room
import com.example.quickmart.data.local.AppDatabase
import com.example.quickmart.repository.QuickMartRepository

class QuickMartApplication : Application() {
    lateinit var database: AppDatabase
        private set
    
    lateinit var repository: QuickMartRepository
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "quickmart_database"
        ).build()
        repository = QuickMartRepository(database.appDao())
    }
}
