package com.kakapo.screeningtest1.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kakapo.screeningtest1.databinding.ItemViewEventBinding
import com.kakapo.screeningtest1.model.EventModel
import com.kakapo.screeningtest1.ui.MenuActivity

class EventAdapter(
    private val context: Context
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    companion object{
        const val EVENT = "event"
    }

    private var event = listOf<EventModel>()

    inner class ViewHolder(view: ItemViewEventBinding) : RecyclerView.ViewHolder(view.root){
        val tvName = view.tvName
        val tvDate = view.tvDate
        val ivEvent = view.ivEvent
        val cvEvent = view.cvItemEvent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewEventBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = event[position]
        holder.tvName.text = item.nama
        holder.tvDate.text = item.tanggal
        Glide.with(context)
            .load(item.image)
            .into(holder.ivEvent)
        holder.cvEvent.setOnClickListener {
            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra(EVENT, item.nama)
            context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun eventList(list: List<EventModel>){
        event = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return event.size
    }
}