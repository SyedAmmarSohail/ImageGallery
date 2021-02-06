package com.structure.gallery.ui.main.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.structure.gallery.R
import com.structure.gallery.databinding.ItemSnapBinding
import com.structure.gallery.model.Snap
import com.structure.gallery.ui.main.adapter.SnapListAdapter

class SnapViewHolder(private val binding: ItemSnapBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Snap) {
        binding.imageView.load(post.webformatURL) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

    }
}
