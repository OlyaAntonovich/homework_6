package com.example.homework_6

import androidx.recyclerview.widget.RecyclerView
import com.example.homework_6.databinding.ItemBinding

class ItemViewHolder(
    private val binding: ItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.textName.text = item.name
        binding.textSurname.text = item.surname
        binding.textPhone.text = item.phone
        binding.textAge.text = item.age
        binding.textDob.text = item.dob
    }
}