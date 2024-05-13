package com.example.notesappcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY dateAdded DESC")
    fun getNotesOrderdByDateAdded(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getNotesOrderdByTitle(): Flow<List<Note>>

}


/*@Query("SELECT * FROM note WHERE id=id")
//suspend fun getNoteById(id: Int): Note
//

 */