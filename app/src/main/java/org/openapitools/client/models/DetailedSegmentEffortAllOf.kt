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

import org.openapitools.client.models.MetaActivity
import org.openapitools.client.models.MetaAthlete
import org.openapitools.client.models.SummarySegment

import com.squareup.moshi.Json
/**
 * 
 * @param name The name of the segment on which this effort was performed
 * @param activity 
 * @param athlete 
 * @param movingTime The effort's moving time
 * @param startIndex The start index of this effort in its activity's stream
 * @param endIndex The end index of this effort in its activity's stream
 * @param averageCadence The effort's average cadence
 * @param averageWatts The average wattage of this effort
 * @param deviceWatts For riding efforts, whether the wattage was reported by a dedicated recording device
 * @param averageHeartrate The heart heart rate of the athlete during this effort
 * @param maxHeartrate The maximum heart rate of the athlete during this effort
 * @param segment 
 * @param komRank The rank of the effort on the global leaderboard if it belongs in the top 10 at the time of upload
 * @param prRank The rank of the effort on the athlete's leaderboard if it belongs in the top 3 at the time of upload
 * @param hidden Whether this effort should be hidden when viewed within an activity
 */

data class DetailedSegmentEffortAllOf (
    /* The name of the segment on which this effort was performed */
    @Json(name = "name")
    val name: kotlin.String? = null,
    @Json(name = "activity")
    val activity: MetaActivity? = null,
    @Json(name = "athlete")
    val athlete: MetaAthlete? = null,
    /* The effort's moving time */
    @Json(name = "moving_time")
    val movingTime: kotlin.Int? = null,
    /* The start index of this effort in its activity's stream */
    @Json(name = "start_index")
    val startIndex: kotlin.Int? = null,
    /* The end index of this effort in its activity's stream */
    @Json(name = "end_index")
    val endIndex: kotlin.Int? = null,
    /* The effort's average cadence */
    @Json(name = "average_cadence")
    val averageCadence: kotlin.Float? = null,
    /* The average wattage of this effort */
    @Json(name = "average_watts")
    val averageWatts: kotlin.Float? = null,
    /* For riding efforts, whether the wattage was reported by a dedicated recording device */
    @Json(name = "device_watts")
    val deviceWatts: kotlin.Boolean? = null,
    /* The heart heart rate of the athlete during this effort */
    @Json(name = "average_heartrate")
    val averageHeartrate: kotlin.Float? = null,
    /* The maximum heart rate of the athlete during this effort */
    @Json(name = "max_heartrate")
    val maxHeartrate: kotlin.Float? = null,
    @Json(name = "segment")
    val segment: SummarySegment? = null,
    /* The rank of the effort on the global leaderboard if it belongs in the top 10 at the time of upload */
    @Json(name = "kom_rank")
    val komRank: kotlin.Int? = null,
    /* The rank of the effort on the athlete's leaderboard if it belongs in the top 3 at the time of upload */
    @Json(name = "pr_rank")
    val prRank: kotlin.Int? = null,
    /* Whether this effort should be hidden when viewed within an activity */
    @Json(name = "hidden")
    val hidden: kotlin.Boolean? = null
)

