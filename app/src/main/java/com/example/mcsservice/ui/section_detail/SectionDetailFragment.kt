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
import com.example.mcsservice.databinding.DialogSectionPassBinding
import com.example.mcsservice.databinding.FragmentSectionDetailBinding
import com.example.mcsservice.model.database.DbMaterial
import com.example.mcsservice.model.database.DbSection
import com.example.mcsservice.model.database.DbTask
import com.example.mcsservice.ui.section_detail.recview.MaterialClickListener
import com.example.mcsservice.ui.section_detail.recview.SectionDetailAdapter
import com.example.mcsservice.ui.section_detail.recview.TaskClickListener
import com.example.mcsservice.ui.section_detail.validator.HtmlValidator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

class SectionDetailFragment : Fragment(), MaterialClickListener, TaskClickListener {
    private val viewModel by viewModels<SectionDetailViewModel>()
    private lateinit var binding: FragmentSectionDetailBinding
    private val args by navArgs<SectionDetailFragmentArgs>()
    private lateinit var section: DbSection


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionId = args.sectionId

        val adapter = SectionDetailAdapter(this, this)
        binding.layoutRecview.recView.adapter = adapter

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.topAppBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.unlock) {
                showSectionPasswordDialog()
                true
            } else false
        }

        viewModel.getSection(sectionId).observe(viewLifecycleOwner) {
            section = it
            binding.layoutRecview.textView2.text = getString(R.string.section, it.name)
            if (it.sectionPass.isNotEmpty()) viewModel.decryptSection(it, HtmlValidator())
        }

        viewModel.getSectionDetailList(sectionId).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isUnlockRequired(sectionId).observe(viewLifecycleOwner) {
            binding.topAppBar.menu.findItem(R.id.unlock).isVisible = it
        }
    }

    override fun onClick(material: DbMaterial) {
        findNavController().navigate(
            SectionDetailFragmentDirections.actionSectionDetailFragmentToWebViewFragment(material.content)
        )
    }

    private fun showSectionPasswordDialog() {
        val dialogBinding = DialogSectionPassBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .setPositiveButton("OK") { _, _ ->
                val pass = dialogBinding.passInput.text.toString()
                Timber.i("Entered pass: $pass")
                viewModel.updateSectionPass(section, pass)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onClick(task: DbTask) {
        findNavController().navigate(
            SectionDetailFragmentDirections.actionSectionDetailFragmentToWebViewFragment(task.description)
        )
    }
}