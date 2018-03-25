package com.example.spotifywebapi.webinterface

import com.example.spotifywebapi.models.Album
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

interface SpotifyAlbumInterface {

    @GET ("/v1/albums/{id}")
    fun getAlbum(@Path("id") id: String) : Single<Result<Album.Simplified>>

    @GET("/v1/albums?ids={ids}")
    fun getAlbums(@Path("ids")id: List<String>): Single<Result<List<Album.Simplified>>>
}

