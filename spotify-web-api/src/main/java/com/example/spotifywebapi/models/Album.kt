package com.example.spotifywebapi.models

import com.example.spotifywebapi.webinterface.*
import com.fasterxml.jackson.annotation.JsonProperty

data class Album(@JsonProperty("album_type") val albumType: String,
                 val artists : List<Artist>,
                 @JsonProperty("available_markets") val availableMarkets: List<String>,
                 val copyrights: List<CopyrightObject>,
                 @JsonProperty("external_ids") val externalIds: ExternalId,
                 @JsonProperty("external_urls") val externalUrls: ExternalUrl,
                 val genres: List<String>,
                 val href: String,
                 val id: String,
                 val image: ImageObject,
                 val label: String,
                 val name: String,
                 val popularity: Int,
                 @JsonProperty("release_date") val releaseDate: String,
                 @JsonProperty("release_date_precision") val releaseDatePrecision: String,
                 val tracks: List<Track>,
                 val type: String,
                 val uri: String)