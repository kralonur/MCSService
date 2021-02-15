package com.example.mcsservice.ui.section.recview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.DomainSection

class SectionAdapter(private val clickListener: SectionClickListener) :
    ListAdapter<DomainSection, SectionViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<DomainSection>() {
        override fun areItemsTheSame(oldItem: DomainSection, newItem: DomainSection): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DomainSection, newItem: DomainSection): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}