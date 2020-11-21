package com.example.mcsservice.ui.section_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mcsservice.R
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
    private val args by navArgs<SectionDetailFragmentArgs>()

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

        val sectionId = args.sectionId

        val adapter = SectionDetailAdapter(this, this)
        binding.recView.adapter = adapter

        viewModel.getSection(sectionId).observe(viewLifecycleOwner) {
            binding.textView2.text = getString(R.string.section, it.name)
        }

        viewModel.getSectionDetailList(sectionId).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onClick(material: DbMaterial) {
        findNavController().navigate(
            SectionDetailFragmentDirections.actionSectionDetailFragmentToWebViewFragment(material.content)
        )
    }

    override fun onClick(task: DbTask) {
        Timber.i(task.toString())
    }
}