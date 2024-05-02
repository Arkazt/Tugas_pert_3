package id.arion.dicoding.tugas_pert_3

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val pilihan = arrayOf("Pilih Umur", "Kurang dari 17 Tahun", "Lebih dari 17 Tahun")
    var posisi = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtBil1 = findViewById<EditText>(R.id.numberWeight)
        val edtBil2 = findViewById<EditText>(R.id.numberHeight)
        val btnHitung = findViewById<Button>(R.id.btnCalculate)
        val txtHasil = findViewById<TextView>(R.id.status)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pilihan)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "kategori terpilih : " + pilihan[position],
                    Toast.LENGTH_SHORT
                ).show()
                posisi = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //listener
        btnHitung.setOnClickListener {
            val bil1 = edtBil1.text.toString().toDouble()
            val bil2 = edtBil2.text.toString().toDouble()

            //perhitungan
            val ubahBil2 = bil2 / 100
            val hasil = bil1.toFloat() / (ubahBil2.toFloat() * ubahBil2.toFloat())

            txtHasil.text = hasil.toString()

            when (posisi) {
                0 -> {
                    txtHasil.text = "Pilih Umur"
                }

                1 -> {
                    BMIAnak(hasil)
                }

                2 -> {
                    BMIDewasa(hasil)
                }
            }
        }

    }

    fun BMIAnak(hasil: Float) {
        val txtHasil = findViewById<TextView>(R.id.status)
        txtHasil.text = hasil.toString()
        when {
            hasil < 5 -> {
                txtHasil.text = "Kurang Gizi"
            }

            hasil in 5.0..84.9 -> {
                txtHasil.text = "Normal"
            }

            hasil in 85.0..95.0 -> {
                txtHasil.text = "Kelebihan Gizi"
            }

            hasil > 95 -> {
                txtHasil.text = "Obesitas"
            }
        }

    }

    fun BMIDewasa(hasil: Float) {
        val txtHasil = findViewById<TextView>(R.id.status)
        txtHasil.text = hasil.toString()
        when {
            hasil < 18.5 -> {
                txtHasil.text = "Kurang Gizi"
            }

            hasil in 18.5..24.9 -> {
                txtHasil.text = "Normal"
            }

            hasil in 25.0..30.0 -> {
                txtHasil.text = "Kelebihan Gizi"
            }

            hasil > 30 -> {
                txtHasil.text = "Obesitas"
            }
        }
    }
}




