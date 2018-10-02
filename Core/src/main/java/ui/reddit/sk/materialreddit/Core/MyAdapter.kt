package ui.reddit.sk.materialreddit.Core

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.nav_header_main.view.*
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
            val commentCount = itemView.findViewById<TextView>(R.id.tv_comments)
            val upvoteCount = itemView.findViewById<TextView>(R.id.tv_upvotes)
            textView.text = version.image_url;
            commentCount.text = version.comment_count
            upvoteCount.text = version.upvote_count

            Glide.with(itemView)
                    .load(version.desc)
                    .into(imageView)




        }
    }
}