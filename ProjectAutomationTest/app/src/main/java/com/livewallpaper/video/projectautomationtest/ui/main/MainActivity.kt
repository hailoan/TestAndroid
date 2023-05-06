package com.livewallpaper.video.projectautomationtest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.livewallpaper.video.projectautomationtest.ApplicationApp
import com.livewallpaper.video.projectautomationtest.databinding.ActivityMainBinding
import com.livewallpaper.video.projectautomationtest.domain.AppRoomDatabase
import com.livewallpaper.video.projectautomationtest.ui.todo.ToDoListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOpenToDo.setOnClickListener {
            startActivity(Intent(this, ToDoListActivity::class.java))
        }

        ApplicationApp.roomDatabase = Room.databaseBuilder(applicationContext,
            AppRoomDatabase::class.java,
            "video-lw-database").fallbackToDestructiveMigration().build()
    }
}
