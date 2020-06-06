/**
 * Strava API v3
 * The [Swagger Playground](https://developers.strava.com/playground) is the easiest way to familiarize yourself with the Strava API by submitting HTTP requests and observing the responses before you write any client code. It will show what a response will look like with different endpoints depending on the authorization scope you receive from your athletes. To use the Playground, go to https://www.strava.com/settings/api and change your “Authorization Callback Domain” to developers.strava.com. Please note, we only support Swagger 2.0. There is a known issue where you can only select one scope at a time. For more information, please check the section “client code” at https://developers.strava.com/docs.
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.models

import io.swagger.client.models.ActivityTotal

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
    val biggestRideDistance: kotlin.Double? = null,
    /* The highest climb ridden by the athlete. */
    val biggestClimbElevationGain: kotlin.Double? = null,
    val recentRideTotals: ActivityTotal? = null,
    val recentRunTotals: ActivityTotal? = null,
    val recentSwimTotals: ActivityTotal? = null,
    val ytdRideTotals: ActivityTotal? = null,
    val ytdRunTotals: ActivityTotal? = null,
    val ytdSwimTotals: ActivityTotal? = null,
    val allRideTotals: ActivityTotal? = null,
    val allRunTotals: ActivityTotal? = null,
    val allSwimTotals: ActivityTotal? = null
) {
}