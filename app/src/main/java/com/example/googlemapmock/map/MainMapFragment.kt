package com.example.googlemapmock.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.googlemapmock.R
import com.example.googlemapmock.map.behavior.behavior
import com.example.googlemapmock.map.ktx.GestureDetectorCompat
import com.example.googlemapmock.map.ktx.LifecycleAwareGestureDetector
import kotlinx.android.synthetic.main.mainmap_fragment.view.*

class MainMapFragment : Fragment(R.layout.mainmap_fragment) {

    private val singleTapDetector: LifecycleAwareGestureDetector by lazy {
        GestureDetectorCompat(requireContext().applicationContext)
    }
    private lateinit var viewModel: MainMapViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainMapViewModel::class.java)
        view.setOnTouchListener { _, event -> singleTapDetector.onTouchEvent(event) }
        singleTapDetector.onSingleTapUp.observe(viewLifecycleOwner) {
            view.search_omnibox_container.behavior?.onSingleTap()
            view.nav_view.behavior?.onSingleTap()
        }
    }
}
