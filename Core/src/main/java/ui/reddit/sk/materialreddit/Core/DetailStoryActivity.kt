package ui.reddit.sk.materialreddit.Core


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ui.reddit.sk.materialreddit.Core.Services.StoriesModel

//1
class DetailStoryActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, story: StoriesModel): Intent {
            val detailIntent = Intent(context, DetailStoryActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, story.title)
            detailIntent.putExtra(EXTRA_URL, story.image_url)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail_main)

        //val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        //setSupportActionBar()

        val title = intent.extras.getString(EXTRA_TITLE)
        val url = intent.extras.getString(EXTRA_URL)

        setTitle(title)



        webView = findViewById(R.id.detail_web_view)

        webView.loadUrl(url)
    }
}
