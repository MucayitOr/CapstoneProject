package com.example.capstoneproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstoneproject.converters.Converters
import com.example.capstoneproject.dao.ExpensesDao
import com.example.capstoneproject.dao.IncomeDao
import com.example.capstoneproject.model.Income

@Database(entities = [Income::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class IncomeAndExpensesRoomDatabase : RoomDatabase() {

    abstract fun incomeDao(): IncomeDao
    abstract fun expensesDao(): ExpensesDao

    companion object {
        private const val DATABASE_NAME = "DATABASE"

        @Volatile
        private var INSTANCE: IncomeAndExpensesRoomDatabase? = null

        fun getDatabase(context: Context): IncomeAndExpensesRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(IncomeAndExpensesRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            IncomeAndExpensesRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }

}