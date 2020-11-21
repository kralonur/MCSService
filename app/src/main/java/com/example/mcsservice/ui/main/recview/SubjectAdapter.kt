package com.example.mcsservice.ui.main.recview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mcsservice.databinding.ItemListBinding
import com.example.mcsservice.model.database.DbSubject

class SubjectAdapter(private val clickListener: SubjectClickListener) :
    ListAdapter<DbSubject, SubjectViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<DbSubject>() {
        override fun areItemsTheSame(oldItem: DbSubject, newItem: DbSubject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DbSubject, newItem: DbSubject): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}