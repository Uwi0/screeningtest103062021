package com.kakapo.screeningtest1.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kakapo.screeningtest1.databinding.ItemViewGuestBinding
import com.kakapo.screeningtest1.model.GuestData
import com.kakapo.screeningtest1.model.GuestImageData
import com.kakapo.screeningtest1.ui.MenuActivity

class GuestAdapter(
    private val context: Context
) : RecyclerView.Adapter<GuestAdapter.ViewHolder>(){

    companion object{
        const val GUEST_NAME = "guest name"
        const val GUEST_DATE = "guest date"
    }

    private var list = listOf<GuestData>()
    private var listImage = listOf<GuestImageData>()

    inner class ViewHolder(view: ItemViewGuestBinding) : RecyclerView.ViewHolder(view.root){
        val tvName = view.tvGuest
        val ivGuest = view.ivGuest
        val rlGuest = view.rlGuest
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewGuestBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemGuest = list[position]
        val itemGuestImage = listImage[position]
        Glide.with(context)
            .load(itemGuestImage.image)
            .into(holder.ivGuest)
        holder.tvName.text = itemGuest.name

        holder.rlGuest.setOnClickListener {

            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra(GUEST_NAME, itemGuest.name)

            val guestDate = itemGuest.birthdate.split("-")
            intent.putExtra(GUEST_DATE, guestDate[2])
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun guestList(guest: List<GuestData>,guestImage: List<GuestImageData>){
        list = guest
        listImage = guestImage
        notifyDataSetChanged()
    }

}