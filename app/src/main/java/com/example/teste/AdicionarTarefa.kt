package com.example.teste

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teste.databinding.ActivityAdicionarNotaBinding

class AdicionarTarefa : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarNotaBinding
    private lateinit var db: NotaDataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotaDataBaseHelper(this)

        binding.saveButton.setOnClickListener{
            val titulo = binding.titleEditText.text.toString()
            val tarefa = binding.contendEditText.text.toString()
            val tarefa_ = Tarefa(0, titulo, tarefa)
            db.insertTarefa(tarefa_)
            finish()
            Toast.makeText(this, "Tarefa Salva", Toast.LENGTH_SHORT).show()
        }
    }
}