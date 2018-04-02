package com.example.ska23.spotifyclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import android.content.Intent
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.LoginActivity
import com.spotify.sdk.android.player.Config
import com.spotify.sdk.android.player.Spotify
import com.spotify.sdk.android.player.SpotifyPlayer

open class SpotifyBaseActivity : AppCompatActivity() {

    val REQUEST_CODE = 1121

    companion object {
        var token: String? = null
    }

    protected open var REDIRECT_URI : String? = null
    protected open var CLIENT_ID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
            // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    token = response.accessToken

                    AuthenticationClient.stopLoginActivity(this, LoginActivity.REQUEST_CODE)
                    val playerConfig = Config(this, response.accessToken, CLIENT_ID)

                    Spotify.getPlayer(playerConfig, this, object : SpotifyPlayer.InitializationObserver {
                        override fun onInitialized(spotifyPlayer: SpotifyPlayer) {
                            val player = spotifyPlayer
//                            player.addConnectionStateCallback(this@LauncherActivity)
//                            player.addNotificationCallback(this@LauncherActivity)

                            SpotifyAuthManager.player = player

                            player.playUri(null, "spotify:track:4tnbcPfOSZq5VAU67XxIdN", 0, 0)
                        }

                        override fun onError(throwable: Throwable) {

                        }


                    })
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
