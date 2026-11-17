package com.example.filmlistan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// UiState: allt som skärmen behöver visa, samlat på ett ställe.
//nyTitel: texten i textfältet, filmer = hela filmlistan.
//Startvärdena gör att appen börjar med tomt fält och de tre startfilmerna.


data class FilmlistanUiState(
    val nyTitel: String = "",
    val filmer: List<Film> = startfilmer
)

//ViewModel äger appens data och är den enda som får ändra den.
//Den överlever rotation, därför finns listan kvar när telefonen vänds.
class FilmlistanViewModel : ViewModel() {
    //Det skärmen läser ifrån. mutableStateOf gör att Compose ritar om
    //skärmen när uiState byts ut. private set betyder att bara ViewModel får ändra.
    var uiState by mutableStateOf(FilmlistanUiState())
        private set
    private var nextId = 4

    fun onNewTitleChange(text: String) {
        uiState = uiState.copy(nyTitel = text)
    }

    //Körs när man trycker "Lägg till"
    // tar också bort mellanslag, avbryter om titeln är tom, skapar en ny film
    //med nästa id, lägger den sist i listan och tömmer textfältet.
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

    //Körs när man trycker "Ta bort" på ett kort
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