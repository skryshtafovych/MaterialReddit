package ui.reddit.sk.materialreddit.Core.Services

import java.io.Serializable

class StoriesModel(    var id: Int ,
                       var desc: String,
                       var image_url: String): Serializable {




    companion object {

        fun getStoriesModel(): List<StoriesModel> {
            val storiesList = ArrayList<StoriesModel>()
            storiesList.clear()
            storiesList.add(StoriesModel(0,"https://raw.githubusercontent.com/AndroidCodility/Picasso-RecyclerView/master/images/cup_cake.png", "Cup Cake"))
            storiesList.add(StoriesModel(1,"https://raw.githubusercontent.com/AndroidCodility/Picasso-RecyclerView/master/images/donut.png", "Donut"))
            storiesList.add(StoriesModel(2,"https://raw.githubusercontent.com/AndroidCodility/Picasso-RecyclerView/master/images/eclair.png", "Eclair"))
            storiesList.add(StoriesModel(3,"https://raw.githubusercontent.com/AndroidCodility/Picasso-RecyclerView/master/images/Froyo.jpg", "Froyo"))

            return storiesList
        }
    }


}