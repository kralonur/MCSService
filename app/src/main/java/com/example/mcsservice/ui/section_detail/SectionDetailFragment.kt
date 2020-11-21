package com.example.mcsservice.ui.section_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mcsservice.databinding.LayoutRecviewWithTitleBinding
import com.example.mcsservice.model.database.DbMaterial
import com.example.mcsservice.model.database.DbTask
import com.example.mcsservice.ui.section_detail.recview.MaterialClickListener
import com.example.mcsservice.ui.section_detail.recview.SectionDetailAdapter
import com.example.mcsservice.ui.section_detail.recview.TaskClickListener
import timber.log.Timber

class SectionDetailFragment : Fragment(), MaterialClickListener, TaskClickListener {
    private val viewModel by viewModels<SectionDetailViewModel>()
    private lateinit var binding: LayoutRecviewWithTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutRecviewWithTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SectionDetailAdapter(this, this)
        binding.recView.adapter = adapter

        viewModel.getSectionDetailList(3).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onClick(material: DbMaterial) {
        Timber.i(material.toString())
    }

    override fun onClick(task: DbTask) {
        Timber.i(task.toString())
    }
}