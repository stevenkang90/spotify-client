package com.example.spotifywebapi.models

import com.fasterxml.jackson.annotation.JsonProperty

sealed class Artist(@JsonProperty("external_urls") val externalUrls: ExternalUrl,
                    val href: String,
                    val id: String,
                    val name: String,
                    val type: String,
                    val uri: String) {

    class Simplified(externalUrls: ExternalUrl,
                     href: String,
                     id: String,
                     name: String,
                     type: String,
                     uri: String) : Artist(externalUrls, href, id, name, type, uri)

    class Full(externalUrls: ExternalUrl,
               val followers: Followers,
               val genres: List<String>,
               href: String,
               id: String,
               val images: List<ImageObject>,
               name: String,
               val popularity: Int,
               type: String,
               uri: String) : Artist(externalUrls, href, id, name, type, uri)
}