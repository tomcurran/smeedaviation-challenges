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

import io.swagger.client.models.BaseStream

/**
 * 
 * @param &#x60;data&#x60; The sequence of altitude values for this stream, in meters
 */
data class AltitudeStream (

    /* The sequence of altitude values for this stream, in meters */
    val `data`: kotlin.Array<kotlin.Float>? = null,
    /* The number of data points in this stream */
    val originalSize: kotlin.Int? = null,
    /* The level of detail (sampling) in which this stream was returned */
    val resolution: AltitudeStream.Resolution? = null,
    /* The base series used in the case the stream was downsampled */
    val seriesType: AltitudeStream.SeriesType? = null,
    /* The sequence of altitude values for this stream, in meters */
    val `data`: kotlin.Array<kotlin.Float>? = null
) {
}