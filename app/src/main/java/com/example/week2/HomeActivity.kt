package com.example.week2

import Adapter.adapter
import Interface.Carlistener

import model.GlobalVar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2.databinding.ActivityHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeActivity : AppCompatActivity(), Carlistener {
    private lateinit var viewbind: ActivityHomeBinding
    private  val adapterhewan = adapter(GlobalVar.ListdataHewan, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        setupRecycler()
        setUpListener()
    }
    private fun setUpListener(){
        viewbind.add.setOnClickListener {
            val add = Intent(baseContext, AddActivity::class.java).apply {
                putExtra("status", GlobalVar.statusadd)
            }
            startActivity(add)
        }
    }


    override fun onResume() {
        super.onResume()
        if(GlobalVar.ListdataHewan.size > 0){
            viewbind.recyclerView.visibility = View.VISIBLE
            viewbind.textView.visibility = View.GONE
        }else{
            viewbind.recyclerView.visibility = View.GONE
            viewbind.textView.visibility = View.VISIBLE
        }
        adapterhewan.notifyDataSetChanged()
    }

    private fun setupRecycler(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewbind.recyclerView.layoutManager =layoutManager
        viewbind.recyclerView.adapter = adapterhewan
    }

    override fun onFeedClicked(posistion: Int) {
        if(GlobalVar.ListdataHewan.get(posistion).jenishewan.equals("Ayam")){
            Toast.makeText(baseContext, "kamu memberi makan biji-bijian", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(baseContext, "kamu memberi makan rumput", Toast.LENGTH_LONG).show()
        }
    }


    override fun editOnclick(position: Int) {

        val edit = Intent(baseContext, AddActivity::class.java).apply {
            putExtra("status", GlobalVar.statusedit)
            putExtra("posisi", position)
        }
        startActivity(edit)


    }
    override fun onVoiceClicked(posistion: Int){
        if(GlobalVar.ListdataHewan.get(posistion).jenishewan.equals("Ayam")){
            Toast.makeText(baseContext, "mwoooooooooo", Toast.LENGTH_LONG).show()
        }else if(GlobalVar.ListdataHewan.get(posistion).jenishewan.equals("Kambing")){
            Toast.makeText(baseContext, "Mbhekkkkkkk", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(baseContext, "petok", Toast.LENGTH_LONG).show()
        }
    }


    override fun deleteOnClick(posistion: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah kamu mau menghapus hewan ini?")
            .setNegativeButton("Batal") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Setuju") { dialog, which ->
                GlobalVar.ListdataHewan.removeAt(posistion)
                Toast.makeText(baseContext, "Data  di hapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }


}