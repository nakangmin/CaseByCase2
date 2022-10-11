package com.example.casebycase.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {

    @POST("/user/log-in/")
    fun login(
        @Body rdata:PostLogin2
    ): Call<PostLogin>

}