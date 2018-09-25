package ui.reddit.sk.materialreddit.Core

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ui.reddit.sk.materialreddit.Core.Services.SharedPrefRecorder
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {




    private var mRecyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var mAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>? = null
    var listOfusers: ArrayList<StoriesModel> = ArrayList()
    protected lateinit var recorder: SharedPrefRecorder
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initilizing SharedPref
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        recorder = SharedPrefRecorder(baseContext);
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        println("Intercept1")



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        mRecyclerView = findViewById(R.id.my_recycler_view)

        //var mLayoutManager = androidx.recyclerview.widget.RecyclerView.VERTICAL(this, , false)
        //mRecyclerView!!.layoutManager = mLayoutManager

        mAdapter = Myadapter(listOfusers)
        mRecyclerView!!.adapter = mAdapter

    }



    public override fun onResume() {
        super.onResume()
        //Fetches Values from Reddit
        getTokenForFetchingArticles()
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
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
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



    private fun getTokenForFetchingArticles() {
        // TODO("need to add Netwokr call logic") //To change body of created functions use File | Settings | File Templates.
        val uuidPref = sharedPref.getString("uuid", "na")


        if(!uuidPref.contains("na")){
            Snackbar.make(nav_view , uuidPref+"UUID Present", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
           //uuid.text = uuidPref




        }else{
            val uuid = UUID.randomUUID().toString()
            Snackbar.make(nav_view , "NoUUID-InPref", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            recorder.savePreferencesS("uuid",uuid)
        }

    }






}
