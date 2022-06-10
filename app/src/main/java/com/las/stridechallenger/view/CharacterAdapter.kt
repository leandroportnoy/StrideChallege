package com.las.stridechallenger.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.las.stridechallenger.R
import com.las.stridechallenger.model.Character
import com.squareup.picasso.Picasso

internal class CharacterAdapter(private var items: List<Character>):
    RecyclerView.Adapter<CharacterAdapter.ItemCharacterViewHolder>() {

    internal inner class ItemCharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var characterImageView: ImageView = view.findViewById(R.id.character_item_imageview)
        var characterNameView: TextView = view.findViewById(R.id.character_name_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character_list, parent, false)
        return ItemCharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemCharacterViewHolder, position: Int) {
        val item = items[position]
        holder.characterNameView.text = item.name
            Picasso.get()
            .load(item.image)
            .resize(100, 100)
            .centerCrop()
            .into(holder.characterImageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}