package com.example.mcsservice.ui.main.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.R
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.database.DbSubject

class SubjectViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        subject: DbSubject,
        clickListener: SubjectClickListener
    ) {
        binding.textView.text = subject.name
        binding.imageView2.setImageResource(R.drawable.school)
        binding.layout.setOnClickListener { clickListener.onClick(subject) }
    }
}