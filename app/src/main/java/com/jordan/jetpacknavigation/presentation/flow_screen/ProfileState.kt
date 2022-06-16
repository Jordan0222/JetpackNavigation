package com.jordan.jetpacknavigation.presentation.flow_screen

import com.jordan.jetpacknavigation.domain.model.Post

data class ProfileState(
    val profilePicUrl: String? = null,
    val username: String? = null,
    val description: String? = null,
    val posts: List<Post> = emptyList()
)