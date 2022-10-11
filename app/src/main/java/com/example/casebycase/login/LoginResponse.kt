package com.example.casebycase.login

import android.media.session.MediaSession
import android.media.session.MediaSession.Token

data class SignUpInfo(
    val MESSAGE: String?
)

data class SignUpMessage(
    val name: String ?= null,
    val email: String ?= null,
    val password: String ?= null,
    val nickname: String ?= null,
    val phone: String ?= null
)

data class PostLogin(
    val email: String?,
    val password: String?,
    val access_token: String?,
    val user_nickname: String
)

data class PostLogin2(
    val email: String?,
    val password: String?
)

