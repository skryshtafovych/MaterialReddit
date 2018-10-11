package ui.reddit.sk.materialreddit.Core


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.content_detail_main.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.reddit.sk.materialreddit.Core.Services.ApiServices
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel
import java.io.IOException

//1
class DetailStoryActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    lateinit var sharedPref: SharedPreferences



    companion object {
        const val EXTRA_ACCESS_TOKEN = "accessToken"
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"
        const val EXTRA_URL_PERMALINK = "permalink"


        fun newIntent(context: Context, story: StoriesModel): Intent {
            val detailIntent = Intent(context, DetailStoryActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, story.title)
            detailIntent.putExtra(EXTRA_URL, story.image_url)
            detailIntent.putExtra(EXTRA_URL_PERMALINK, story.permaLink)
            detailIntent.putExtra(EXTRA_ACCESS_TOKEN,story.accessToken)


            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail_main)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val title = intent.extras.getString(EXTRA_TITLE)
        val url_permalink = intent.extras.getString(EXTRA_URL_PERMALINK)
        val accessToken = intent.extras.getString(EXTRA_ACCESS_TOKEN)
        tempid.text = url_permalink

        FetchFunction(url_permalink)
        setTitle(title)

    }


    fun FetchFunction(permaLink:String ){

        val manager = getSupportFragmentManager()
        val transaction = manager.beginTransaction()
        //transaction.add(0, DetailStoryActivity)
        transaction.commit()
        val acessToken = sharedPref.getString("access_token", "na")

        print("FullJSON-Detail."+acessToken+"\nPermaLink"+permaLink)


        val clientFetch = OkHttpClient.Builder()
                .build()

        val retrofitFetch = Retrofit.Builder()
                .baseUrl(" https://oauth.reddit.com/")
                .client(clientFetch)
                .addConverterFactory(GsonConverterFactory.create())
                .build()



        val serviceFetch = retrofitFetch.create(ApiServices::class.java)


        try {
            val callFetch = serviceFetch.getDetailStories(permaLink,"Bearer "+acessToken)
            val urlFetching  = callFetch.request().url()
            println("FullJSON-Detail"+urlFetching)
            callFetch.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {



                    if (response.isSuccessful) {

                        try {
                            val jsonStr = response.body()!!.string()
                            println("FullJSON-Detail"+jsonStr)



                        } catch (e: IOException) {
                            Log.e("InsideDetailStoryFetch", "Error handling API Response", e)

                        }


                    } else
                        try {

                            val rawcheckBody = response.errorBody()!!.string()
                            println("InsideDetailStoryFetch"+rawcheckBody)

                        } catch (e: Exception) {
                            e.printStackTrace()
                            return

                        }


                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }



    }



}
