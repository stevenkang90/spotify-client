package com.example.spotifywebapi.models

import com.fasterxml.jackson.annotation.JsonProperty


sealed class User(@JsonProperty("display_name") val dislayName: String,
                  @JsonProperty("external_urls") val externalUrl: ExternalUrl,
                  val followers: Followers,
                  val href: String,
                  val id: String,
                  val images: List<ImageObject>,
                  val type: String,
                  val uri: String) {

            class Public(@JsonProperty("display_name") dislayName: String,
                         @JsonProperty("external_urls") externalUrl: ExternalUrl,
                         followers: Followers,
                         href: String,
                         id: String,
                         images: List<ImageObject>,
                         type: String,
                         uri: String) : User(dislayName, externalUrl, followers, href, id, images, type, uri)

            class Private(val birthDate: String?,
                          //The country of the user, as set in the user’s account profile. An ISO 3166-1 alpha-2 country code. This field is only available when the current user has granted access to the user-read-private scope.
                          val country: String?,
                          @JsonProperty("display_name") dislayName: String,
                          val email: String?,
                         @JsonProperty("external_urls") externalUrl: ExternalUrl,
                         followers: Followers,
                         href: String,
                         id: String,
                         images: List<ImageObject>,
                          val product: String?,
                         type: String,
                         uri: String) : User(dislayName, externalUrl, followers, href, id, images, type, uri)
}