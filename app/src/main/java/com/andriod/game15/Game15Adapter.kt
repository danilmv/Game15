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
                binding.cardView.isVisible = true
                binding.textView.apply {
                    text = value.toString()
                    binding.textView.background =
                        if (value == adapterPosition + 1)
                            AppCompatResources.getDrawable(context, R.color.green)
                        else
                            AppCompatResources.getDrawable(context, R.color.red)
                }
            } else {
                binding.cardView.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size
}