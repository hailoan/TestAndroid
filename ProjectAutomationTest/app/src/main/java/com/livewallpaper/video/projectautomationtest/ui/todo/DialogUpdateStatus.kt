package com.livewallpaper.video.projectautomationtest.ui.todo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.databinding.DialogChangeStatusTodoBinding
import com.livewallpaper.video.projectautomationtest.databinding.DialogCreateNewTodoBinding
import java.util.Calendar

class DialogUpdateStatus(
    context: Context,
    private val onConfirm: (Int, StatusToDo) -> Unit,
    private val onCancel: () -> Unit,
    private val todo: ToDo,
) : Dialog(context) {

    private lateinit var binding: DialogChangeStatusTodoBinding
    private var newStatus: StatusToDo = StatusToDo.TODO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogChangeStatusTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvStatusToDoDlgCancel.setOnClickListener {
            dismiss()
            onCancel()
        }
        binding.tvStatusToDoDlgTodo.setOnClickListener {
            newStatus = StatusToDo.TODO
        }

        binding.tvStatusToDoDlgProgress.setOnClickListener {
            newStatus = StatusToDo.PROGRESS
        }

        binding.tvStatusToDoDlgDone.setOnClickListener {
            newStatus = StatusToDo.DONE
        }

        binding.tvStatusToDoDlgUpdate.setOnClickListener {
            dismiss()
            onConfirm.invoke(todo.id, newStatus)
        }
    }
}