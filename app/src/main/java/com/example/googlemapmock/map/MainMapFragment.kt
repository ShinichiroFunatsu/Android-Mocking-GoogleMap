package com.example.googlemapmock.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.googlemapmock.R
import com.example.googlemapmock.map.behavior.behavior
import com.example.googlemapmock.map.ktx.GestureDetectorCompat
import kotlinx.android.synthetic.main.mainmap_fragment.view.*

class MainMapFragment : Fragment(R.layout.mainmap_fragment) {

    private val singleTapDetector by lazy {
        return@lazy { view: View ->
            GestureDetectorCompat(requireContext().applicationContext).apply {
                view.setOnTouchListener { _, event -> onTouchEvent(event) }
            }
        }
    }
    private lateinit var viewModel: MainMapViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainMapViewModel::class.java)
        setupStatusBar(view)
        singleTapDetector(view).onSingleTapUp.observe(viewLifecycleOwner) {
            view.search_omnibox_container.behavior?.onSingleTap()
            view.footer_container.behavior?.onSingleTap()
        }
    }

    private fun setupStatusBar(view: View) {
        view.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.statusBar)
    }
}
