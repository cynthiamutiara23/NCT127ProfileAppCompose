package com.dicoding.mycomposeapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycomposeapp.data.MemberRepository
import com.dicoding.mycomposeapp.model.Member
import com.dicoding.mycomposeapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MemberRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Member>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Member>>
        get() = _uiState

    fun getMemberById(memberId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMemberById(memberId))
        }
    }
}