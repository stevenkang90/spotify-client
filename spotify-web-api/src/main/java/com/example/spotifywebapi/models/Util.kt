package com.example.spotifywebapi.models

import com.fasterxml.jackson.annotation.JsonProperty

typealias ExternalUrl = Map<String, String>
typealias ExternalId = Map<String, String>

data class Followers(val href: String,
                     val total: Int)

data class ImageObject(val height: Int,
                       val url: String,
                       val width: Int)

class PagingObject<out T>(val href: String,
                          val items: List<T>,
                          val limit: Int,
                          val next: String,
                          val offset: Int,
                          val previous: String,
                          val total: Int)


data class TrackLink(val externalUrls: ExternalUrl,
                     val href: String,
                     val id: String,
                     val type: String,
                     val uri: String)



data class CopyrightObject(private val text: String,
                           private val type: String)

class PlayHistoryObject(val track: Track.Simplified,
                        @JsonProperty("played_at") val playedAt: String,
                        val context: ContextObject)

class ContextObject(val type: String,
                    val href: String,
                    @JsonProperty("external_urls") val externalUrls: ExternalUrl,
                    val uri: String)