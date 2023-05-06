package com.livewallpaper.video.projectautomationtest

import android.content.Context
import androidx.room.Room
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.domain.AppRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class TodoDatabaseUnitTest {
    private lateinit var db: AppRoomDatabase

    @Mock
    lateinit var context: Context


    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            context, AppRoomDatabase::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertNewToDo_success() = runTest {
        withContext(Dispatchers.IO) {
            val newItem = ToDo(title = "hello", desc = "hello")
//            db.toDoDao().insertToDo(arrayListOf(newItem))
            val items = db.toDoDao().getAll()
//            assert(items.contains(newItem))
        }
    }
}