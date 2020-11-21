package com.example.mcsservice.ui.section_detail.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.databinding.ItemHeaderBinding

class HeaderViewHolder(private val binding: ItemHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        text: String
    ) {
        binding.textView.text = text
    }
}