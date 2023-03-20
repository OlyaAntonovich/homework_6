package com.example.homework_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_6.databinding.ItemSecondBinding

class AdapterSecond(
    private val listItemsSecond: MutableList<ItemSecond>
) : RecyclerView.Adapter<AdapterSecond.ItemViewHolder>() {

    inner class ItemViewHolder(
        private val binding: ItemSecondBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemSecond: ItemSecond) {

            val imAv = when (itemSecond.name.first()) {
                in 'a'..'c' -> R.drawable.ic_boat_fish
                in 'd'..'f' -> R.drawable.ic_sea_horse
                in 'g'..'j' -> R.drawable.ic_many_fish
                in 'k'..'m' -> R.drawable.ic_three_whales
                in 'n'..'p' -> R.drawable.ic_turtle
                in 'q'..'s' -> R.drawable.ic_two_fish
                in 't'..'w' -> R.drawable.ic_whale_birds
                else -> R.drawable.ic_fish_boat
            }

            binding.textName.text = itemSecond.name
            binding.textSurname.text = itemSecond.surname
            binding.textPhone.text = itemSecond.phone
            binding.textAge.text = itemSecond.age
            binding.textDob.text = itemSecond.dob
            binding.imageAvatar.setImageResource(imAv)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemSecondBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemSecond = listItemsSecond[position]
        holder.bind(itemSecond)
    }

    override fun getItemCount(): Int = listItemsSecond.size

}