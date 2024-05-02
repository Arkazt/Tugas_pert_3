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
            }}

        btnHitung.setOnClickListener(View.OnClickListener {
            val bil1 = edtBil1.text.toString().toDouble()
            val bil2 = edtBil2.text.toString().toDouble()

            val ubahBil2 = bil2 / 100
            val hasil = bil1 / (ubahBil2*ubahBil2)

            txtHasil.text = hasil.toString()

            when (posisi) {
                0 -> {
                    txtHasil.text = "Pilih Umur"
                }
                1 -> {
                    BMIAnak(bmi)
                }
                2 -> {
                    BMIDewasa(bmi)
                }
        }
        })

        fun resultBMIAnak(bmi: Float) {

            }
        }

    }

    private fun BMIAnak(bmi: Float) {
        txtHasil.text = bmi.toString()
        when {
            bmi < 5 -> {
                txtHasil.text = "Underweight"
            }

            bmi in 5.0..84.9 -> {
                txtHasil.text = "Normal"
            }

            bmi in 85.0 .. 95.0 -> {
                txtHasil.text = "Overweight"
            }

            bmi > 95 -> {
                txtHasil.text = "Obese"
            }
        }

    }

}

