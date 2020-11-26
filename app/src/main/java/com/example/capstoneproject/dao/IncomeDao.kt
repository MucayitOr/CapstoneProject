package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.model.Income

@Dao
interface IncomeDao {
    @Query("SELECT * FROM incomeTable")
    fun getAllIncomes(): LiveData<List<Income>>

    @Insert
    suspend fun insertIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("DELETE FROM incomeTable")
    suspend fun deleteAllIncomes()
}