package com.example.appmovies.features.home.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovies.R
import com.example.appmovies.features.home.presentation.adapter.ListMovieAdapter
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
import com.example.appmovies.features.home.presentation.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        
        val editSearch = view.findViewById<EditText>(R.id.editSearch)
        val recyclerViewSearchResults = view.findViewById<RecyclerView>(R.id.recyclerViewSearchResults)
        val scrollViewCategories = view.findViewById<View>(R.id.scrollViewCategories)
        
        recyclerViewSearchResults.layoutManager = LinearLayoutManager(requireContext())
        
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString()?.trim() ?: ""
                
                if (query.isEmpty()) {
                    recyclerViewSearchResults.visibility = View.GONE
                    scrollViewCategories.visibility = View.VISIBLE
                } else {
                    recyclerViewSearchResults.visibility = View.VISIBLE
                    scrollViewCategories.visibility = View.GONE
                }
                
                viewModel.searchMovies(query)
            }
        })
        
        lifecycleScope.launch {
            viewModel.searchResults.collect { results ->
                recyclerViewSearchResults.adapter = ListMovieAdapter(results) { movie ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }
            }
        }
        
        return view
    }
}
