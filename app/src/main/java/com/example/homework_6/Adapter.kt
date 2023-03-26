package com.example.homework_6


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_6.R.drawable
import com.example.homework_6.databinding.ItemBinding


class Adapter(
    private val listItems: MutableList<Item>
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    inner class ItemViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {

            val imAv = when (item.name.first()) {
                in 'a'..'c' -> drawable.ic_boat_fish
                in 'd'..'f' -> drawable.ic_sea_horse
                in 'g'..'j' -> drawable.ic_many_fish
                in 'k'..'m' -> drawable.ic_three_whales
                in 'n'..'p' -> drawable.ic_turtle
                in 'q'..'s' -> drawable.ic_two_fish
                in 't'..'w' -> drawable.ic_whale_birds
                else -> drawable.ic_fish_boat
            }

            binding.textName.text = item.name
            binding.textSurname.text = item.surname
            binding.textPhone.text = item.phone
            binding.textAge.text = item.age
            binding.textDob.text = item.dob
            binding.imageAvatar.setImageResource(imAv)

            binding.btnDelete.setOnClickListener {
                val position = adapterPosition
                listItems.removeAt(position)
                notifyItemRemoved(position)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItems.size


}




