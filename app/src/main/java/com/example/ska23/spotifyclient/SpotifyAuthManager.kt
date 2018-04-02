package com.example.ska23.spotifyclient

import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.SpotifyPlayer

object SpotifyAuthManager {

    var token: String? = null

    var player: SpotifyPlayer? = null


    var isTokenValid = !token.isNullOrEmpty()

    fun authenticateUser(activity: SpotifyBaseActivity,
                         CLIENT_ID: String,
                         REDIRECT_URI: String,
                         REQUEST_CODE: Int,
                         scopeConsts: Array<String>) {

        val builder = AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI)

        builder.setScopes(scopeConsts)

        val request = builder.build()

        if (SpotifyBaseActivity.token == null) {
            AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request)
        }
    }
}