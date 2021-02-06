package com.structure.gallery.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.structure.gallery.databinding.ItemSnapBinding
import com.structure.gallery.model.Snap
import com.structure.gallery.ui.main.viewholder.SnapViewHolder


class SnapListAdapter() :
    ListAdapter<Snap, SnapViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SnapViewHolder(
        ItemSnapBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SnapViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Snap>() {
            override fun areItemsTheSame(oldItem: Snap, newItem: Snap): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Snap, newItem: Snap): Boolean =
                oldItem == newItem

        }
    }
}
