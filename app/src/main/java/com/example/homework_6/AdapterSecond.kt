package com.example.homework_6

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_6.databinding.ItemSecondBinding

class AdapterSecond(
    private val listItemsSecond: MutableList<ItemSecond>,
    val context: Context
) : RecyclerView.Adapter<AdapterSecond.ItemViewHolder>() {

    inner class ItemViewHolder(
        private val binding: ItemSecondBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemSecond: ItemSecond) {

            val listOfImages = listOf(
                R.drawable.ic_boat_fish, R.drawable.ic_sea_horse,
                R.drawable.ic_many_fish, R.drawable.ic_three_whales, R.drawable.ic_turtle,
                R.drawable.ic_two_fish, R.drawable.ic_whale_birds, R.drawable.ic_fish_boat
            )

            makeBackgroundForFirstLetter(itemSecond.name,
                listOfImages,binding,context)

            with(binding) {

                textName.text = itemSecond.name
                textSurname.text = itemSecond.surname
                textPhone.text = itemSecond.phone
                textAge.text = itemSecond.age
                textDob.text = itemSecond.dob

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemSecondBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemSecond = listItemsSecond[position]
        holder.bind(itemSecond)
    }

    override fun getItemCount(): Int = listItemsSecond.size

}

private fun createPalette(bitmap: Bitmap, binding: ItemSecondBinding) {
    Palette.from(bitmap).generate { palette ->
        val defaultValue = 0x000000
        if (palette != null) {
            binding.rv.setBackgroundColor(palette.getLightMutedColor(defaultValue))

        } else {
            binding.rv.setBackgroundColor(Color.parseColor("#AARRGGBB"))
        }
    }
}

private fun makeBackgroundForFirstLetter(
    string: String,
    list: List<Int>,
    binding: ItemSecondBinding,
    context: Context
) {
    when (string.first()) {
        in 'a'..'c' -> {
            binding.imageAvatar.setImageResource(list[0])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[0]
                )
            createPalette(bitmap1, binding)
        }

        in 'd'..'f' -> {
            binding.imageAvatar.setImageResource(list[1])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[1]
                )
            createPalette(bitmap1, binding)
        }
        in 'g'..'j' -> {
            binding.imageAvatar.setImageResource(list[2])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[2]
                )
            createPalette(bitmap1, binding)
        }
        in 'k'..'m' -> {
            binding.imageAvatar.setImageResource(list[3])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[3]
                )
            createPalette(bitmap1, binding)
        }
        in 'n'..'p' -> {
            binding.imageAvatar.setImageResource(list[4])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[4]
                )
            createPalette(bitmap1, binding)
        }

        in 'q'..'s' -> {
            binding.imageAvatar.setImageResource(list[5])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[5]
                )
            createPalette(bitmap1, binding)
        }
        in 't'..'w' -> {
            binding.imageAvatar.setImageResource(list[6])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[6]
                )
            createPalette(bitmap1, binding)

        }
        else -> {
            binding.imageAvatar.setImageResource(list[7])
            val bitmap1: Bitmap =
                BitmapFactory.decodeResource(
                    context.resources,
                    list[7]
                )
            createPalette(bitmap1, binding)
        }
    }


}