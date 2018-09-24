package ui.reddit.sk.materialreddit.Core

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import ui.reddit.sk.materialreddit.Core.Services.SharedPrefRecorder
import java.util.*
import java.util.UUID.randomUUID



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    protected lateinit var recorder: SharedPrefRecorder
    lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initilizing SharedPref
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        recorder = SharedPrefRecorder(baseContext);
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)




        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun getTokenForFetchingArticles() {
       // TODO("need to add Netwokr call logic") //To change body of created functions use File | Settings | File Templates.
        val uuidPref = sharedPref.getString("uuid", "na")


        if(!uuidPref.contains("na")){
            Snackbar.make(nav_view , uuidPref+"UUID Present", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            hellow.text = uuidPref




        }else{
            val uuid = UUID.randomUUID().toString()
            Snackbar.make(nav_view , "NoUUID-InPref", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            recorder.savePreferencesS("uuid",uuid)
        }







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
}
