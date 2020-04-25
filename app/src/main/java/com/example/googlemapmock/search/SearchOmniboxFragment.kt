package com.example.googlemapmock.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.googlemapmock.R

class SearchOmniboxFragment : Fragment(R.layout.search_omnibox_fragment) {

    private lateinit var viewModel: SearchOmniboxViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchOmniboxViewModel::class.java)
    }
}
