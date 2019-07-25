package com.jay.storymirror.ui.storyread

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.storymirror.model.Story
import com.jay.storymirror.model.StoryChapters
import com.jay.storymirror.data.Data

class StoryReadViewModel(application: Application) : AndroidViewModel(application) {

    private val _story = MutableLiveData<Story>()

    val title: LiveData<String> get() = _title
    private val _title = MutableLiveData<String>()

    val content: LiveData<String> get() = _content
    private val _content = MutableLiveData<String>()

    private val _chapter = MutableLiveData<StoryChapters>()

    private val _currentChapterId = MutableLiveData<Long>()

    val prevButtonVisibility: LiveData<Boolean> get() = _prevButtonVisibility
    private val _prevButtonVisibility = MutableLiveData<Boolean>()

    val nextButtonVisibility: LiveData<Boolean> get() = _nextButtonVisibility
    private val _nextButtonVisibility = MutableLiveData<Boolean>()

    val finishActivity: LiveData<Boolean> get() = _finishActivity
    private val _finishActivity = MutableLiveData<Boolean>()

    fun getStory(storyId: Long) {
        var storyIdFound = false
        for (s in Data.stories) {
            if (s.id == storyId) {
                storyIdFound = true
                _story.value = s
                _currentChapterId.value = s.chapterIds.first()
                updateData()
                break
            }
        }
        if (!storyIdFound) _finishActivity.value = true
    }

    fun handlePreviousButtonClicked() {
        for (i in _story.value!!.chapterIds.indices) {
            if (_currentChapterId.value == _story.value!!.chapterIds[i]) {
                _currentChapterId.value = _story.value!!.chapterIds[i - 1]
                updateData()
                break
            }
        }
    }

    fun handleNextButtonClicked() {
        for (i in _story.value!!.chapterIds.indices) {
            if (_currentChapterId.value == _story.value!!.chapterIds[i]) {
                _currentChapterId.value = _story.value!!.chapterIds[i + 1]
                updateData()
                break
            }
        }
    }

    private fun updateData() {
        for (c in Data.storyChapters) {
            if (c.id == _currentChapterId.value) {
                _chapter.value = c
            }
        }
        _title.value = _chapter.value?.title
        _content.value = _chapter.value?.htmlText
        _prevButtonVisibility.value = _currentChapterId.value != _story.value?.chapterIds?.first()
        _nextButtonVisibility.value = _currentChapterId.value != _story.value!!.chapterIds.last()
    }
}