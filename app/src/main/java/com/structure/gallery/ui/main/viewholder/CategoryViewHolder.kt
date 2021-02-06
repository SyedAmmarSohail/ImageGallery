package com.structure.gallery.ui.main.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.structure.gallery.R
import com.structure.gallery.databinding.ItemCategoryBinding
import com.structure.gallery.model.Category
import com.structure.gallery.ui.main.adapter.CategoryListAdapter

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        category: Category,
        onItemClickListener: CategoryListAdapter.OnItemClickListener? = null
    ) {
        binding.txtCategory.text = category.type

        if (category.isSelected) {
            binding.txtCategory.setBackgroundResource(R.drawable.border_selected)
            binding.txtCategory.setTextColor(Color.WHITE)
        } else {
            binding.txtCategory.setBackgroundResource(R.drawable.border)
            binding.txtCategory.setTextColor(Color.BLACK)
        }

        onItemClickListener?.let { listener ->
            binding.root.setOnClickListener {
                category.isSelected = true
                listener.onItemClicked(category)
            }
        }
    }
}
