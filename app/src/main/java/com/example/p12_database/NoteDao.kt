package com.example.p12_database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

//    @Delete
//    fun delete(note: Note)
    @Query("DELETE FROM note_table WHERE id = :id")
    fun delete(id: Int)

    @get:Query("SELECT * from note_table ORDER BY id ASC")
    val allNotes: LiveData<List<Note>>
}