package com.example.mcsservice.ui.section_detail.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.mcsservice.R
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.database.DbMaterial

class MaterialViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        material: DbMaterial,
        clickListener: MaterialClickListener
    ) {
        binding.textView.text = material.name
        binding.imageView2.setImageResource(R.drawable.book)
        binding.layout.setOnClickListener { clickListener.onClick(material) }
    }
}