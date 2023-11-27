package com.orhanavan.mymovieexplorer.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SingleItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    private val halfSpace: Int = spaceHeight / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val layoutManager = parent.layoutManager
        val spanCount = if (layoutManager is GridLayoutManager) {
            layoutManager.spanCount
        } else {
            1
        }
        val itemCount = parent.adapter!!.itemCount

        with(outRect) {

            left = spaceHeight
            right = spaceHeight

            val position = parent.getChildAdapterPosition(view)
            // first line
            if (parent.getChildAdapterPosition(view) < spanCount) {
                top = spaceHeight
                bottom = halfSpace
            }
            // last line
            else if (position >= itemCount - spanCount) {
                top = halfSpace
                bottom = spaceHeight
            }
            // elements in middle
            else {
                top = halfSpace
                bottom = halfSpace
            }
        }
    }
}