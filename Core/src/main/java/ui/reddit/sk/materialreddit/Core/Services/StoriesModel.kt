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

        fun FetchFunction(accessToken: String): List<StoriesModel>{


            val storiesList = ArrayList<StoriesModel>()

            storiesList.clear()
            storiesList.add(StoriesModel(0,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Cup Cake"));
            storiesList.add(StoriesModel(1,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Donut"));
            storiesList.add(StoriesModel(2,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Eclair"));
            storiesList.add(StoriesModel(3,"https://vignette.wikia.nocookie.net/central/images/1/10/Reddit.png/revision/latest?cb=20171025091848", "Froyo"));



            val clientFetch = OkHttpClient.Builder()
                    .build()
//https://oauth.reddit.com/r/programming/
            val retrofitFetch = Retrofit.Builder()
                    .baseUrl(" https://oauth.reddit.com/")
                    .client(clientFetch)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()



            val serviceFetch = retrofitFetch.create(ApiServices::class.java)


            try {
                val callFetch = serviceFetch.getStories("r/all/","Bearer "+accessToken)
                callFetch.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                        if (response.isSuccessful) {

                            try {
                                val jsonStr = response.body()!!.string()

                                val tokenExtract = JSONObject(jsonStr)
                                val childrenOBJ = tokenExtract.getJSONObject("data")
                                val arrayOfStories = childrenOBJ.getJSONArray("children")
                                val pagingAfter = childrenOBJ.optString("after")
                                val pagingBefore = childrenOBJ.optString("before")
                                //Repopulation OF Model
                                storiesList.add(StoriesModel(4,pagingAfter, "saldjfhlkajsdf"));

                                for (i in 0..(arrayOfStories.length() - 1)) {
                                    val item = arrayOfStories.getJSONObject(i)
                                    val singleKindType = item.getJSONObject("data")
                                    val subreddit_name_prefixed = singleKindType.optString("subreddit_name_prefixed")
                                    //DATA OBJ With Specifc Values
                                    println("FakeIterator since going over index."+subreddit_name_prefixed)

                                }

                                val arraySIZE = arrayOfStories.length()
                                println("ArrayCount"+arraySIZE+"\nPAGING"+pagingAfter)
                                println("IN-theModelFetch"+arrayOfStories)


                            } catch (e: IOException) {
                                Log.e("IN-theModelFetch", "Error handling API Response", e)

                            }


                        } else
                            try {

                                val rawcheckBody = response.errorBody()!!.string()
                                println("IN-theModelFetch"+rawcheckBody)

                            } catch (e: Exception) {
                                e.printStackTrace()

                            }


                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

            } catch (e: Exception) {
                e.printStackTrace()
            }


            return storiesList
        }





    }


}