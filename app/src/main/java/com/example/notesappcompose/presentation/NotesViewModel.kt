package com.example.notesappcompose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappcompose.data.Note
import com.example.notesappcompose.data.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class NotesViewModel(
    private val dao: NoteDao
) : ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)
//     val isSortedByDateAdded = StateFlow(true)
//         get() =_isSortedByDateAdded

    private var notes =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getNotesOrderdByDateAdded()
            } else {
                dao.getNotesOrderdByTitle()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(NoteState())
    val state =
        combine(_state, isSortedByDateAdded, notes) { state, isSortedByDateAdded, notes ->
            state.copy(
                notes = notes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NotesEvent) {
        when (event)
        {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }
            is NotesEvent.SaveNote -> {
                val note = Note(
                    title = state.value.title.value,
                    description = state.value.description.value,
                    dateAdded = System.currentTimeMillis()
//                    dateAdded = Date.from(Instant.now())
                )
                viewModelScope.launch {
                    dao.insertNote(note)
//                    dao.updateNote(note)
                }
                _state.update {
                    it.copy(
//                        id = it.id,
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }
//                viewModelScope.launch {
//                    dao.updateNote(note)
//                }
            }

            is NotesEvent.UpdateNote-> {
                viewModelScope.launch {
                    dao.updateNote(event.note)

                }
            }

            NotesEvent.SortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }

        }
    }
//     var showDialog by  mutableStateOf(false)
//    fun onDismissDialog(){   // try using viewModelScope
//        showDialog = false
//    }
//    fun onShowDialog(){
//        showDialog = true
//    }




}