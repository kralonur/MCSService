package com.example.mcsservice.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mcsservice.R
import com.example.mcsservice.databinding.FragmentSectionBinding
import com.example.mcsservice.model.DomainSection
import com.example.mcsservice.ui.section.recview.SectionAdapter
import com.example.mcsservice.ui.section.recview.SectionClickListener

class SectionFragment : Fragment(), SectionClickListener {
    private val viewModel by viewModels<SectionViewModel>()
    private lateinit var binding: FragmentSectionBinding
    private val args by navArgs<SectionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectId = args.subjectId

        val adapter = SectionAdapter(this)
        binding.layoutRecview.recView.adapter = adapter

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getSubject(subjectId).observe(viewLifecycleOwner) {
            binding.layoutRecview.textView2.text = getString(R.string.section_of_subject, it.name)
        }

        viewModel.getSectionList(subjectId).observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onClick(section: DomainSection) {
        findNavController().navigate(
            SectionFragmentDirections.actionSectionFragmentToSectionDetailFragment(section.id)
        )
    }
}