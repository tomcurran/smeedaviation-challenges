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

import org.openapitools.client.models.Error

import com.squareup.moshi.Json
/**
 * Encapsulates the errors that may be returned from the API.
 * @param errors The set of specific errors associated with this fault, if any.
 * @param message The message of the fault.
 */

data class Fault (
    /* The set of specific errors associated with this fault, if any. */
    @Json(name = "errors")
    val errors: kotlin.Array<Error>? = null,
    /* The message of the fault. */
    @Json(name = "message")
    val message: kotlin.String? = null
)

