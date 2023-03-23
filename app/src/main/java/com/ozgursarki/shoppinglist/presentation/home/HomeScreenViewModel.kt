package com.ozgursarki.shoppinglist.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenUIState> =
        MutableStateFlow(HomeScreenUIState())

    val uiState: StateFlow<HomeScreenUIState>
        get() = _uiState
}