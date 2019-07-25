package com.jay.storymirror.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.storymirror.model.StoryInfo
import com.jay.storymirror.data.Data

class StoryFragmentViewModel : ViewModel() {

    private lateinit var paidStories: MutableLiveData<List<StoryInfo>>

    fun getPaidStories():LiveData<List<StoryInfo>>{
        if (!::paidStories.isInitialized){
            paidStories = MutableLiveData()
            paidStories.value = Data.paidStories
        }
        return paidStories
    }

    fun getRecommendationsStories():LiveData<List<StoryInfo>>{
        if (!::paidStories.isInitialized){
            paidStories = MutableLiveData()
            paidStories.value = Data.recommendationsStories
        }
        return paidStories
    }

    fun getGreatFirstReadStories():LiveData<List<StoryInfo>>{
        if (!::paidStories.isInitialized){
            paidStories = MutableLiveData()
            paidStories.value = Data.greatFirstReadStories
        }
        return paidStories
    }
}
