package ui.reddit.sk.materialreddit.Core

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel


class MyAdapter(private val storiesList: ArrayList<StoriesModel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(storiesList[position])
    }

    override fun getItemCount(): Int {
        return storiesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.reddit_story, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(version: StoriesModel) {
            val textView = itemView.findViewById<TextView>(R.id.tv_desc)
            val imageView = itemView.findViewById<ImageView>(R.id.iv_preview_story)
            textView.text = version.image_url;

            Glide.with(itemView)
                    .load(version.desc)
                    .into(imageView)




        }
    }
}