package com.example.mcsservice.ui.section.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.R
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.DomainSection

class SectionViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        section: DomainSection,
        clickListener: SectionClickListener
    ) {
        binding.textView.text = section.name
        val resource = if (section.unlockRequired) R.drawable.report else R.drawable.bookmark
        binding.imageView2.setImageResource(resource)
        binding.layout.setOnClickListener { clickListener.onClick(section) }
    }
}