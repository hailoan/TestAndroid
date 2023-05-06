package com.livewallpaper.video.projectautomationtest.ui.todo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.databinding.DialogCreateNewTodoBinding
import java.util.Calendar

class DialogCreateNew(
    context: Context,
    private val onConfirm: (ToDo) -> Unit,
    private val onCancel: () -> Unit,
) : Dialog(context) {

    private var timeStart = Calendar.getInstance().timeInMillis
    private var timeEnd = Calendar.getInstance().timeInMillis + 24 * 60 * 60 * 1000
    private lateinit var binding: DialogCreateNewTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogCreateNewTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvCreateNewToDoDlgCancel.setOnClickListener {
            dismiss()
            onCancel()
        }

        binding.tvCreateNewToDoDlgCreate.setOnClickListener {
            dismiss()
            onConfirm(createNewToDo())
        }
    }

    private fun createNewToDo(): ToDo {
        return ToDo(title = binding.tvCreateNewToDoDlgTitle.text.toString(),
            desc = binding.edtCreateNewToDoDlgDesc.text.toString(),
            timeStart = timeStart,
            timeEnd = timeEnd)
    }
}