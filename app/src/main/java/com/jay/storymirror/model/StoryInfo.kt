package com.jay.storymirror.model

data class StoryInfo(
    val title: String,
    val author: String,
    val reads: Long,
    val votes: Long,
    val description: String,
    val tags: List<String>,
    val thumbnail: String,
    val storyId: Long
)