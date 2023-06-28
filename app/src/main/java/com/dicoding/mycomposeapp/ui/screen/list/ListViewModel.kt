package com.dicoding.mycomposeapp.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycomposeapp.data.MemberRepository
import com.dicoding.mycomposeapp.model.Member
import com.dicoding.mycomposeapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: MemberRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Member>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Member>>>
        get() = _uiState

    fun getAllMembers() {
        viewModelScope.launch {
            repository.getAllMembers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { members ->
                    _uiState.value = UiState.Success(members)
                }
        }
    }
}