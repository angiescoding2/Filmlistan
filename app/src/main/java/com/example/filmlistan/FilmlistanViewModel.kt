package com.example.filmlistan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class FilmlistanUiState(
    val nyTitel: String = "",
    val filmer: List<Film> = startfilmer
)

class FilmlistanViewModel : ViewModel() {
    var uiState by mutableStateOf(FilmlistanUiState())
        private set
    private var nextId = 4

    fun onNewTitleChange(text: String) {
        uiState = uiState.copy(nyTitel = text)
    }

    fun addFilm() {
        val titel = uiState.nyTitel.trim()
        if (titel.isBlank()) return

        val nyFilm = Film(nextId, titel, 2026, "Okänd")
        nextId++

        uiState = uiState.copy(
            filmer = uiState.filmer + nyFilm,
            nyTitel = ""
        )
    }
    fun toggleSeen(id: Int) {
        uiState = uiState.copy(
            filmer = uiState.filmer.map { film ->
                if (film.id == id) film.copy(sedd = !film.sedd) else film
            }
        )
    }
    fun removeFilm(id: Int) {
        uiState = uiState.copy(
            filmer = uiState.filmer.filter { it.id != id }
        )
    }
}