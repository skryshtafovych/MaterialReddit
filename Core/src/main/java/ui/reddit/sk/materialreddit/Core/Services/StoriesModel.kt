package ui.reddit.sk.materialreddit.Core.Services

import java.io.Serializable

class StoriesModel(    var id: Int ,
                       var desc: String,
                       var image_url: String): Serializable {




    companion object {

        fun getStoriesModel(): List<StoriesModel> {
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