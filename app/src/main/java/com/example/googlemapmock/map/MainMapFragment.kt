package com.example.googlemapmock.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.googlemapmock.R
import kotlinx.android.synthetic.main.mainmap_fragment.view.*

class MainMapFragment : Fragment(R.layout.mainmap_fragment) {

    private lateinit var viewModel: MainMapViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainMapViewModel::class.java)
    }
}
