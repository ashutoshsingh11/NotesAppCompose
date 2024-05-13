package com.example.notesappcompose.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.notesappcompose.data.Note
import java.time.LocalTime


@Composable
fun EditNoteScreen(
    state: NoteState,
    navHostController: NavHostController,
    onEvent: (NotesEvent) -> Unit
) {
    val note =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Note>("data") ?: Note(
            "",
            "",
//            0L
            state.time.value!! ,
        )
    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.description) }
//    val time = currentTimeMillis()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
//                onEvent(NotesEvent.SaveNote(
////                    id = state.id.value,
//                    title = state.title.value,
//                    description = state.description.value
//                ))

//                onEvent(NotesEvent.UpdateNote(
//                   note.copy(
//                       title = title,
//                       description = description
//                   )
//                ))
                Log.d("TAG", "EditNoteScreen: $note ")
                navHostController.popBackStack()
            }) {

                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = "Save Note"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = title,
                onValueChange = {
                    title = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Title")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = description,
                onValueChange = {
                    description = it
                },
                placeholder = {
                    Text(text = "Description")
                }
            )
        }
    }
}