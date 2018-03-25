package com.example.spotifywebapi.models

import com.fasterxml.jackson.annotation.JsonProperty


sealed class Album(@JsonProperty("album_group") val albumGroup: String?,
                @JsonProperty("album_type") val albumType: String,
                 val artists : List<Artist.Simplified>,
                 @JsonProperty("available_markets") val availableMarkets: List<String>,
                 @JsonProperty("external_urls") val externalUrls: ExternalUrl,
                 val href: String,
                 val id: String,
                 val image: ImageObject,
                 val label: String,
                 val name: String,
                 @JsonProperty("release_date") val releaseDate: String,
                 @JsonProperty("release_date_precision") val releaseDatePrecision: String,
                 val type: String,
                 val uri: String) {

    class Simplified(albumGroup: String?,
                     albumType: String,
                     artists : List<Artist.Simplified>,
                     availableMarkets: List<String>,
                     externalUrls: ExternalUrl,
                     href: String,
                     id: String,
                     image: ImageObject,
                     label: String,
                     name: String,
                     releaseDate: String,
                     releaseDatePrecision: String,
                     type: String,
                     uri: String) : Album(albumGroup ,albumType, artists, availableMarkets, externalUrls, href, id, image, label, name, releaseDate, releaseDatePrecision, type, uri)

    class Full(albumGroup: String?,
            albumType: String,
                 artists : List<Artist.Simplified>,
                 availableMarkets: List<String>,
                 val copyrights: List<CopyrightObject>,
                 val externalIds: ExternalId,
                 externalUrls: ExternalUrl,
                 val genres: List<String>,
                 href: String,
                 id: String,
                 image: ImageObject,
                 label: String,
                 name: String,
                 val popularity: Int,
                 releaseDate: String,
                 releaseDatePrecision: String,
                 val tracks: PagingObject<Track.Simplified>,
                 type: String,
                 uri: String) : Album(albumGroup, albumType, artists, availableMarkets, externalUrls, href, id, image, label, name, releaseDate, releaseDatePrecision, type, uri)
    }