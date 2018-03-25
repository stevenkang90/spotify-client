package com.example.spotifywebapi.models

import com.fasterxml.jackson.annotation.JsonProperty


sealed class Track(val artists: List<Artist.Simplified>,
                   @JsonProperty("available_markets") val availableMarkets: List<String>,
                   @JsonProperty("disc_number") val discNumber: Int,
                   @JsonProperty("duration_ms") val durationMs: Int,
                   val explicit: Boolean,
                   @JsonProperty("external_urls") val externalUrl: ExternalUrl,
                   val href: String,
                   val id: String,
                   @JsonProperty("is_playable") val isPlayable: Boolean,
                   @JsonProperty("linked_form") val linkedFrom: TrackLink,
                   val name: String,
                   @JsonProperty("preview_url") val previewUrl: String,
                   @JsonProperty("track_number") val trackNumber: Int,
                   val type: String,
                   val uri: String) {

    class Simplified(artists: List<Artist.Simplified>,
                     @JsonProperty("available_markets") availableMarkets: List<String>,
                     @JsonProperty("disc_number") discNumber: Int,
                     @JsonProperty("duration_ms") durationMs: Int,
                     explicit: Boolean,
                     @JsonProperty("external_urls") externalUrl: ExternalUrl,
                     href: String,
                     id: String,
                     @JsonProperty("is_playable") isPlayable: Boolean,
                     @JsonProperty("linked_form") linkedFrom: TrackLink,
                     name: String,
                     @JsonProperty("preview_url") previewUrl: String,
                     @JsonProperty("track_number") trackNumber: Int,
                     type: String,
                     uri: String) : Track(artists, availableMarkets, discNumber, durationMs, explicit, externalUrl, href, id, isPlayable, linkedFrom, name, previewUrl, trackNumber, type, uri)

    class Full(val album: Album.Simplified,
               artists: List<Artist.Simplified>,
               @JsonProperty("available_markets") availableMarkets: List<String>,
               @JsonProperty("disc_number") discNumber: Int,
               @JsonProperty("duration_ms") durationMs: Int,
               explicit: Boolean,
               @JsonProperty("external_ids") val externalId: ExternalId,
               @JsonProperty("external_urls") externalUrl: ExternalUrl,
               href: String,
               id: String,
               @JsonProperty("is_playable") isPlayable: Boolean,
               @JsonProperty("linked_form") linkedFrom: TrackLink,
               restrictions: Any,
               name: String,
               val popularity: Int,
               @JsonProperty("preview_url") previewUrl: String,
               @JsonProperty("track_number") trackNumber: Int,
               type: String,
               uri: String) : Track(artists, availableMarkets, discNumber, durationMs, explicit, externalUrl, href, id, isPlayable, linkedFrom, name, previewUrl, trackNumber, type, uri)
}