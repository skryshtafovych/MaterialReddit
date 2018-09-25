package ui.reddit.sk.materialreddit.Core

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel

class Myadapter(private val mDataList: ArrayList<StoriesModel>) : RecyclerView.Adapter<Myadapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reddit_story, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_login.text = mDataList[position].login
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tv_login: TextView

        init {
            tv_login = itemView.findViewById<View>(R.id.tv_login) as TextView
        }
    }
}