package com.livewallpaper.video.projectautomationtest.ui.todo

import androidx.lifecycle.ViewModel
import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.data.repo.ToDoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ToDoVM : ViewModel() {
    private var repo = ToDoRepo()

    var todoList: ArrayList<ToDo> = ArrayList()

    var currentStatus: StatusToDo? = null

    fun getToDoList(): Flow<List<ToDo>> {
        return flow {
            todoList.clear()
            val data = repo.getToDoList(currentStatus?.name)
            todoList.addAll(data ?: arrayListOf())
            emit(todoList)
        }
    }

    fun insertToDo(toDo: ToDo): Flow<Boolean> {
        return flow {
            val result = repo.insertToDo(arrayListOf(toDo))
            emit(result)
        }
    }

    fun updateStatusToDo(id: Int, newStatus: StatusToDo): Flow<ToDo> {
        return flow {
            repo.updateStatusToDo(id, newStatus.name)
            val item = todoList.firstOrNull { it.id == id }
            item?.status = newStatus.name
            if (item != null) {
                emit(item)
            }
        }
    }

    fun removeToDo(id: Int): Flow<Boolean> {
        return flow {
            val result = repo.removeToDo(id)
            emit(result)
        }
    }

    fun updateFilter(newStatus: StatusToDo?): Flow<List<ToDo>> {
        this.currentStatus = newStatus
        return getToDoList()
    }
}