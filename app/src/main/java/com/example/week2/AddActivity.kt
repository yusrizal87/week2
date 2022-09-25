package com.example.week2

import model.Ayam
import model.GlobalVar
import model.Hewan
import model.Sapi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.week2.databinding.ActivityAddBinding
import kotlin.NumberFormatException

class AddActivity : AppCompatActivity() {
    private lateinit var viewbind: ActivityAddBinding
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityAddBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        GetIntent()
        setUpListener()

    }

    private fun GetIntent(){
        if(intent.getIntExtra("status", 0) == GlobalVar.statusadd){
            viewbind.imageView2.visibility = View.INVISIBLE
        }else if(intent.getIntExtra("status", 0) == GlobalVar.statusedit){
            position = intent.getIntExtra("posisi", -1)

            viewbind.Title.editText?.setText(GlobalVar.ListdataHewan[position].namahewan)
            viewbind.Rating.editText?.setText(GlobalVar.ListdataHewan[position].usiahewan.toString())
            if(viewbind.iqmal.checkedRadioButtonId == viewbind.radioButton3.id){
                "Ayam"
            }else if(viewbind.iqmal.checkedRadioButtonId == viewbind.radioButton2.id){
                "Sapi"
            }else if(viewbind.iqmal.checkedRadioButtonId == viewbind.radioButton.id){
                "Kambing"
            }
        }
    }

    private fun setUpListener() {
        viewbind.basori.setOnClickListener {
            finish()
        }
        viewbind.movieAdd.setOnClickListener {
            try{
                if(viewbind.radioButton3.isChecked) {
                    val hewan = Ayam(
                        viewbind.Title.editText?.text.toString().trim(),
                        viewbind.Rating.editText?.text.toString().trim().toInt(),
                        viewbind.radioButton3.text.toString().trim()

                    )
                    if (FormChecker(hewan)) {
                        if (intent.getIntExtra("status", 0) == GlobalVar.statusadd) {
                            GlobalVar.ListdataHewan.add(hewan)
                        } else if (intent.getIntExtra("status", 0) == GlobalVar.statusedit) {
                            GlobalVar.ListdataHewan[position] = hewan
                        }
                        Toast.makeText(baseContext, "Data di simpan", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Data tidak tersimpan", Toast.LENGTH_LONG).show()
                    }
                }else if(viewbind.radioButton2.isChecked) {
                    val hewan = Sapi(
                        viewbind.Title.editText?.text.toString().trim(),
                        viewbind.Rating.editText?.text.toString().trim().toInt(),
                        viewbind.radioButton2.text.toString().trim()
                    )
                    if (FormChecker(hewan)) {
                        if (intent.getIntExtra("status", 0) == GlobalVar.statusadd) {
                            GlobalVar.ListdataHewan.add(hewan)
                        } else if (intent.getIntExtra("status", 0) == GlobalVar.statusedit) {
                            GlobalVar.ListdataHewan[position] = hewan
                        }
                        Toast.makeText(baseContext, "Data di simpan", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Data tidak tersimpan", Toast.LENGTH_LONG).show()
                    }
                }
            }catch (e: NumberFormatException) {
                viewbind.Title.error = "Usia belum terisi"
            }
        }
    }
    private fun FormChecker(hewan:Hewan): Boolean {

        var isCompleted = true

        if(hewan.namahewan.isEmpty()){
            viewbind.Title.error = "Nama hewan belum terisi"
            isCompleted = false
        }else{
            viewbind.Title.error = ""
        }

        if(hewan.usiahewan == 0){
            viewbind.Rating.error = "Umur hewan belum terisi"
            isCompleted = false
        }else{
            viewbind.Rating.error = ""
        }

        if(hewan.jenishewan.isEmpty()){
            if (viewbind.radioButton.isChecked == false){
                viewbind.radioButton.error = "Jenis Hewan Belum terisi"
            }else if(viewbind.radioButton2.isChecked == false){
                viewbind.radioButton2.error = "Jenis hewan belum terisi"
            }else{
                viewbind.radioButton3.error = "Jenis hewan belum terisi"
            }
        }

        return isCompleted
    }
}