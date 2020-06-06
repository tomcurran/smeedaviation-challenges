/**
* Strava API v3
* The [Swagger Playground](https://developers.strava.com/playground) is the easiest way to familiarize yourself with the Strava API by submitting HTTP requests and observing the responses before you write any client code. It will show what a response will look like with different endpoints depending on the authorization scope you receive from your athletes. To use the Playground, go to https://www.strava.com/settings/api and change your “Authorization Callback Domain” to developers.strava.com. Please note, we only support Swagger 2.0. There is a known issue where you can only select one scope at a time. For more information, please check the section “client code” at https://developers.strava.com/docs.
*
* The version of the OpenAPI document: 3.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.client.models

import org.openapitools.client.models.DetailedClubAllOf
import org.openapitools.client.models.SummaryClub

import com.squareup.moshi.Json
/**
 * 
 * @param id The club's unique identifier.
 * @param resourceState Resource state, indicates level of detail. Possible values: 1 -> \"meta\", 2 -> \"summary\", 3 -> \"detail\"
 * @param name The club's name.
 * @param profileMedium URL to a 60x60 pixel profile picture.
 * @param coverPhoto URL to a ~1185x580 pixel cover photo.
 * @param coverPhotoSmall URL to a ~360x176  pixel cover photo.
 * @param sportType 
 * @param city The club's city.
 * @param state The club's state or geographical region.
 * @param country The club's country.
 * @param private Whether the club is private.
 * @param memberCount The club's member count.
 * @param featured Whether the club is featured or not.
 * @param verified Whether the club is verified or not.
 * @param url The club's vanity URL.
 * @param membership The membership status of the logged-in athlete.
 * @param admin Whether the currently logged-in athlete is an administrator of this club.
 * @param owner Whether the currently logged-in athlete is the owner of this club.
 * @param followingCount The number of athletes in the club that the logged-in athlete follows.
 */

data class DetailedClub (
    /* The club's unique identifier. */
    @Json(name = "id")
    val id: kotlin.Long? = null,
    /* Resource state, indicates level of detail. Possible values: 1 -> \"meta\", 2 -> \"summary\", 3 -> \"detail\" */
    @Json(name = "resource_state")
    val resourceState: kotlin.Int? = null,
    /* The club's name. */
    @Json(name = "name")
    val name: kotlin.String? = null,
    /* URL to a 60x60 pixel profile picture. */
    @Json(name = "profile_medium")
    val profileMedium: kotlin.String? = null,
    /* URL to a ~1185x580 pixel cover photo. */
    @Json(name = "cover_photo")
    val coverPhoto: kotlin.String? = null,
    /* URL to a ~360x176  pixel cover photo. */
    @Json(name = "cover_photo_small")
    val coverPhotoSmall: kotlin.String? = null,
    @Json(name = "sport_type")
    val sportType: DetailedClub.SportType? = null,
    /* The club's city. */
    @Json(name = "city")
    val city: kotlin.String? = null,
    /* The club's state or geographical region. */
    @Json(name = "state")
    val state: kotlin.String? = null,
    /* The club's country. */
    @Json(name = "country")
    val country: kotlin.String? = null,
    /* Whether the club is private. */
    @Json(name = "private")
    val private: kotlin.Boolean? = null,
    /* The club's member count. */
    @Json(name = "member_count")
    val memberCount: kotlin.Int? = null,
    /* Whether the club is featured or not. */
    @Json(name = "featured")
    val featured: kotlin.Boolean? = null,
    /* Whether the club is verified or not. */
    @Json(name = "verified")
    val verified: kotlin.Boolean? = null,
    /* The club's vanity URL. */
    @Json(name = "url")
    val url: kotlin.String? = null,
    /* The membership status of the logged-in athlete. */
    @Json(name = "membership")
    val membership: DetailedClub.Membership? = null,
    /* Whether the currently logged-in athlete is an administrator of this club. */
    @Json(name = "admin")
    val admin: kotlin.Boolean? = null,
    /* Whether the currently logged-in athlete is the owner of this club. */
    @Json(name = "owner")
    val owner: kotlin.Boolean? = null,
    /* The number of athletes in the club that the logged-in athlete follows. */
    @Json(name = "following_count")
    val followingCount: kotlin.Int? = null
) {

    /**
    * 
    * Values: cycling,running,triathlon,other
    */
    
    enum class SportType(val value: kotlin.String){
        @Json(name = "cycling") cycling("cycling"),
        @Json(name = "running") running("running"),
        @Json(name = "triathlon") triathlon("triathlon"),
        @Json(name = "other") other("other");
    }
    /**
    * The membership status of the logged-in athlete.
    * Values: member,pending
    */
    
    enum class Membership(val value: kotlin.String){
        @Json(name = "member") member("member"),
        @Json(name = "pending") pending("pending");
    }
}

