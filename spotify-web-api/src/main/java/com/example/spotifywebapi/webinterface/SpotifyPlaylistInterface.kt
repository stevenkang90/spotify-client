package com.example.spotifywebapi.webinterface

import com.example.spotifywebapi.models.Album
import com.example.spotifywebapi.models.Playlist
import com.example.spotifywebapi.models.PlaylistResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

val AUTHORIZATION_BEARER= "Authorization: Bearer"
interface SpotifyPlaylistInterface {

    @GET ("/v1/me/playlists")
    fun getMyPlaylists(@Header("Authorization") oauth: String,
                       @Query("limit") limit: Int,
                       @Query("offset") offset: Int) : Single<Result<PlaylistResponse>>

}

