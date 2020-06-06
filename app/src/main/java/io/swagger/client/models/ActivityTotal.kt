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


/**
 * A roll-up of metrics pertaining to a set of activities. Values are in seconds and meters.
 * @param count The number of activities considered in this total.
 * @param distance The total distance covered by the considered activities.
 * @param movingTime The total moving time of the considered activities.
 * @param elapsedTime The total elapsed time of the considered activities.
 * @param elevationGain The total elevation gain of the considered activities.
 * @param achievementCount The total number of achievements of the considered activities.
 */
data class ActivityTotal (

    /* The number of activities considered in this total. */
    val count: kotlin.Int? = null,
    /* The total distance covered by the considered activities. */
    val distance: kotlin.Float? = null,
    /* The total moving time of the considered activities. */
    val movingTime: kotlin.Int? = null,
    /* The total elapsed time of the considered activities. */
    val elapsedTime: kotlin.Int? = null,
    /* The total elevation gain of the considered activities. */
    val elevationGain: kotlin.Float? = null,
    /* The total number of achievements of the considered activities. */
    val achievementCount: kotlin.Int? = null
) {
}