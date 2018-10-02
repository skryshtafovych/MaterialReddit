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
                       var image_url: String): Serializable {




    companion object {

        fun getStoriesModel(): List<StoriesModel> {
            //FetchFunction("-5rCjSuy6VTG3kikS6pa2XcykSeY")

            val storiesList = ArrayList<StoriesModel>()
            storiesList.clear()
            storiesList.add(StoriesModel(0,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Cup Cake"));
            storiesList.add(StoriesModel(1,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Donut"));
            storiesList.add(StoriesModel(2,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Eclair"));
            storiesList.add(StoriesModel(3,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Froyo"));

            return storiesList
        }




    }


}