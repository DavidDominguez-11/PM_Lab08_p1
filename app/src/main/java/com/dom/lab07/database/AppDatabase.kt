package com.dom.lab07.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dom.lab07.database.categories.MealCategoryDao
import com.dom.lab07.database.categories.MealCategoryEntity

@Database(entities = [MealCategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
}