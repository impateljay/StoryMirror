package com.jay.storymirror.model

data class Story(
    val id: Long,
    val chapterIds: List<Long>
)