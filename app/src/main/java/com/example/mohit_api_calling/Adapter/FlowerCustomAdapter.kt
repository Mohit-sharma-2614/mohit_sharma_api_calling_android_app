package com.example.mohit_api_calling.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mohit_api_calling.Data.Flower_DataItem
import com.example.mohit_api_calling.R
import com.squareup.picasso.Picasso

class FlowerCustomAdapter(
    private val data: List<Flower_DataItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FlowerCustomAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(flower: Flower_DataItem)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flowerName: TextView = view.findViewById(R.id.tv_flower_name)
        val flowerImage: ImageView = view.findViewById(R.id.img_flower)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(data[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flower_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flower = data[position]
        Picasso
            .get()
            .load(flower.imageUrl)
            .into(holder.flowerImage)
        holder.flowerName.text = flower.name
    }
}