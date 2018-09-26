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
    @Headers("Content-type: $content_type",
            "Accept: application/json")
    @GET("/{pathToFetch}")
    fun getStories(
            @Path("pathToFetch") pathToFetch: String,
            @Header("Authorization") oauth: String
    ): Call<ResponseBody>


    //Post Token

    @FormUrlEncoded
    //Static Headers
    @Headers("Accept: application/json")
    @POST("access_token")
    fun postTokenFetch(
                   @Header("Authorization") authorization: String,
                   @Field("grant_type") grantType: String,
                   @Field("device_id") deviceID: String
    ): Call<ResponseBody>



    companion object {
        ////////////////////////////////////////////////////////////
        ///////////////HEADERS FOR Reddit//////////////////////////////
        ////////////////////////////////////////////////////////////
        const val x_esa_apikey = ""
        const val authorization_oauth = ""
        const val content_type = "application/json"

    }

}
