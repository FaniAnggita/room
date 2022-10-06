package com.example.a203110026_fanianggita_room



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO 5: Membuat kelas ViewModel
/* Peran ViewModel adalah memberikan data ke UI atau bertindak  sebagai pusat komunikasi antara Repositori dan UI.
ViewModel akan mengubah data dari Repositori, dari Flow ke LiveData, dan mengekspos daftar kata sebagai LiveData ke UI.
Hal ini memastikan bahwa setiap kali data berubah dalam database, UI akan otomatis diperbarui. */


class CatatanViewModel (application: Application) :AndroidViewModel(application) {

    // Variabel untuk mengambil data dengan LiveData
    val semuaCatatan : LiveData<List<Catatan>>

    val repository : CatatanRepositori

    // Inisialisasi kelas dao dan repositori
    init {
        val dao = CatatanDatabase.getDatabase(application).getCatatanDao()
        repository = CatatanRepositori(dao)
        semuaCatatan = repository.semuaCatatan
    }

   // memanggil metode delete dari kelas CatatanRepositori  untuk menghapus data catatan.
    fun deleteNote (note: Catatan) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // memanggil metode update dari kelas CatatanRepositori  untuk memperbarui data catatan.
    fun updateNote(note: Catatan) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


    // memanggil metode insert dari kelas CatatanRepositori  untuk menambah data catatan.
    fun addNote(note: Catatan) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}
