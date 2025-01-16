package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note (
    @ColumnInfo(name = "lastName") var lastname:String,
    @ColumnInfo(name = "number") var number:String,
    @ColumnInfo(name = "timestamp") var timesTamp:String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}