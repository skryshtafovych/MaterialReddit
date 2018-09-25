package ui.reddit.sk.materialreddit.Core.Services


import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by skryshtafovych.
 */

interface ApiServices {

    //Get Stories
    //Static Headers
    @Headers("Content-type: $content_type","Accept: application/json")
    @GET("/url/url/url")
    fun getStories(
            @Header(authorization_oauth) oauth: String,
            @Header("X-Device-Uuid") deviceID: String
    ): Call<ResponseBody>


    //Post Token

    @FormUrlEncoded
    //Static Headers
    @Headers("Content-type: $content_type","Accept: application/json")
    @POST("/url/url/url")
    fun postTokenFetch(
                   @Header("Authorization") authorization: String
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