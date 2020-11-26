package com.example.capstoneproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstoneproject.dao.IncomeDao
import com.example.capstoneproject.database.IncomeRoomDatabase
import com.example.capstoneproject.model.Income

class IncomeRepository(context: Context) {
    private var incomeDao: IncomeDao

    init {
        val incomeRoomDatabase = IncomeRoomDatabase.getDatabase(context)
        incomeDao = incomeRoomDatabase!!.incomeDao()
    }

    fun getAllIncomes(): LiveData<List<Income>> {
        return incomeDao.getAllIncomes()
    }

    suspend fun insertIncome(income: Income) {
        incomeDao.insertIncome(income)
    }

    suspend fun deleteIncome(income: Income) {
        incomeDao.deleteIncome(income)
    }

    suspend fun  deleteAllIncomes(){
        incomeDao.deleteAllIncomes()
    }
}