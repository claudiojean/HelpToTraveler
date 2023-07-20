package com.example.helptotraveler.Others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helptotraveler.Model.Viagem
import com.example.helptotraveler.R
import kotlinx.android.synthetic.main.layout_viagens.view.*

class RecyclerViagens(val listener: RowClickListener) : RecyclerView.Adapter<RecyclerViagens.MyViewHolder>(){

    private var viagemList = emptyList<Viagem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.layout_viagens, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(viagemList[position])
        }
        holder.bind(viagemList[position])
    }

    class MyViewHolder(itemView: View, val listener: RowClickListener) : RecyclerView.ViewHolder(itemView){

        val origemTravel = itemView.origemTravel
        val destinno = itemView.destinoTravel
        val dataITravel = itemView.dataITravel
        val dataFTravel = itemView.dataFTravel
        val delete = itemView.deleteTravel

        fun bind(viagem: Viagem) {
            origemTravel.text = viagem.origem
            destinno.text = viagem.destino
            dataITravel.text = viagem.dataI
            dataFTravel.text = viagem.dataF
            delete.setOnClickListener {
                listener.onDeleteViagemClickListener(viagem)
            }
        }
    }

    override fun getItemCount(): Int {
        return viagemList.size
    }

    fun setData(viagens: List<Viagem>){
        this.viagemList = viagens
        notifyDataSetChanged()
    }

    interface RowClickListener{
        fun onDeleteViagemClickListener(viagem: Viagem)
        fun onItemClickListener(viagem: Viagem)
    }
}