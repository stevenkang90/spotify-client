package com.example.ska23.spotifyclient

object SpotifyAuthManager {

    var token: String? = null

    init {
        //try and load the token from persistance
    }

    var isTokenValid = !token.isNullOrEmpty()
}