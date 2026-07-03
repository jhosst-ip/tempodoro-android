package com.ipial.jhostyn.tempodoro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipial.jhostyn.tempodoro.model.Tarea
import com.ipial.jhostyn.tempodoro.model.Usuario

@Database(
    entities = [
        Usuario::class,
        Tarea::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun tareaDao(): TareaDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tempodoro_db"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}