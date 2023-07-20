package com.example.helptotraveler.Others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.R
import kotlinx.android.synthetic.main.layout_eventos.view.*

class RecyclerEventos(val listener: RowClickListener) : RecyclerView.Adapter<RecyclerEventos.MyViewHolder>(){

    private var eventoList = emptyList<Evento>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.layout_eventos, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(eventoList[position])
        }
        holder.bind(eventoList[position])
    }

    class MyViewHolder(itemView: View, val listener: RowClickListener) : RecyclerView.ViewHolder(itemView){
        val descricaoEvent = itemView.descricaoEvent
        val localEvent = itemView.localEvent
        val dataEvent = itemView.dataEvent
        val horarioInicialEvent = itemView.horarioInicialEvent
        val horarioFinalEvent = itemView.horarioFinalEvent
        val delete = itemView.deleteEvent

        fun bind(evento: Evento) {
            descricaoEvent.text = evento.descricao
            localEvent.text = evento.local
            dataEvent.text = evento.data

            val horaI:Int= (evento.horarioI/60)
            val minuteI:Int = evento.horarioI.rem(60)

            val horaF:Int = (evento.horarioF/60)
            val minuteF:Int = evento.horarioF.rem(60)

            if(minuteI<10 && horaI<10)
                horarioInicialEvent.text = ("0$horaI:0$minuteI")
            else if (minuteI>=10 && horaI<10)
                horarioInicialEvent.text = ("0$horaI:$minuteI")
            else if (minuteI<10 && horaI>=10)
                horarioInicialEvent.text = ("$horaI:0$minuteI")
            else
                horarioInicialEvent.text = ("$horaI:$minuteI")

            if(minuteF<10 && horaF<10)
                horarioFinalEvent.text = ("0$horaF:0$minuteF")
            else if (minuteF>=10 && horaF<10)
                horarioFinalEvent.text = ("0$horaF:$minuteF")
            else if (minuteF<10 && horaF>=10)
                horarioFinalEvent.text = ("$horaF:0$minuteF")
            else
                horarioFinalEvent.text = ("$horaF:$minuteF")

            delete.setOnClickListener {
                listener.onDeleteEventClickListener(evento)
            }
        }
    }

    override fun getItemCount(): Int {
        return eventoList.size
    }

    fun setData(eventos: List<Evento>){
        this.eventoList = eventos
        notifyDataSetChanged()
    }

    interface RowClickListener{
        fun onDeleteEventClickListener(evento: Evento)
        fun onItemClickListener(evento: Evento)
    }
}