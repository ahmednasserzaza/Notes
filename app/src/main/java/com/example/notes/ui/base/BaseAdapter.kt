package com.example.notes.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.BR

interface BaseInterActionListener

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private val listener: BaseInterActionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {


    abstract val layoutId:Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context) , layoutId,  parent , false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.apply {
                    setVariable(BR.item , currentItem)
                    setVariable(BR.listener , listener)
                }
            }
        }
    }

    fun setItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(SimpleDiffUtil(items , newItems){oldItem, newItem ->
            areItemsSame(oldItem , newItem)
        })
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    open fun areItemsSame(oldItem: T, newItem: T):Boolean {
        return oldItem?.equals(newItem) == true
    }

    fun getItems() = items

    override fun getItemCount() = items.size

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}