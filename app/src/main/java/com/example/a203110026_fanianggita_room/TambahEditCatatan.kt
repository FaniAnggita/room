package com.example.a203110026_fanianggita_room

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class TambahEditCatatan : AppCompatActivity() {
    // mendeklarikan variabel untuk menentukan tampilan layout.
    lateinit var judulCatatanEdt: EditText
    lateinit var editCatatan: EditText
    lateinit var bntSimpan: Button

    // menginisialisasi variabel viewModal
    lateinit var viewModal: CatatanViewModel

    // menginisialisasi variabel catatanID
    var catatanID = -1

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_edit_catatan)

        // menginisialiasi kelas viewmodel
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CatatanViewModel::class.java)

        // mendeklarikan variabel yang mana datanya dari file layout activity_tambah_edit_catatan
        judulCatatanEdt = findViewById(R.id.idJudulCatatan)
        editCatatan = findViewById(R.id.idEditDeskripsi)
        bntSimpan = findViewById(R.id.idBtn)

        // untuk mengambil data dari intent
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
         // menginisialisasi data untuk update
            val judulCatatan = intent.getStringExtra("judulCatatan")
            val deskripsiCatatan = intent.getStringExtra("deskripsiCatatan")
            catatanID = intent.getIntExtra("catatanID", -1)
            bntSimpan.text = "Memperbarui Catatan"
            judulCatatanEdt.setText(judulCatatan)
            editCatatan.setText(deskripsiCatatan)
        } else {
            bntSimpan.text = "Simpan Catatan"
        }

        // ababila tombol simpan diklik maka akan menjalankan blok kode di dalamnya
        bntSimpan.setOnClickListener {
            // endaptkan data dari judul catatan dan deskripsi catatan
            val judulCatatan = judulCatatanEdt.text.toString()
            val deskripsiCatatan = editCatatan.text.toString()

           // menentukan apakah user membuat data baru atau memperbarui data
            if (noteType.equals("Edit")) {
                if (judulCatatan.isNotEmpty() && deskripsiCatatan.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Catatan(judulCatatan, deskripsiCatatan, currentDateAndTime)
                    updatedNote.id = catatanID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Catatan Berhasil Diperbarui!..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (judulCatatan.isNotEmpty() && deskripsiCatatan.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                   viewModal.addNote(Catatan(judulCatatan, deskripsiCatatan, currentDateAndTime))
                    Toast.makeText(this, "$judulCatatan Berhasil Ditambahkan!", Toast.LENGTH_LONG).show()
                }
            }
            // kembali ke halaman activity utama jika data telah disimpan!
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}