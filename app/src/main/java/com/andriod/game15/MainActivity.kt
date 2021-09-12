package com.andriod.game15

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.andriod.game15.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prepareField()
    }

    private fun prepareField() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, FIELD_SIZE)
            adapter = Game15Adapter().apply {
                data = generateData()
            }
        }
    }

    private fun generateData(): List<Int> {
        val result = mutableListOf<Int>()
        val map = HashMap<Int, Boolean>()
        for (i in 0 until FIELD_SIZE * FIELD_SIZE) {
            do {
                val value = Random.nextInt(FIELD_SIZE * FIELD_SIZE)
                if (map[value] == true) continue
                map[value] = true
                result.add(value)
                break
            } while (true)
        }

        return result
    }

    companion object {
        const val FIELD_SIZE = 4
    }
}