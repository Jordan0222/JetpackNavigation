package com.jordan.jetpacknavigation.presentation.flow_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordan.jetpacknavigation.domain.model.Post
import com.jordan.jetpacknavigation.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor(): ViewModel() {

    private val isAuthenticated = MutableStateFlow(true)
    private val user = MutableStateFlow<User?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    val profileState = _profileState.asStateFlow()

    private val flow1 = (1..10).asFlow().onEach { delay(1000L) }
    private val flow2 = (11..20).asFlow().onEach { delay(300L) }

    var numberString by mutableStateOf("")
        private set

    init {
        isAuthenticated.combine(user) { isAuthenticated, user ->
            if (isAuthenticated) user else null
        }.combine(posts) { user, posts ->
            user?.let {
                _profileState.value = profileState.value?.copy(
                    profilePicUrl = user.profilePicUrl,
                    username = user.userName,
                    description = user.description,
                    posts = posts
                )
            }
        }

        // zip: flow2 會等 flow1 再一起 emit
        flow1.zip(flow2) { number1, number2 ->
            numberString += "($number1, $number2)\n"
        }.launchIn(viewModelScope)

        // merge: flow2 不會等 flow1，會直接 emit
        /*merge(flow1, flow2).onEach {
            numberString += "$it\n"
        }.launchIn(viewModelScope)*/
    }
}