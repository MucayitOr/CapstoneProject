package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstoneproject.model.Income
import com.example.capstoneproject.repository.IncomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val incomeRepository = IncomeRepository(application.applicationContext)

    val incomes: LiveData<List<Income>> = incomeRepository.getAllIncomes()

    fun insertIncome(income: Income) {
        ioScope.launch {
            incomeRepository.insertIncome(income)
        }
    }

    fun deleteIncome(income: Income) {
        ioScope.launch {
            incomeRepository.deleteIncome(income)
        }
    }

    fun deleteAllIncomes(){
        ioScope.launch {
            incomeRepository.deleteAllIncomes()
        }
    }
}