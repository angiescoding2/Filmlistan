package com.example.filmlistan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class FilmlistanUiState(
    val filmer: List<Film> = startfilmer
)

class FilmlistanViewModel : ViewModel() {

    var uiState by mutableStateOf(FilmlistanUiState())
        private set

}