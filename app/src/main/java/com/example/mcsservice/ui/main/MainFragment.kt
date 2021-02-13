package com.example.mcsservice.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mcsservice.R
import com.example.mcsservice.databinding.FragmentMainBinding
import com.example.mcsservice.model.ResultWrapper
import com.example.mcsservice.model.database.DbSubject
import com.example.mcsservice.ui.main.recview.SubjectAdapter
import com.example.mcsservice.ui.main.recview.SubjectClickListener

class MainFragment : Fragment(), SubjectClickListener {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutRecview.textView2.text = getString(R.string.all_subjects)

        val adapter = SubjectAdapter(this)
        binding.layoutRecview.recView.adapter = adapter

        viewModel.getSubjectList().observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) initDb()
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.swipeRefresh.setOnRefreshListener { updateDb() }

    }

    override fun onClick(subject: DbSubject) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToSectionFragment(subject.id)
        )
    }

    private fun initDb() {
        viewModel.initDatabase().observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it == ResultWrapper.Loading
            if (it !is ResultWrapper.Success && it != ResultWrapper.Loading) viewModel.clearDatabase()
            when (it) {
                ResultWrapper.Loading -> requireContext().showShortText(getString(R.string.initialising_database))
                is ResultWrapper.Success -> requireContext().showShortText(getString(R.string.initialising_database_success))
                ResultWrapper.NetworkError -> requireContext().showShortText(getString(R.string.check_your_internet_connection))
                else -> requireContext().showShortText(getString(R.string.initialising_database_error))
            }
        }
    }

    private fun updateDb() {
        viewModel.updateDatabase().observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it == ResultWrapper.Loading
            when (it) {
                ResultWrapper.Loading -> requireContext().showShortText(getString(R.string.updating_database))
                is ResultWrapper.Success -> requireContext().showShortText(getString(R.string.updating_database_success))
                ResultWrapper.NetworkError -> requireContext().showShortText(getString(R.string.check_your_internet_connection))
                else -> requireContext().showShortText(getString(R.string.updating_database_error))
            }
        }
    }

    private fun Context.showShortText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}