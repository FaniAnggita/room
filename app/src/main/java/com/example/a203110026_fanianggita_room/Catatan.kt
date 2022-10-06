package com.example.a203110026_fanianggita_room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// TODO 1: Membuat kelas entitas untuk membuat struktur database

// Menentukan nama tabel
@Entity(tableName = "notesTable")

// Menentukan daftar kolom
class Catatan (@ColumnInfo(name = "title") val judulCatatan :String,
               @ColumnInfo(name = "description") val deskripsiCatatan :String,
               @ColumnInfo(name = "timestamp") val timeStamp :String) {

    // Menentukan ID data, "autoGenerate = true" artinya ID akan terbentuk seiring bertambahnya data.
    // var id = 0 untuk menentukan nilai awal id adalah 0.
    @PrimaryKey(autoGenerate = true) var id = 0
}
