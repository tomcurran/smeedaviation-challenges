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

import org.openapitools.client.models.DetailedGearAllOf
import org.openapitools.client.models.SummaryGear

import com.squareup.moshi.Json
/**
 * 
 * @param id The gear's unique identifier.
 * @param resourceState Resource state, indicates level of detail. Possible values: 2 -> \"summary\", 3 -> \"detail\"
 * @param primary Whether this gear's is the owner's default one.
 * @param name The gear's name.
 * @param distance The distance logged with this gear.
 * @param brandName The gear's brand name.
 * @param modelName The gear's model name.
 * @param frameType The gear's frame type (bike only).
 * @param description The gear's description.
 */

data class DetailedGear (
    /* The gear's unique identifier. */
    @Json(name = "id")
    val id: kotlin.String? = null,
    /* Resource state, indicates level of detail. Possible values: 2 -> \"summary\", 3 -> \"detail\" */
    @Json(name = "resource_state")
    val resourceState: kotlin.Int? = null,
    /* Whether this gear's is the owner's default one. */
    @Json(name = "primary")
    val primary: kotlin.Boolean? = null,
    /* The gear's name. */
    @Json(name = "name")
    val name: kotlin.String? = null,
    /* The distance logged with this gear. */
    @Json(name = "distance")
    val distance: kotlin.Float? = null,
    /* The gear's brand name. */
    @Json(name = "brand_name")
    val brandName: kotlin.String? = null,
    /* The gear's model name. */
    @Json(name = "model_name")
    val modelName: kotlin.String? = null,
    /* The gear's frame type (bike only). */
    @Json(name = "frame_type")
    val frameType: kotlin.Int? = null,
    /* The gear's description. */
    @Json(name = "description")
    val description: kotlin.String? = null
)

