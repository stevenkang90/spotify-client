package com.example.spotifywebapi.models

import com.fasterxml.jackson.annotation.JsonProperty


sealed class Playlist(val collaborative: Boolean,
                      @JsonProperty("external_urls") val externalUrl: ExternalUrl,
                      val href: String,
                      val id: String,
                      val images: List<ImageObject>,
                      val name: String,
                      val owner: User.Public,
                      val public: Boolean,
                      @JsonProperty("snapshot_id") val snapshotId: String,
                      val tracks: List<PlaylistTrack>,
                      val type: String,
                      val uri: String) {

    class Simplified(collaborative: Boolean,
                     @JsonProperty("external_urls") externalUrl: ExternalUrl,
                     href: String,
                     id: String,
                     images: List<ImageObject>,
                     name: String,
                     owner: User.Public,
                     public: Boolean,
                     @JsonProperty("snapshot_id") snapshotId: String,
                     tracks: List<PlaylistTrack>,
                     type: String,
                     uri: String): Playlist(collaborative, externalUrl, href, id, images, name, owner, public, snapshotId, tracks, type, uri)

    class Full(collaborative: Boolean,
               val description: String,
               @JsonProperty("external_urls") externalUrl: ExternalUrl,
               val followers: Followers,
               href: String,
               id: String,
               images: List<ImageObject>,
               name: String,
               owner: User.Public,
               public: Boolean,
               @JsonProperty("snapshot_id") snapshotId: String,
               tracks: List<PlaylistTrack>,
               type: String,
               uri: String): Playlist(collaborative, externalUrl, href, id, images, name, owner, public, snapshotId, tracks, type, uri)
}

class PlaylistTrack(//Timestamps are returned in ISO 8601 format as Coordinated Universal Time (UTC) with a zero offset: YYYY-MM-DDTHH:MM:SSZ
                    @JsonProperty("added_at") val addedAt: String,
                    @JsonProperty("added_by") val addedBy: User.Public,
                    @JsonProperty("is_local") val isLocal: Boolean,
                    val track: Track.Simplified)
