package com.andriod.game15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class Game15ViewModel : ViewModel() {

    private val _gameField = MutableLiveData<MutableList<Int>>()
    val gameField: LiveData<MutableList<Int>> get() = _gameField
    private val gameFieldValues = mutableListOf<Int>()

    fun onCreate() {
        gameFieldValues.clear()
        gameFieldValues.addAll(generateData())
        _gameField.postValue(gameFieldValues)
    }

    private fun generateData(): MutableList<Int> {
        val result = mutableListOf<Int>()
        for (i in (0 until NUM_ITEMS).shuffled())
            result.add(i)

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
        if (gameField.value?.get(to) == 0) {
            swap(from, to)
        } else return
    }

    private fun swap(from: Int, to: Int) {
        gameFieldValues[to] = gameFieldValues[from]
        gameFieldValues[from] = 0
        _gameField.postValue(gameFieldValues)
    }

    companion object {
        const val FIELD_SIZE = 4
        const val NUM_ITEMS = FIELD_SIZE * FIELD_SIZE
    }
}

