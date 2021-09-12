package com.andriod.game15

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.andriod.game15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    private val adapter by lazy { Game15Adapter() }
    private val viewModel by lazy {
        ViewModelProvider(this).get(Game15ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) viewModel.onCreate()
        prepareField()
    }

    private fun prepareField() {
        viewModel.field.observe(this) {
            adapter.data = it
        }

        binding.recyclerView.apply {
            layoutManager =
                CustomGridLayoutManager(this@MainActivity, Game15ViewModel.FIELD_SIZE).apply {
                    isScrollEnabled = false
                }
            adapter = this@MainActivity.adapter

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.onSwipe(viewHolder, direction)
                    this@MainActivity.adapter.notifyDataSetChanged()
                }
            }).attachToRecyclerView(this)
        }
    }
}