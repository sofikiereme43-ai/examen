package com.example.a4x4_puzle

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var BTNButtons: Array<Button>
    private lateinit var TXVMessage: TextView
    private lateinit var BTNRestart: Button
    private lateinit var BTNDisorder: Button
    private lateinit var BTNVerify: Button
    private lateinit var Tablero: Array<Array<String>>
    private val filas = 4
    private val columnas = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BTNButtons = arrayOf(
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btn10),
            findViewById(R.id.btn11),
            findViewById(R.id.btn12),
            findViewById(R.id.btn13),
            findViewById(R.id.btn14),
            findViewById(R.id.btn15),
            findViewById(R.id.btn16)
        )

        TXVMessage = findViewById(R.id.tvMensaje)
        BTNRestart = findViewById(R.id.btnReiniciar)
        BTNDisorder = findViewById(R.id.btnDesordenar)
        BTNVerify = findViewById(R.id.btnVerificar)

        Tablero = Array(filas) { fila ->
            Array(columnas) { col ->
                val numero = fila * columnas + col + 1
                if (numero < 16) numero.toString() else ""
            }
        }
        for (i in BTNButtons.indices) {
            val fila = i / columnas
            val col = i % columnas
            BTNButtons[i].setOnClickListener {
                click(fila, col)
            }
        }
    }

    private fun click(fila: Int, col: Int) {
        if (fila > 0 && Tablero[fila - 1][col] == "") {
            intercambiar(fila, col, fila - 1, col)
        } else if (fila < filas - 1 && Tablero[fila + 1][col] == "") {
            intercambiar(fila, col, fila + 1, col)
        } else if (col > 0 && Tablero[fila][col - 1] == "") {
            intercambiar(fila, col, fila, col - 1)
        } else if (col < columnas - 1 && Tablero[fila][col + 1] == "") {
            intercambiar(fila, col, fila, col + 1)
        }
    }

    private fun intercambiar(filaOrigen: Int, colOrigen: Int, filaVacia: Int, colVacia: Int) {
        val temp = Tablero[filaOrigen][colOrigen]
        Tablero[filaOrigen][colOrigen] = Tablero[filaVacia][colVacia]
        Tablero[filaVacia][colVacia] = temp

        actualizarBotones()
    }

    private fun actualizarBotones() {
        for (fila in 0 until filas) {
            for (col in 0 until columnas) {
                val index = fila * columnas + col
                BTNButtons[index].text = Tablero[fila][col]
            }
        }
    }
}