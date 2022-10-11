package com.example.digipics.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.digipics.R
import com.example.digipics.databinding.FragmentDetailBinding
import com.example.digipics.models.Hits


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var image: Hits

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = args.image

        populateUi()
    }

    private fun populateUi() {

        binding.apply {
            Glide.with(requireContext())
                .load(image.previewURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageView)
            user.text = "User: "+image.user
            likes.text ="Likes: "+ image.likes.toString()
            comments.text = "Comments: "+image.comments.toString()
            tags.text = "Tags: "+image.tags
            views.text = "Views: "+image.views.toString()
            web.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(image.pageURL))
                startActivity(browserIntent)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}