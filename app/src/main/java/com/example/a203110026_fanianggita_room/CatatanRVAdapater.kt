package com.example.a203110026_fanianggita_room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TODO 6: Membuat kelas adapter untuk mangatur item pada recyclerview
class CatatanRVAdapter(
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<CatatanRVAdapter.ViewHolder>() {

    //  Variabel array setiap daftar catatan
    private val semuaCatatan = ArrayList<Catatan>()

   // kelas view holder membungkus a item pada RecylerView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Mendeklariskan variabel yang mana nilainya dari file layout
        val catatanTV: TextView = itemView.findViewById(R.id.idJudulCatatan)
        val tanggalTV: TextView = itemView.findViewById(R.id.idTanggalCatatan)
        val hapusTV: ImageView = itemView.findViewById(R.id.idIVDelete)
    }

    // mengembalikan setiap item pada recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_catatan,
            parent, false
        )
        return ViewHolder(itemView)
    }

    // menyetel data ke setiap item tampilan recyclerview.
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.catatanTV.text = semuaCatatan.get(position).judulCatatan
        holder.tanggalTV.text = "Diperbarui: " + semuaCatatan.get(position).timeStamp
        // Apabila tombol delete pada setiap item diklik
        holder.hapusTV.setOnClickListener {
                     noteClickDeleteInterface.onDeleteIconClick(semuaCatatan.get(position))
        }

        // Apabila setiap item diklik
        holder.itemView.setOnClickListener {
               noteClickInterface.onNoteClick(semuaCatatan.get(position))
        }
    }

    // menentukan ukuran setiap item
    override fun getItemCount(): Int {
             return semuaCatatan.size
    }

   // fungsi ini untuk melakukan update setiap item
   @SuppressLint("NotifyDataSetChanged")
   fun updateList(newList: List<Catatan>) {
        semuaCatatan.clear()
        semuaCatatan.addAll(newList)
        notifyDataSetChanged()
    }
}

// fungsi ini untuk melakukan delete setiap item
interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Catatan)
}

//  memperbarui tampilan item pada recyclerview
interface NoteClickInterface {

    fun onNoteClick(note: Catatan)
}
