package com.livewallpaper.video.projectautomationtest.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.livewallpaper.video.projectautomationtest.data.model.ToDo

@Database(entities = [ToDo::class], version = 4, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}