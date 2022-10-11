package com.example.digipics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digipics.R
import com.example.digipics.adapter.ImageAdapter
import com.example.digipics.databinding.FragmentHomeFeedBinding
import com.example.digipics.models.Hits
import com.example.digipics.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFeedFragment : Fragment(R.layout.fragment_home_feed) {

    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageViewModel by viewModels()

    private lateinit var imageAdapter: ImageAdapter
    private var imagesList = arrayListOf<Hits>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFeedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        viewModel.getAllImages("")
        viewModel.responseImage.observe(viewLifecycleOwner) { listImages ->
            imagesList.addAll(listImages.hits)
            imageAdapter.notifyDataSetChanged()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.responseImage.observe(viewLifecycleOwner) { listImages ->
                    imagesList.clear()
                    imagesList.addAll(listImages.hits)
                    imageAdapter.notifyDataSetChanged()
                }
                viewModel.getAllImages(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter(requireContext(), imagesList)

        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}