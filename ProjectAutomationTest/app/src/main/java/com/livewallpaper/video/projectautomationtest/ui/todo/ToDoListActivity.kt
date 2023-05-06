package com.livewallpaper.video.projectautomationtest.ui.todo

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.livewallpaper.video.projectautomationtest.R
import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.databinding.ActivityToDoListBinding
import com.livewallpaper.video.projectautomationtest.extensions.getReportTasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Calendar


class ToDoListActivity : AppCompatActivity() {
    private var adapter: ToDoAdapter = ToDoAdapter()
    private lateinit var binding: ActivityToDoListBinding
    private var vm: ToDoVM = ToDoVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()
        initView()
    }

    private fun initEvents() {
        binding.ivToDoListAddNew.setOnClickListener {
            showCreateNew()
        }

        binding.ivToDoListFilter.setOnClickListener {
            showMenuFilter(it)
        }
    }

    private fun showCreateNew() {
        val dialog = DialogCreateNew(this, onCancel = {

        }, onConfirm = {
            vm.insertToDo(it).flowOn(Dispatchers.IO).onEach { result ->
                if (result) {
                    vm.todoList.add(0, it)
                    adapter.data.add(0, it)
                    adapter.notifyItemInserted(0)
                }
            }.launchIn(vm.viewModelScope)
        })
        dialog.show()
    }

    private fun initView() {
        binding.rcToDoList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcToDoList.adapter = adapter
        vm.getToDoList().flowOn(Dispatchers.IO).onEach {
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
            updateReport(it)
        }.launchIn(scope = vm.viewModelScope)
        adapter.onClickItem = {
            showChangeStatusDialog(it)
        }
    }

    private fun showChangeStatusDialog(toDo: ToDo) {
        val dialog = DialogUpdateStatus(this, onCancel = {

        }, onConfirm = { id, newStatus ->
            vm.updateStatusToDo(id, newStatus).flowOn(Dispatchers.IO).onEach { result ->
                val indexOf = adapter.data.indexOfFirst { it.id == result.id }
                if (indexOf > -1) {
                    adapter.notifyItemChanged(indexOf, ToDoAdapter.PAYLOAD_UPDATE_STATUS)
                }
            }.launchIn(vm.viewModelScope)
        }, todo = toDo)
        dialog.show()
    }

    private fun showMenuFilter(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.filter_task, popup.menu)
        popup.setOnMenuItemClickListener {
            val newStatus = when (it.itemId) {
                R.id.active -> {
                    StatusToDo.TODO
                }

                R.id.inprogress -> {
                    StatusToDo.PROGRESS
                }

                R.id.completed -> {
                    StatusToDo.DONE
                }

                else -> {
                    null
                }
            }
            updateFilter(newStatus)
            true
        }

        popup.show()

    }

    private fun updateFilter(statusToDo: StatusToDo?) {
        vm.updateFilter(newStatus = statusToDo).flowOn(Dispatchers.IO).onEach {
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
            updateReport(it)
        }.launchIn(scope = vm.viewModelScope)
    }

    private fun updateReport(data: List<ToDo>) {
        val report = data.getReportTasks()
        binding.tvToDoListReport.text =
            "ToDo = ${report.todoPercent}%\nProgress = ${report.progressPercent}%\nDone = ${report.donePercent}%"
    }
}

fun Long.formatTime(): String {
    val f: Format = SimpleDateFormat("MM/dd/yy")
    val time = Calendar.getInstance()
    time.timeInMillis = this
    return f.format(time.time)
}