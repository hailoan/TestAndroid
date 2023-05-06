package com.livewallpaper.video.projectautomationtest.ui.todo

import android.view.ViewGroup
import com.livewallpaper.video.projectautomationtest.R
import com.livewallpaper.video.projectautomationtest.base.BaseAdapter
import com.livewallpaper.video.projectautomationtest.base.BaseViewHolder
import com.livewallpaper.video.projectautomationtest.base.inflaterView
import com.livewallpaper.video.projectautomationtest.data.model.StatusToDo
import com.livewallpaper.video.projectautomationtest.data.model.ToDo
import com.livewallpaper.video.projectautomationtest.databinding.ItemTodoBinding
import com.livewallpaper.video.projectautomationtest.extensions.getStatusStr

class ToDoAdapter : BaseAdapter<ToDo>() {

    companion object {
        const val PAYLOAD_UPDATE_STATUS = "PAYLOAD_UPDATE_STATUS"
    }

    var onClickItem: ((ToDo) -> Unit)? = null

    override fun getView(viewType: Int, parent: ViewGroup): BaseViewHolder<ToDo> {
        return ToDoVH(ItemTodoBinding.bind(parent.inflaterView(R.layout.item_todo)), onClickItem = {
            onClickItem?.invoke(data[it])
        })
    }
}

class ToDoVH(private val binding: ItemTodoBinding, onClickItem: ((Int) -> Unit)? = null) :
    BaseViewHolder<ToDo>(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClickItem?.invoke(layoutPosition)
        }
    }

    override fun bindData(data: ToDo) {
        binding.tvToDoItmTitle.text = data.title ?: ""
        binding.tvToDoItmDesc.text = data.desc ?: "<NoData>"
        binding.tvToDoItmStatus.text = data.getStatusStr()
        binding.tvToDoItmTime.text =
            "Time: ${data.timeStart.formatTime()} to ${data.timeEnd.formatTime()}"
    }

    override fun bindData(data: ToDo, payloads: List<Any>) {
        for (payload in payloads) {
            if (payload == ToDoAdapter.PAYLOAD_UPDATE_STATUS) {
                binding.tvToDoItmStatus.text = data.getStatusStr()
            }
        }
    }


}