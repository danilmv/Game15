package com.andriod.game15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class Game15ViewModel : ViewModel() {

    private val _field = MutableLiveData<MutableList<Int>>()
    val field: LiveData<MutableList<Int>> get() = _field
    private val data = mutableListOf<Int>()

    fun onCreate() {
        data.clear()
        data.addAll(generateData())
        _field.postValue(data)
    }

    private fun generateData(): MutableList<Int> {
        val result = mutableListOf<Int>()
        val map = HashMap<Int, Boolean>()
        for (i in 0 until NUM_ITEMS) {
            do {
                val value = Random.nextInt(NUM_ITEMS)
                if (map[value] == true) continue
                map[value] = true
                result.add(value)
                break
            } while (true)
        }

        return result
    }

    fun onSwipe(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val move = when (direction) {
            ItemTouchHelper.UP -> -FIELD_SIZE
            ItemTouchHelper.DOWN -> FIELD_SIZE
            ItemTouchHelper.LEFT -> -1
            ItemTouchHelper.RIGHT -> 1
            else -> 0
        }

        val from = viewHolder.adapterPosition
        val to = from + move
        if (to < 0 || to >= NUM_ITEMS) return
        if (field.value?.get(to) == 0) {
            swap(from, to)
        } else return
    }

    private fun swap(from: Int, to: Int) {
        data[to] = data[from]
        data[from] = 0
        _field.postValue(data)
    }

    companion object {
        const val FIELD_SIZE = 4
        const val NUM_ITEMS = FIELD_SIZE * FIELD_SIZE
    }
}

