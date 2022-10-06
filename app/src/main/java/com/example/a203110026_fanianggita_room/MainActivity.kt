package com.example.a203110026_fanianggita_room


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    // mendeklarikan variabel untuk menentukan tampilan layout.
    lateinit var viewModal: CatatanViewModel
    lateinit var catatanRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variabel ini untuk mengambil id beserta nilanya pada activity_main.
        catatanRV = findViewById(R.id.catatanRV)
        addFAB = findViewById(R.id.btnTambah)

       // Mengatur layout ke recylerview.
        catatanRV.layoutManager = LinearLayoutManager(this)

       // variabel untuk  menginisialisasi kelas adapter.
        val noteRVAdapter = CatatanRVAdapter(this, this)

      // set kelas adapter ke layout recylerview.
        catatanRV.adapter = noteRVAdapter

        // variabel untuk  menginisialisasi kelas ViewModal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CatatanViewModel::class.java)

        // memanggil semua catatan dari kelas ViewModal.
        viewModal.semuaCatatan.observe(this) { list ->
            list?.let {
                // untuk update list pada recylcerview/
                noteRVAdapter.updateList(it)
            }
        }

        // apabila tombol tambah diklik, maka blok kode akan dijelankan.
        addFAB.setOnClickListener {
            // melakukan intent atau navigasi ke halaman TambahEditCatatan
            val intent = Intent(this@MainActivity, TambahEditCatatan::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Catatan) {
         // melakukan pembacaan input data yang akan disimpan
        val intent = Intent(this@MainActivity, TambahEditCatatan::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("judulCatatan", note.judulCatatan)
        intent.putExtra("deskripsiCatatan", note.deskripsiCatatan)
        intent.putExtra("catatanID", note.id)
        startActivity(intent)
        this.finish()
    }

    // apabila tombol delete diklik, maka akan menampilkan toast
    override fun onDeleteIconClick(note: Catatan) {
        viewModal.deleteNote(note)
            Toast.makeText(this, "${note.judulCatatan} Catatan Berhasil dihapus", Toast.LENGTH_LONG).show()
    }
}
