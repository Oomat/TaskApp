package com.example.taskapp.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp.ui.home.TaskModel

@Dao
interface TaskDao {

    @Insert
    fun insert(task: TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)

    @Query("SELECT * FROM TaskModel")
    fun getAllTasks(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title DESC")
    fun getAllTasksByAZ():List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title ASC")
    fun getAllTasksByZA():List<TaskModel>


    @Query("SELECT * FROM TaskModel ORDER BY id DESC")
    fun getAllTasksByDate():List<TaskModel>


}