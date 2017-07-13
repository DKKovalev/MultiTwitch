package dkkovalev.com.multitwitch.ui.utils

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import dkkovalev.com.multitwitch.R
import dkkovalev.com.multitwitch.data.models.top_games.Top
import dkkovalev.com.multitwitch.data.models.top_streams.Stream

class RecyclerAdapter(private val context: Context, private val onRecyclerItemClickListener: OnRecyclerItemClickListener, private val items: ArrayList<Any>) : RecyclerView.Adapter<RecyclerAdapter.TopGamesViewHolder>() {

    override fun getItemCount(): Int = items.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGamesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.top_games_item, null)
        val viewHolder = TopGamesViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TopGamesViewHolder, position: Int) {
        val item = items[position]
        if (item is Top) Picasso.with(context).load(Uri.parse(item.game.box.large)).fit().into(holder.thumbnail)
        else if (item is Stream) Picasso.with(context).load(Uri.parse(item.preview.large)).fit().into(holder.thumbnail)
    }

    inner class TopGamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onRecyclerItemClickListener.onClick(this@TopGamesViewHolder, p0!!, adapterPosition)
        }

        val thumbnail: ImageView = itemView.findViewById<ImageView>(R.id.top_games_thumbnail)
    }
}