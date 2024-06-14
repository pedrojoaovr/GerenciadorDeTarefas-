package com.example.teste

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotaDataBaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "tarefaapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "alltarefas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_TAREFA = "tarefa"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITULO TEXT, $COLUMN_TAREFA TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertTarefa(tarefa: Tarefa){
        val db= writableDatabase
        val values = ContentValues().apply{
        put(COLUMN_TITULO, tarefa.titulo)
            put(COLUMN_TAREFA, tarefa.tarefa)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getALLNotes(): List<Tarefa> {
        val notesList = mutableListOf<Tarefa>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO))
            val tarefa = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAREFA))

            val note = Tarefa(id, titulo, tarefa)
            notesList.add(note)
        }
        cursor.close()
        db.close()
        return notesList
    }

}