package com.example.capstoneproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstoneproject.converters.Converters
import com.example.capstoneproject.dao.IncomeDao
import com.example.capstoneproject.model.Income

@Database(entities = [Income::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class IncomeRoomDatabase : RoomDatabase() {

    abstract fun incomeDao(): IncomeDao

    companion object {
        private const val DATABASE_NAME = "INCOME_DATABASE"

        @Volatile
        private var INSTANCE: IncomeRoomDatabase? = null

        fun getDatabase(context: Context): IncomeRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(IncomeRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            IncomeRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }

}