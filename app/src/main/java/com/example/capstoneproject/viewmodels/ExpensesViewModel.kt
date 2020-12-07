package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.model.Income
import com.example.capstoneproject.repository.IncomeAndExpensesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val Repository = IncomeAndExpensesRepository(application.applicationContext)

    val expenses: LiveData<List<Expense>> = Repository.getAllExpenses()

    fun insertExpense(expense: Expense) {
        ioScope.launch {
            Repository.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        ioScope.launch {
            Repository.deleteExpense(expense)
        }
    }

    fun deleteAllExpenses(){
        ioScope.launch {
            Repository.deleteAllExpenses()
        }
    }
}