package ui.reddit.sk.materialreddit.Core


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.content_detail_main.*

//1
class DetailStoryFragment : Fragment() {

    //2
    companion object {

        fun newInstance(): DetailStoryFragment {
            return DetailStoryFragment()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.content_detail_main, container, false)
    }

}