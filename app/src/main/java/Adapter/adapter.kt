package Adapter

import Interface.Carlistener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week2.R
import com.example.week2.databinding.CardFilmBinding
import model.Hewan

class adapter(val ListdataHewan: ArrayList<Hewan>, val carlistener: Carlistener):RecyclerView.Adapter<adapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_film, parent, false)
        return viewHolder(view, carlistener)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(ListdataHewan[position], position)
    }
    override fun getItemCount(): Int {
        return  ListdataHewan.size
    }



    class viewHolder(val itemView: View, val carlistener1: Carlistener ): RecyclerView.ViewHolder(itemView){
        val binding = CardFilmBinding.bind(itemView)

        fun setData(hewan: Hewan, position: Int){
            binding.namahewan.text = hewan.namahewan
            binding.jenisHewan.text = hewan.jenishewan
            binding.usia.text = hewan.usiahewan.toString()+ " Tahun"
            binding.editbtnn.setOnClickListener {
                carlistener1.editOnclick(position)
            }
            binding.tomdel.setOnClickListener{
                carlistener1.deleteOnClick(position)
            }
            binding.cahya.setOnClickListener {
                carlistener1.onVoiceClicked(position)
            }
            binding.yoga.setOnClickListener {
                carlistener1.onFeedClicked(position)
            }

        }
    }




}