package ui.reddit.sk.materialreddit.Core.Services


import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by skryshtafovych.
 */

interface ApiServices {

    //Get Stories
    @Headers("Authorization: $x_esa_apikey",
            "Content-type: $content_type")
    @GET("/url/url/url")
    fun getStories(
            @Header(authorization_oauth) oauth: String,
            @Header("X-Device-Uuid") deviceID: String
    ): Call<ResponseBody>


    //Post Token
    @Headers("Authorization: $x_esa_apikey",
            "Content-type: $content_type")
    @POST("/url/url/url")
    fun postTokenFetch(
                   @Header(authorization_oauth) oauth: String
    ): Call<ResponseBody>



    companion object {
        ////////////////////////////////////////////////////////////
        ///////////////HEADERS FOR Reddit//////////////////////////////
        ////////////////////////////////////////////////////////////
        const val x_esa_apikey = ""
        const val authorization_oauth = ""
        const val content_type = ""

    }

}
