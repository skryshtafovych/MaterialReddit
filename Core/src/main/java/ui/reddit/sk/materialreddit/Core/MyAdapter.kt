package ui.reddit.sk.materialreddit.Core

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.Log
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity


class MyAdapter(val context1: MainActivity ,private val storiesList: ArrayList<StoriesModel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(storiesList[position],context1)



    }

    override fun getItemCount(): Int {
        return storiesList.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {




        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.reddit_story, parent, false))
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(version: StoriesModel,context: Context) {
            val card = itemView.findViewById<LinearLayout>(R.id.ln_card_view)
            val textView = itemView.findViewById<TextView>(R.id.tv_desc)
            val imageView = itemView.findViewById<ImageView>(R.id.iv_preview_story)
            val commentCount = itemView.findViewById<TextView>(R.id.tv_comments)
            val upvoteCount = itemView.findViewById<TextView>(R.id.tv_upvotes)
            val title = itemView.findViewById<TextView>(R.id.tv_title)




                itemView.setOnClickListener(View.OnClickListener {

                 

                    println("Dude Inside onClickListener "+version.desc)


                })




            textView.text = version.image_url;
            commentCount.text = version.comment_count
            upvoteCount.text = version.upvote_count

            title.text = version.title

            Glide.with(itemView)
                    .load(version.desc)
                    .into(imageView)

//Where the Color of Article is controlled setting it to
// GrayScale is Nice way to Inform User Article already Read.
            if(version.title.contains("These")){
                val colorMatrix = ColorMatrix()
                colorMatrix.setSaturation(0f)
                val filter = ColorMatrixColorFilter(colorMatrix)
                imageView.setColorFilter(filter)
            }





        }
    }
}