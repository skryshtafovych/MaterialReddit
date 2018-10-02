package ui.reddit.sk.materialreddit.Core.Services

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.Serializable

class StoriesModel(    var id: Int ,
                       var desc: String,
                       var image_url: String,
                       var comment_count: String,
                       var upvote_count: String ): Serializable {






}