package com.example.mcsservice.ui.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mcsservice.databinding.LayoutWebviewBinding

class WebViewFragment : Fragment() {
    private lateinit var binding: LayoutWebviewBinding
    private val args by navArgs<WebViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = args.content

        binding.web.loadData(content, "text/html", "UTF-8")

        binding.web.settings.builtInZoomControls = true
    }
}