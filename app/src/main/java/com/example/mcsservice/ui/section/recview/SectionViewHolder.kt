package com.example.mcsservice.ui.section.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.R
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.database.DbSection

class SectionViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        section: DbSection,
        clickListener: SectionClickListener
    ) {
        binding.textView.text = section.name
        binding.imageView2.setImageResource(R.drawable.bookmark)
        binding.layout.setOnClickListener { clickListener.onClick(section) }
    }
}