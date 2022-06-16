package com.jeff.customgesturelib.version

import retrofit2.Call
import retrofit2.http.GET


interface  ApiService {
    @GET("version.txt")
    fun getVersionData(): Call<VersionData>
}