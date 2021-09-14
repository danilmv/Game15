package com.andriod.game15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.andriod.game15.databinding.ItemCellBinding

class Game15Adapter : RecyclerView.Adapter<Game15Adapter.ViewHolder>() {
    var data = emptyList<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cell,
                parent,
                false)) {
        private val binding = ItemCellBinding.bind(itemView)

        fun bind(value: Int) {
            if (value != 0) {
                binding.textView.apply {
                    text = value.toString()
                    background = AppCompatResources.getDrawable(context,
                        if (value == adapterPosition + 1) R.color.green else R.color.red)
                }
            }
            binding.cardView.isVisible = value != 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size
}