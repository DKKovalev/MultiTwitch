package dkkovalev.com.multitwitch.ui.utils

import android.support.v7.widget.RecyclerView
import android.view.View

interface OnRecyclerItemClickListener {
    fun onClick(viewHolder: RecyclerView.ViewHolder, view: View, pos: Int)
}
