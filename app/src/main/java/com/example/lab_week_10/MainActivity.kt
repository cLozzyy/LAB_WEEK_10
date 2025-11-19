package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room // Impor Room
import com.example.lab_week_10.database.Total // Impor Entity Total
import com.example.lab_week_10.database.TotalDatabase // Impor TotalDatabase
import com.example.lab_week_10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    // Instance database
    private lateinit var db: TotalDatabase

    // Konstanta ID untuk baris tunggal di database
    private val ID: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi database
        db = prepareDatabase()

        // 2. Muat nilai awal dari database
        initializeValueFromDatabase()

        // 3. Persiapkan ViewModel dan observer LiveData
        prepareViewModel()
    }

    // Dipanggil saat Activity akan beralih ke background/ditutup
    override fun onPause() {
        super.onPause()

        // Simpan nilai LiveData saat ini ke database
        viewModel.total.value?.let { currentTotal ->
            db.totalDao().update(Total(ID, currentTotal))
        }
    }

    // Fungsi untuk membuat dan mengonfigurasi instance Room Database
    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java, "total-database"
        ).allowMainThreadQueries().build()
    }

    // Fungsi untuk memuat data dari database ke ViewModel
    private fun initializeValueFromDatabase() {
        val totalList = db.totalDao().getTotal(ID)

        if (totalList.isEmpty()) {
            // Jika database kosong (pertama kali dibuka), masukkan nilai awal 0
            db.totalDao().insert(Total(ID, 0))
            viewModel.setTotal(0)
        } else {
            // Jika ada data, muat nilai terbaru ke ViewModel
            val latestTotal = totalList.first().total
            viewModel.setTotal(latestTotal)
        }
    }

    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    private fun prepareViewModel() {
        // Observer LiveData: Memperbarui UI Activity
        viewModel.total.observe(this) { total ->
            updateText(total)
        }

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }
}