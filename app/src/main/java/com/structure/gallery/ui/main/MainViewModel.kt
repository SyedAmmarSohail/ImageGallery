package com.structure.gallery.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.structure.gallery.data.repository.SnapsRepository
import com.structure.gallery.model.Snap
import com.structure.gallery.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(private val postsRepository: SnapsRepository) :
    ViewModel() {

    private val _postsLiveData = MutableLiveData<State<List<Snap>>>()

    val postsLiveData: LiveData<State<List<Snap>>>
        get() = _postsLiveData

    fun getPosts(search : String? = "yellow", type : String?  = "nature") {
        viewModelScope.launch {
            postsRepository.getAllPosts(search, type).collect {
                _postsLiveData.value = it
            }
        }
    }

}
