package com.example.a203110026_fanianggita_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// TODO 3: Mengimplementasikan database Room pada aplikasi

// kelas ini untuk Room harus abstract dan extend RoomDatabase.
@Database(entities = [Catatan::class], version = 1, exportSchema = false)
abstract class CatatanDatabase : RoomDatabase() {

    // Database mengekspos kelas DAO melalui metode "getter" abstrak untuk setiap @Dao.
    abstract fun getCatatanDao(): CatatanDao

    companion object {
    // Mencegah pembukaan database di waktu yang sama.
        @Volatile
        private var INSTANCE: CatatanDatabase? = null

        fun getDatabase(context: Context): CatatanDatabase {
            // Jika INSTANCE bukan null, maka kembalikan databasenya.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatatanDatabase::class.java,
                    "note_database"
                ).build()
                // mengembalikan instance database
                INSTANCE = instance
                instance
            }
        }
    }
}
