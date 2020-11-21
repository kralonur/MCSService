package com.example.mcsservice.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mcsservice.R
import com.example.mcsservice.databinding.LayoutRecviewWithTitleBinding
import com.example.mcsservice.model.database.DbSection
import com.example.mcsservice.ui.section.recview.SectionAdapter
import com.example.mcsservice.ui.section.recview.SectionClickListener
import timber.log.Timber

class SectionFragment : Fragment(), SectionClickListener {
    private val viewModel by viewModels<SectionViewModel>()
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

        binding.textView2.text = getString(R.string.all_subjects)

        val adapter = SectionAdapter(this)
        binding.recView.adapter = adapter

        viewModel.getSectionList().observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onClick(section: DbSection) {
        Timber.i(section.toString())
    }
}