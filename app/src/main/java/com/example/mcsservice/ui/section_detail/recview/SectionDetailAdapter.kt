package com.example.mcsservice.ui.section_detail.recview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.databinding.ItemHeaderBinding
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.SectionDetailItem
import com.example.mcsservice.model.SectionDetailItemType

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_MATERIAL = 1
private const val ITEM_VIEW_TYPE_TASK = 2

class SectionDetailAdapter(
    private val materialClickListener: MaterialClickListener,
    private val taskClickListener: TaskClickListener
) : ListAdapter<SectionDetailItem, RecyclerView.ViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<SectionDetailItem>() {
        override fun areItemsTheSame(
            oldItem: SectionDetailItem,
            newItem: SectionDetailItem
        ): Boolean {
            if (oldItem.type != newItem.type)
                return false

            if (oldItem.type == SectionDetailItemType.MATERIAL && oldItem.type == SectionDetailItemType.MATERIAL)
                return oldItem.material?.id == newItem.material?.id

            if (oldItem.type == SectionDetailItemType.TASK && oldItem.type == SectionDetailItemType.TASK)
                return oldItem.task?.id == newItem.task?.id

            if (oldItem.type == SectionDetailItemType.HEADER && oldItem.type == SectionDetailItemType.HEADER)
                return oldItem.header == newItem.header

            return false
        }

        override fun areContentsTheSame(
            oldItem: SectionDetailItem,
            newItem: SectionDetailItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }
            ITEM_VIEW_TYPE_MATERIAL -> {
                val binding = ItemListBinding.inflate(inflater, parent, false)
                MaterialViewHolder(binding)
            }
            ITEM_VIEW_TYPE_TASK -> {
                val binding = ItemListBinding.inflate(inflater, parent, false)
                TaskViewHolder(binding)
            }
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(
                getItem(position).header!!
            )
            is MaterialViewHolder -> holder.bind(
                getItem(position).material!!,
                materialClickListener
            )
            is TaskViewHolder -> holder.bind(
                getItem(position).task!!,
                taskClickListener
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            SectionDetailItemType.HEADER -> ITEM_VIEW_TYPE_HEADER
            SectionDetailItemType.MATERIAL -> ITEM_VIEW_TYPE_MATERIAL
            SectionDetailItemType.TASK -> ITEM_VIEW_TYPE_TASK
        }
    }
}