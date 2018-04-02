package com.example.spotifywebapi

import com.example.spotifywebapi.webinterface.SpotifyAlbumInterface
import com.example.spotifywebapi.webinterface.SpotifyPlaylistInterface
import retrofit2.Retrofit

class SpotifyWebService(val service: SpotifyPlaylistInterface = Network.createService(SpotifyPlaylistInterface::class.java)) {

    fun getMyPlayLists(oauthToken: String) = service.getMyPlaylists("Bearer $oauthToken")

}