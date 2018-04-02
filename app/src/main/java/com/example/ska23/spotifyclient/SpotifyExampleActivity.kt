package com.example.ska23.spotifyclient

import android.os.Bundle
import com.example.spotifywebapi.SpotifyWebService
import com.example.spotifywebapi.models.PlaylistResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.Result


class SpotifyExampleActivity : SpotifyBaseActivity() {


    override var REDIRECT_URI : String? = "things-spotify-client://login/callback"
    override var CLIENT_ID: String? = "c1e596724b3442fda53060bf6f75ec86"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(token == null) {
            SpotifyAuthManager.authenticateUser(this,
                    CLIENT_ID.orEmpty(),
                    REDIRECT_URI.orEmpty(),
                    REQUEST_CODE,
                    arrayOf(ScopeConsts.USER_READ_PRIVATE,
                            ScopeConsts.STREAMING,
                            ScopeConsts.PLAYLIST_READ_PRIVATE,
                            ScopeConsts.PLAYLIST_READ_COLLABORATIVE))
        }
    }

    override fun onResume() {
        super.onResume()

        token?.let{
            SpotifyWebService().getMyPlayLists(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ t: Result<PlaylistResponse>? ->
                        if(t?.isError == true) {

                        }
                    }, { error ->
                        error
                    })
        }
    }

}
