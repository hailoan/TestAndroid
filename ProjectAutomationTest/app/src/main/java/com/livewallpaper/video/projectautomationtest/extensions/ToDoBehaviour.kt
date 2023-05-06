package com.livewallpaper.video.projectautomationtest.extensions

import com.livewallpaper.video.projectautomationtest.data.model.Report
import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import kotlin.math.roundToInt


fun List<ToDo>.getReportTasks(): Report {
    val sum = this.size
//    if (sum == 0) return Report()
    val countTodo = this.count { it.status == StatusToDo.TODO.name }
    val countProgress = this.count { it.status == StatusToDo.PROGRESS.name }
    val countDone = this.count { it.status == StatusToDo.DONE.name }

    return Report(
        todoPercent = ((countTodo / sum.toFloat()) * 100f).toInt(),
        progressPercent = ((countProgress / sum.toFloat()) * 100f).roundToInt(),
        donePercent = ((countDone / sum.toFloat()) * 100f).toInt()
    )
}

fun ToDo.getStatusStr(): String {
    return when (status) {
        StatusToDo.DONE.name -> {
            "#done"
        }

        StatusToDo.PROGRESS.name -> {
            "#inprogress"
        }

        else -> {
            "#todo"
        }
    }
}