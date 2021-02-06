package com.structure.gallery.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.structure.gallery.R
import com.structure.gallery.databinding.ActivityMainBinding
import com.structure.gallery.model.Category
import com.structure.gallery.ui.base.BaseActivity
import com.structure.gallery.ui.main.adapter.CategoryListAdapter
import com.structure.gallery.ui.main.adapter.SnapListAdapter
import com.structure.gallery.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    CategoryListAdapter.OnItemClickListener {

    private val mAdapter: SnapListAdapter by lazy { SnapListAdapter() }
    private val mCategoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter(
            onItemClickListener = this
        )
    }

    private val categoryList = listOf<Category>(
        Category("backgrounds"),
        Category("fashion"),
        Category("nature"),
        Category("science"),
        Category("education"),
        Category("feelings"),
        Category("health"),
        Category("people"),
        Category("religion"),
        Category("animals"),
        Category("industry"),
        Category("computer"),
        Category("food")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewBinding.postsRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addItemDecoration(GridItemDecoration(15, 2))
            adapter = mAdapter
        }

        mViewBinding.categoryRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mCategoryAdapter
        }

        mViewBinding.searchView.setOnClickListener {
            mViewBinding.searchView.isIconified = false
        }

        mViewBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                mViewModel.getPosts(search = search)
                return true
            }

            override fun onQueryTextChange(search: String?): Boolean {
                return true
            }

        })


        mCategoryAdapter.submitList(categoryList)
        initPosts()

        handleNetworkChanges()
    }

    private fun initPosts() {
        mViewModel.postsLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                    } else {
                        showToast(getString(R.string.data_not_found))
                    }
                    showLoading(false)
                }
                is State.Error -> {
                    showToast(state.message)
                    showLoading(false)
                }
            }
        })

        mViewBinding.swipeRefreshLayout.setOnRefreshListener {
            getPosts()
        }

        if (mViewModel.postsLiveData.value !is State.Success) {
            getPosts()
        }
    }

    private fun getPosts() {
        mViewModel.getPosts()
    }

    private fun showLoading(isLoading: Boolean) {
        mViewBinding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, Observer { isConnected ->
            if (!isConnected) {
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                }
            } else {
                if (mViewModel.postsLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getPosts()
                }
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        })
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogCustom)
            .setTitle(resources.getString(R.string.exit))
            .setMessage(resources.getString(R.string.exit_message))
            .setNegativeButton(resources.getString(android.R.string.yes)) { dialog, which ->
                finish()
            }
            .setPositiveButton(resources.getString(android.R.string.no)) { dialog, which ->
            }
            .show()

    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel() = viewModelOf<MainViewModel>(mViewModelProvider)

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

    override fun onItemClicked(category: Category) {
        mViewModel.getPosts(type = category.type)
        mCategoryAdapter.setSelected(category)
    }
}
