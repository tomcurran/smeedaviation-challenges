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

import org.openapitools.client.models.ActivityTotal

import com.squareup.moshi.Json
/**
 * A set of rolled-up statistics and totals for an athlete
 * @param biggestRideDistance The longest distance ridden by the athlete.
 * @param biggestClimbElevationGain The highest climb ridden by the athlete.
 * @param recentRideTotals 
 * @param recentRunTotals 
 * @param recentSwimTotals 
 * @param ytdRideTotals 
 * @param ytdRunTotals 
 * @param ytdSwimTotals 
 * @param allRideTotals 
 * @param allRunTotals 
 * @param allSwimTotals 
 */

data class ActivityStats (
    /* The longest distance ridden by the athlete. */
    @Json(name = "biggest_ride_distance")
    val biggestRideDistance: kotlin.Double? = null,
    /* The highest climb ridden by the athlete. */
    @Json(name = "biggest_climb_elevation_gain")
    val biggestClimbElevationGain: kotlin.Double? = null,
    @Json(name = "recent_ride_totals")
    val recentRideTotals: ActivityTotal? = null,
    @Json(name = "recent_run_totals")
    val recentRunTotals: ActivityTotal? = null,
    @Json(name = "recent_swim_totals")
    val recentSwimTotals: ActivityTotal? = null,
    @Json(name = "ytd_ride_totals")
    val ytdRideTotals: ActivityTotal? = null,
    @Json(name = "ytd_run_totals")
    val ytdRunTotals: ActivityTotal? = null,
    @Json(name = "ytd_swim_totals")
    val ytdSwimTotals: ActivityTotal? = null,
    @Json(name = "all_ride_totals")
    val allRideTotals: ActivityTotal? = null,
    @Json(name = "all_run_totals")
    val allRunTotals: ActivityTotal? = null,
    @Json(name = "all_swim_totals")
    val allSwimTotals: ActivityTotal? = null
)

