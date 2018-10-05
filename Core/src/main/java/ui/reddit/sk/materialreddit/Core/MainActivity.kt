package ui.reddit.sk.materialreddit.Core

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.reddit.sk.materialreddit.Core.Services.ApiServices
import ui.reddit.sk.materialreddit.Core.Services.SharedPrefRecorder
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel
import java.io.IOException
import java.util.*
import org.json.JSONObject
import java.time.Instant
import kotlin.reflect.KFunction1



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {




    private var mRecyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var mAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>? = null
    var listOfusers: ArrayList<StoriesModel> = ArrayList()
    protected lateinit var recorder: SharedPrefRecorder
    lateinit var sharedPref: SharedPreferences
    private var service: ApiServices? = null
    val storiesListFetched = ArrayList<StoriesModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initilizing SharedPref
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        recorder = SharedPrefRecorder(baseContext);
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        println("Intercept1")
        //FetchFunction("NA")
        tokenDecider("",::FetchFunction)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        //setStoriesList()
    }



    public override fun onResume() {
        super.onResume()
        println("Intercept2")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings ->
//                showAlertDialog {
//                    setTitle("Settings")
//                    setMessage("Here you can change amount of Stories returned on one page")
//                    positiveButton("Yes") {
//
//                    }
//
//                    negativeButton {
//                    }
//                }
                settingsChooser()


            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }






    fun tokenDecider(uuid: String, kFunction0: KFunction1<@ParameterName(name = "na") String, Unit>){


        val acessToken = sharedPref.getString("access_token", "na")
        val accessTokenExpireTime = sharedPref.getLong("expires_in", 0)
        val now = Instant.now().toEpochMilli()
        println("SharedPref"+now+"\nSharedPref"+accessTokenExpireTime)
        ///IF TOKEN IS EXPIRED OR DEAD GET NEW TOKEN
        if(now > accessTokenExpireTime){
            println("TokenHasExpired")


            val oauthEncoded = "Basic MFpGaVU2amJabjRENnc6T2h1WmxobjlXS1BOOHNwUnFtZm1rY29WYVE4"
            val client = OkHttpClient.Builder()
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/api/v1/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(ApiServices::class.java)
            try {
                println("InsideNetworkCall"+oauthEncoded+"uuid"+uuid)

                val call = service.postTokenFetch(oauthEncoded,"https://oauth.reddit.com/grants/installed_client","4e0b545ad2dd445983f6b1ea88c8f0f3")
                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                        if (response.isSuccessful) {

                            try {
                                val jsonStr = response.body()!!.string()

                                val tokenExtractRaw = JSONObject(jsonStr)
                                val accessToken = tokenExtractRaw.getString("access_token")
                                val accessTokenExpiresOn = tokenExtractRaw.getLong("expires_in")


                                val now = Instant.now().toEpochMilli()
                                val combinedExpiredTime = now+accessTokenExpiresOn
                                println("InsideNetworkCall-Token"+accessToken)
                                recorder.savePreferencesS("access_token",accessToken)
                                recorder.savePreferencesNumL("expires_in",combinedExpiredTime)
                                return kFunction0.call(accessToken)


                            } catch (e: IOException) {
                                Log.e("MainActivity", "Error handling API Response", e)

                            }


                        } else
                            try {

                                val rawcheckBody = response.errorBody()!!.string()
                                println("InsideNetworkCall"+rawcheckBody)

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

        mAdapter?.notifyDataSetChanged()


        val uuidPref = sharedPref.getString("uuid", "na")
        if(!uuidPref.contains("na"))
        {
        }else{
            val uuid = UUID.randomUUID().toString()
            recorder.savePreferencesS("uuid",uuid)
        }


        return kFunction0.call(acessToken)

    }




    fun FetchFunction(accessToken: String){


        val articlePerPage =  sharedPref.getString("articlePerPage", "25")


        print("SharedPref."+articlePerPage)

        val clientFetch = OkHttpClient.Builder()
                .build()
//https://oauth.reddit.com/r/programming/
        // //oauth.reddit.com/r/all?limit=2
        val retrofitFetch = Retrofit.Builder()
                .baseUrl(" https://oauth.reddit.com/")
                .client(clientFetch)
                .addConverterFactory(GsonConverterFactory.create())
                .build()



        val serviceFetch = retrofitFetch.create(ApiServices::class.java)


        try {
            val callFetch = serviceFetch.getStories("r/all/",articlePerPage,"Bearer "+accessToken)
            callFetch.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                    storiesListFetched.clear()
                    var imagesAR: String = ""


                    if (response.isSuccessful) {

                        try {
                            val jsonStr = response.body()!!.string()

                            val tokenExtract = JSONObject(jsonStr)
                            val childrenOBJ = tokenExtract.getJSONObject("data")
                            val arrayOfStories = childrenOBJ.getJSONArray("children")
                            val pagingAfter = childrenOBJ.optString("after")
                            val pagingBefore = childrenOBJ.optString("before")


                            for (i in 0..(arrayOfStories.length() - 1)) {
                                val item = arrayOfStories.getJSONObject(i)
                                val singleKindType = item.getJSONObject("data")
                                val subreddit_name_prefixed = singleKindType.optString("subreddit_name_prefixed")
                                val title = singleKindType.optString("title")
                               // val smallTitle = getWordFromTitle(title)
                                val num_comments = singleKindType.optInt("num_comments")
                                val score = singleKindType.optInt("score")
                                imagesAR = singleKindType.optString("thumbnail")
                                val previewARImages = singleKindType.optJSONObject("preview")
                                print("FullJSON-Per-Item."+item)

                                if(previewARImages!== null){
                                    imagesAR = previewARImages.optJSONArray("images").getJSONObject(0).getJSONObject("source").getString("url")
                                    println("FakeIterator since going over index."+imagesAR)
                                }
                                //DATA OBJ With Specifc Values
                                storiesListFetched.add(StoriesModel(0,imagesAR, subreddit_name_prefixed,num_comments.toString(),score.toString(),title));


                                // Your code here
                            }

                            val arraySIZE = arrayOfStories.length()
                            println("ArrayCount"+arraySIZE+"\nPAGING"+pagingAfter)






                            //THIS IS How Paging API should look like.
                            //https://oauth.reddit.com/r/all?after=t3_9kh692

//                                    t1_	Comment
//                                    t2_	Account
//                                    t3_	Link
//                                    t4_	Message
//                                    t5_	Subreddit
//                                    t6_	Award




                            println("InsideStoryFetch"+arrayOfStories)


                        } catch (e: IOException) {
                            Log.e("InsideStoryFetch", "Error handling API Response", e)

                        }


                        setStoriesList()
                    } else
                        try {

                            val rawcheckBody = response.errorBody()!!.string()
                            println("InsideStoryFetch"+rawcheckBody)

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
    private fun setStoriesList() {

        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val versions = ArrayList<StoriesModel>()
        versions.addAll(storiesListFetched)
        val myAdapter = MyAdapter(versions)
        recyclerView.adapter = myAdapter
    }


    private fun getWordFromTitle(sentenceToChop:String ):String{

        val arr = sentenceToChop.split(" ".toRegex(), 6).toTypedArray()

        val firstWord = arr[0]   //the
        val theRest = arr[1]



        return firstWord
    }









    fun showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
        val builder = AlertDialog.Builder(this)
        val seek =  SeekBar(this);
        seek.setMax(255);
        seek.setKeyProgressIncrement(1);
        builder.dialogBuilder()
        builder.setView(seek)
        val dialog = builder.create()

        dialog.show()
    }

    fun AlertDialog.Builder.positiveButton(text: String = "Okay", handleClick: (which: Int) -> Unit = {}) {
        this.setPositiveButton(text, { dialogInterface, which-> handleClick(which) })
    }

    fun AlertDialog.Builder.negativeButton(text: String = "Cancel", handleClick: (which: Int) -> Unit = {}) {
        this.setNegativeButton(text, { dialogInterface, which-> handleClick(which) })
    }


    private fun settingsChooser() {
        val alert = android.app.AlertDialog.Builder(this)
                .setPositiveButton("ok-test", null)

        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.custom_settings_dialog, null)


        val input = promptsView.findViewById(R.id.custom_settings_et) as EditText
        alert.setView(promptsView)
        alert.setNegativeButton("Cancel") { dialog, whichButton ->
            dialog.cancel()
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
        alert.setPositiveButton("Ok") { dialog, whichButton ->
            try {

                val value = input.text.toString()



                recorder.savePreferencesS("articlePerPage",input.text.toString())



                this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
                Toast.makeText(this, value,
                        Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                Log.d("InsideNumChooser", "No Values in draw# field")
                alert.setPositiveButton("", null)

                e.printStackTrace()
            }
        }


        val dialog = alert.create()


        input.addTextChangedListener(object : TextWatcher {
            private fun handleText() {
                // Grab the button
                val okButton = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                if (input.text.length < 0 || input.text.length > 2) {
                    okButton.isEnabled = false
                } else {
                    okButton.isEnabled = true
                }
            }

            override fun afterTextChanged(arg0: Editable) {
                handleText()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Nothing to do
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Nothing to do
            }
        })

        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        dialog.show()
        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).isEnabled = false


    }






}
