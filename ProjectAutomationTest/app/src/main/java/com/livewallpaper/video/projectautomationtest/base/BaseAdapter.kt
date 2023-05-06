package com.livewallpaper.video.projectautomationtest.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T : Any> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var data = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getView(viewType, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(data[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bindData(data[position], payloads)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    abstract fun getView(viewType: Int, parent: ViewGroup): BaseViewHolder<T>
}

abstract class BaseViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(data: T)

    abstract fun bindData(data: T, payloads: List<Any>)
}


fun ViewGroup.inflaterView(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)