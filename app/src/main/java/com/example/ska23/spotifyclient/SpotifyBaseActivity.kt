package com.example.ska23.spotifyclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import android.content.Intent
import com.spotify.sdk.android.authentication.AuthenticationRequest





open class SpotifyBaseActivity : AppCompatActivity() {

    val REQUEST_CODE = 1337
    val REDIRECT_URI = "yourcustomprotocol://callback"
    protected val CLIENT_ID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        if(!SpotifyAuthManager.isTokenValid) {
            val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

            builder.setScopes(arrayOf("streaming"))
            val request = builder.build()

            AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
            // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    SpotifyAuthManager.token = response.accessToken
                }

            // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                }
            }// Handle successful response
            // Handle error response
            // Most likely auth flow was cancelled
            // Handle other cases
        }
    }
}
