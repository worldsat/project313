package com.example.project313.ui.detail

import com.example.project313.data.model.Estate

data class EstateDetailUiState(
    val estate: Estate? = null,
    val isSaved: Boolean = false,
    val isLoading: Boolean = false,
    val feedbackMessage: String? = null
)
