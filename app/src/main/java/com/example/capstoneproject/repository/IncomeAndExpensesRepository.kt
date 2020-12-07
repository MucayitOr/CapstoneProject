package com.example.capstoneproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstoneproject.dao.ExpensesDao
import com.example.capstoneproject.dao.IncomeDao
import com.example.capstoneproject.database.IncomeAndExpensesRoomDatabase
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.model.Income

class IncomeAndExpensesRepository(context: Context) {
    private var incomeDao: IncomeDao
    private var expensesDao: ExpensesDao

    init {
        val incomeRoomDatabase = IncomeAndExpensesRoomDatabase.getDatabase(context)
        incomeDao = incomeRoomDatabase!!.incomeDao()
        val expensesRoomDatabase = IncomeAndExpensesRoomDatabase.getDatabase(context)
        expensesDao = incomeRoomDatabase!!.expensesDao()
    }

    fun getAllExpenses(): LiveData<List<Expense>>{
        return expensesDao.getAllExpenses()
    }

    fun getAllIncomes(): LiveData<List<Income>> {
        return incomeDao.getAllIncomes()
    }

    suspend fun insertExpense(expense: Expense) {
        expensesDao.insertExpense(expense)
    }

    suspend fun insertIncome(income: Income) {
        incomeDao.insertIncome(income)
    }

    suspend fun deleteExpense(expense: Expense) {
        expensesDao.deleteExpense(expense)
    }

    suspend fun deleteIncome(income: Income) {
        incomeDao.deleteIncome(income)
    }

    suspend fun  deleteAllExpenses(){
        expensesDao.deleteAllExpenses()
    }

    suspend fun  deleteAllIncomes(){
        incomeDao.deleteAllIncomes()
    }
}