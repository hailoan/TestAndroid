package com.livewallpaper.video.projectautomationtest

import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.extensions.getReportTasks
import com.livewallpaper.video.projectautomationtest.extensions.getStatusStr
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun getReportTasks_noCompleted_returnsHundredZero() {
//        val tasks = listOf(ToDo())
//        val result = tasks.getReportTasks()
//        assertEquals(result.todoPercent, 100)
//        assertEquals(result.progressPercent, 0)
//        assertEquals(result.donePercent, 0)
    }

    @Test
    fun getReportTasks_empty_returnZeros() {
        val tasks = listOf<ToDo>()
        val result = tasks.getReportTasks()
        assertEquals(result.todoPercent, 0)
        assertEquals(result.progressPercent, 0)
        assertEquals(result.donePercent, 0)
    }

    @Test
    fun getStatusStr_done_returnDone() {
        val item = ToDo(status = StatusToDo.DONE.name)
        val str = item.getStatusStr()
        assertEquals(str, "#done")
    }

    @Test
    fun getStatusStr_progress_returnProgress() {
        val item = ToDo(status = StatusToDo.PROGRESS.name)
        val str = item.getStatusStr()
        assertEquals(str, "#inprogress")
    }

    @Test
    fun getStatusStr_done_returnTodo() {
        val item = ToDo(status = StatusToDo.TODO.name)
        val str = item.getStatusStr()
        assertEquals(str, "#todo")
    }

    @Test
    fun getStatusStr_none_returnTodo() {
        val item = ToDo(status = "")
        val str = item.getStatusStr()
        assertEquals(str, "#todo")
    }
}