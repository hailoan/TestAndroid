package com.livewallpaper.video.projectautomationtest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class ToDo(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "desc") var desc: String? = null,
    @ColumnInfo(name = "start") var timeStart: Long = 0,
    @ColumnInfo(name = "end") var timeEnd: Long = 0,
    @ColumnInfo(name = "status") var status: String = StatusToDo.TODO.name,
)

enum class StatusToDo {
    TODO, PROGRESS, DONE
}