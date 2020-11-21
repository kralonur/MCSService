package com.example.mcsservice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mcsservice.R
import com.example.mcsservice.databinding.LayoutRecviewWithTitleBinding
import com.example.mcsservice.model.database.DbSubject
import com.example.mcsservice.ui.main.recview.SubjectAdapter
import com.example.mcsservice.ui.main.recview.SubjectClickListener

class MainFragment : Fragment(), SubjectClickListener {
    private val viewModel by viewModels<MainViewModel>()
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

        val adapter = SubjectAdapter(this)
        binding.recView.adapter = adapter

        viewModel.getSubjectList().observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onClick(subject: DbSubject) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToSectionFragment(subject.id)
        )
    }
}