package com.example.casebycase.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("user/sign-up/")
    fun register(
        @Body rdata :SignUpMessage
    ): Call<SignUpInfo>
}