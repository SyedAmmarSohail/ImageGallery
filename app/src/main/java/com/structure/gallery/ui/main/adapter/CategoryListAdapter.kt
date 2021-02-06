package com.structure.gallery.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.structure.gallery.databinding.ItemCategoryBinding
import com.structure.gallery.model.Category
import com.structure.gallery.ui.main.viewholder.CategoryViewHolder

class CategoryListAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Category, CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClickListener)

    interface OnItemClickListener {
        fun onItemClicked(category: Category)
    }

    fun setSelected(category: Category){
        currentList.forEach { it.isSelected = false }
        category.isSelected = true
        notifyDataSetChanged()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.type == newItem.type

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem == newItem

        }
    }
}
