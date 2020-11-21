package com.example.mcsservice.ui.section_detail.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.R
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.database.DbTask

class TaskViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        task: DbTask,
        clickListener: TaskClickListener
    ) {
        binding.textView.text = task.name
        binding.imageView2.setImageResource(R.drawable.label)
        binding.layout.setOnClickListener { clickListener.onClick(task) }
    }
}