package com.livewallpaper.video.projectautomationtest.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo WHERE status == :status")
    suspend fun getAll(status: String): List<ToDo>

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<ToDo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDo(todoList: List<ToDo>)

    @Query("UPDATE todo SET status=:status WHERE id = :id")
    fun updateTodo(id: Int, status: String)

    @Query("DELETE FROM todo WHERE id = :id")
    fun deleteById(id: Int)
}