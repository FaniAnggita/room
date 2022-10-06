package com.example.a203110026_fanianggita_room


import androidx.lifecycle.LiveData

// TODO 4: Membuat kelas Repositori
/*
Kelas repositori pada dasarnya mengabstraksi akses ke berbagai sumber data seperti mendapatkan data dari API
atau mendapatkan data dari database Room.
 */

class CatatanRepositori(private val catatanDao: CatatanDao) {

   // Membuat variabel 'semuaCatatan' untuk mendapatkan semua data dari kelas catatanDao.
    val semuaCatatan: LiveData<List<Catatan>> = catatanDao.getSemuaCatatan()

   // Method insert untuk menambah catatan
   fun insert(note: Catatan) {
        catatanDao.insert(note)
    }

    // Method delete untuk menghapus catatan
    fun delete(note: Catatan){
        catatanDao.delete(note)
    }

    // Method update untuk memperbarui catatan
    fun update(note: Catatan){
        catatanDao.update(note)
    }
}
