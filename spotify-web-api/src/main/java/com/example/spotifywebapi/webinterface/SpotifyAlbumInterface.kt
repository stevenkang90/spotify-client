package com.example.spotifywebapi.webinterface

import com.example.spotifywebapi.models.Album
import com.fasterxml.jackson.annotation.JsonProperty
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single
import java.util.*

interface SpotifyAlbumInterface {

    @GET ("/v1/albums/{id}")
    fun getAlbum(@Path("id") id: String) : Single<Result<Album>>
}

typealias ExternalUrl = Map<String, String>
typealias ExternalId = Map<String, String>



data class Track(val album: Album,
                 val artist: List<Artist>)

data class Artist(@JsonProperty("external_urls") val externalUrls: ExternalUrl)

data class ImageObject(val height: Int,
                       val url: String,
                       val width: Int)

data class CopyrightObject(private val text: String,
                           private val type: String)