package com.example.capstoneproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
@Entity(tableName = "incomeTable")
data class Income(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "discription")
    var discription: String,

    @ColumnInfo(name = "amount")
    var amount: Double,

    @ColumnInfo(name = "date")
    var date: LocalDate,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)