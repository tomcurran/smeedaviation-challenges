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

import org.openapitools.client.models.SummaryClub
import org.openapitools.client.models.SummaryGear

import com.squareup.moshi.Json
/**
 * 
 * @param followerCount The athlete's follower count.
 * @param friendCount The athlete's friend count.
 * @param measurementPreference The athlete's preferred unit system.
 * @param ftp The athlete's FTP (Functional Threshold Power).
 * @param weight The athlete's weight.
 * @param clubs The athlete's clubs.
 * @param bikes The athlete's bikes.
 * @param shoes The athlete's shoes.
 */

data class DetailedAthleteAllOf (
    /* The athlete's follower count. */
    @Json(name = "follower_count")
    val followerCount: kotlin.Int? = null,
    /* The athlete's friend count. */
    @Json(name = "friend_count")
    val friendCount: kotlin.Int? = null,
    /* The athlete's preferred unit system. */
    @Json(name = "measurement_preference")
    val measurementPreference: DetailedAthleteAllOf.MeasurementPreference? = null,
    /* The athlete's FTP (Functional Threshold Power). */
    @Json(name = "ftp")
    val ftp: kotlin.Int? = null,
    /* The athlete's weight. */
    @Json(name = "weight")
    val weight: kotlin.Float? = null,
    /* The athlete's clubs. */
    @Json(name = "clubs")
    val clubs: kotlin.Array<SummaryClub>? = null,
    /* The athlete's bikes. */
    @Json(name = "bikes")
    val bikes: kotlin.Array<SummaryGear>? = null,
    /* The athlete's shoes. */
    @Json(name = "shoes")
    val shoes: kotlin.Array<SummaryGear>? = null
) {

    /**
    * The athlete's preferred unit system.
    * Values: feet,meters
    */
    
    enum class MeasurementPreference(val value: kotlin.String){
        @Json(name = "feet") feet("feet"),
        @Json(name = "meters") meters("meters");
    }
}
