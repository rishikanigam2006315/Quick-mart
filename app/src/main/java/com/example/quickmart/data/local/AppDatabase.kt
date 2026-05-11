package com.example.quickmart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quickmart.data.local.entity.CartItemEntity
import com.example.quickmart.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class, CartItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
