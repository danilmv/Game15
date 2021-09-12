package com.andriod.game15

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager

class CustomGridLayoutManager(context: Context, attrs: Int) :
    GridLayoutManager(context, attrs) {
    var isScrollEnabled = true

    fun constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        GridLayoutManager(context, attrs, defStyleAttr, defStyleRes)
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}