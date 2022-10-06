package com.example.a203110026_fanianggita_room


import androidx.lifecycle.LiveData
import androidx.room.*

// TODO 2: Membuat kelas DAO untuk menentukan kueri SQL

// @Dao untuk menganotasikan bahwa ini adalah kelas DAO untuk room
@Dao
interface CatatanDao {

    // Method untuk menangani insert data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note :Catatan)

    // Method untuk menangani delete data
    @Delete
    fun delete(note: Catatan)

    // Method untuk mengembalikan kueri pengambalian semua data dari tabel "notesTable" dengan urutan id naik (ASC).
    @Query("Select * from notesTable order by id ASC")
    fun getSemuaCatatan(): LiveData<List<Catatan>>

    // Method untuk menangani update data
    @Update
    fun update(note: Catatan)

}
