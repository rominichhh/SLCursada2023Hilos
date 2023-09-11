package com.example.hiloscursada2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var tvSonar: TextView
    lateinit var tvDescarga: TextView
    lateinit var btnSonar: Button
    lateinit var btnDescargar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSonar = findViewById(R.id.tvSuena)
        tvDescarga = findViewById(R.id.tvDescarga)
        btnSonar = findViewById(R.id.btnSonar)
        btnDescargar = findViewById(R.id.btnDescarga)


        var numeroCancion = 1
        var numeroDescarga = 1

        btnSonar.setOnClickListener {
            tvSonar.text = "Suena cancion numero $numeroCancion"
            numeroCancion++
        }

        btnDescargar.setOnClickListener {
            simularDescargaCorrutina(numeroDescarga)
            numeroDescarga++
        }
    }

    private fun simularDescarga(numeroDescarga: Int) {
        tvDescarga.text = "Inicia descarga $numeroDescarga"


        var thread = Thread(Runnable {
            Thread.sleep(5000) // Tarea Larga
            runOnUiThread(Runnable {
                tvDescarga.text = "Finalizó descarga $numeroDescarga"
            })
        })

        thread.start()

    }

    private fun simularDescargaCorrutina(numeroDescarga: Int){

        lifecycleScope.launch{
            tvDescarga.text = "Inicia descarga $numeroDescarga"

            withContext(Dispatchers.IO){
                Thread.sleep(5000)
            }

            tvDescarga.text = "Finalizó descarga $numeroDescarga"
        }


    }
}