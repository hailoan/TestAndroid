package com.livewallpaper.video.projectautomationtest.data.repo

import com.livewallpaper.video.projectautomationtest.ApplicationApp
import com.livewallpaper.video.projectautomationtest.data.model.ToDo

class ToDoRepo {

    suspend fun getToDoList(status: String? = null): List<ToDo>? {
        return if (status.isNullOrEmpty()) ApplicationApp.roomDatabase?.toDoDao()
            ?.getAll() else ApplicationApp.roomDatabase?.toDoDao()?.getAll(status)
    }

    suspend fun insertToDo(todos: List<ToDo>): Boolean {
        ApplicationApp.roomDatabase?.toDoDao()?.insertToDo(todos)
        return true
    }

    suspend fun updateStatusToDo(id: Int, status: String): Boolean {
        ApplicationApp.roomDatabase?.toDoDao()?.updateTodo(id, status)
        return true
    }

    suspend fun removeToDo(id: Int): Boolean {
        ApplicationApp.roomDatabase?.toDoDao()?.deleteById(id)
        return true
    }
}