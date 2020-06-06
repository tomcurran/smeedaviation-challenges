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

import io.swagger.client.models.MetaActivity
import io.swagger.client.models.MetaAthlete

/**
 * 
 * @param id The unique identifier of this lap
 * @param activity 
 * @param athlete 
 * @param averageCadence The lap's average cadence
 * @param averageSpeed The lap's average speed
 * @param distance The lap's distance, in meters
 * @param elapsedTime The lap's elapsed time, in seconds
 * @param startIndex The start index of this effort in its activity's stream
 * @param endIndex The end index of this effort in its activity's stream
 * @param lapIndex The index of this lap in the activity it belongs to
 * @param maxSpeed The maximum speed of this lat, in meters per second
 * @param movingTime The lap's moving time, in seconds
 * @param name The name of the lap
 * @param paceZone The athlete's pace zone during this lap
 * @param split 
 * @param startDate The time at which the lap was started.
 * @param startDateLocal The time at which the lap was started in the local timezone.
 * @param totalElevationGain The elevation gain of this lap, in meters
 */
data class Lap (

    /* The unique identifier of this lap */
    val id: kotlin.Long? = null,
    val activity: MetaActivity? = null,
    val athlete: MetaAthlete? = null,
    /* The lap's average cadence */
    val averageCadence: kotlin.Float? = null,
    /* The lap's average speed */
    val averageSpeed: kotlin.Float? = null,
    /* The lap's distance, in meters */
    val distance: kotlin.Float? = null,
    /* The lap's elapsed time, in seconds */
    val elapsedTime: kotlin.Int? = null,
    /* The start index of this effort in its activity's stream */
    val startIndex: kotlin.Int? = null,
    /* The end index of this effort in its activity's stream */
    val endIndex: kotlin.Int? = null,
    /* The index of this lap in the activity it belongs to */
    val lapIndex: kotlin.Int? = null,
    /* The maximum speed of this lat, in meters per second */
    val maxSpeed: kotlin.Float? = null,
    /* The lap's moving time, in seconds */
    val movingTime: kotlin.Int? = null,
    /* The name of the lap */
    val name: kotlin.String? = null,
    /* The athlete's pace zone during this lap */
    val paceZone: kotlin.Int? = null,
    val split: kotlin.Int? = null,
    /* The time at which the lap was started. */
    val startDate: java.time.LocalDateTime? = null,
    /* The time at which the lap was started in the local timezone. */
    val startDateLocal: java.time.LocalDateTime? = null,
    /* The elevation gain of this lap, in meters */
    val totalElevationGain: kotlin.Float? = null
) {
}