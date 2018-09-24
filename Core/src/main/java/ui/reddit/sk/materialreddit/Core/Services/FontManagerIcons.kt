package ui.reddit.sk.materialreddit.Core.Services

import android.content.Context
import android.graphics.Typeface


object FontManagerIcons {

    val ROOT = "fonts/"
    val FONTAWESOME = ROOT + "fontawesome-webfont.ttf"

    fun getTypeface(context: Context, font: String): Typeface {
        return Typeface.createFromAsset(context.getAssets(), font)
    }

}