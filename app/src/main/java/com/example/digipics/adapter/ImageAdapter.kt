package com.example.digipics.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.digipics.R
import com.example.digipics.fragments.HomeFeedFragmentDirections
import com.example.digipics.models.Hits


class ImageAdapter(private val context: Context, private val images: ArrayList<Hits>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val requestBuilder: RequestBuilder<Drawable> = Glide.with(context).asDrawable().sizeMultiplier(0.1f)


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val name: TextView = itemView.findViewById(R.id.name)
        val likes: TextView = itemView.findViewById(R.id.likes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.image_item_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ImageAdapter.ViewHolder, position: Int) {

        val image: Hits = images[position]

        val name = viewHolder.name
        name.text = image.user
        val likes = viewHolder.likes
        likes.text = "Likes: "+image.likes.toString()
        val imageView = viewHolder.image

        Glide.with(context)
            .load(image.previewURL)
            .thumbnail(requestBuilder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)

        viewHolder.itemView.setOnClickListener { mView ->
            val direction = HomeFeedFragmentDirections.actionHomeFeedFragmentToDetailFragment(image)
            mView.findNavController().navigate(direction)
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }
}