package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.model.Income

@Dao
interface ExpensesDao {
    @Query("SELECT * FROM expensesTable ORDER BY date")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Insert
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("DELETE FROM expensesTable")
    suspend fun deleteAllExpenses()
}