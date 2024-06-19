package com.example.teste

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.teste.databinding.ActivityAtualizarTarefaBinding

class AtualizarTarefa : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarTarefaBinding
    private lateinit var db: NotaDataBaseHelper
    private var tarefaId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarTarefaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotaDataBaseHelper(this)

        tarefaId = intent.getIntExtra("tarefa_id", -1)
        if (tarefaId == -1){
            finish()
            return
        }

        val note = db.gettarefaByID(tarefaId)
        binding.updateTitleEditText.setText(note.titulo)
        binding.updatetarefaEditText.setText(note.tarefa)

        binding.updateSaveButton.setOnClickListener{
            val newTitulo = binding.updateTitleEditText.text.toString()
            valnewContent = binding.updateContendEditText.text.toString()
            val updateTarefa = Tarefa(tarefaId, newTitulo, newContent)
            db.updateTarefa(updatedTarefa)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}