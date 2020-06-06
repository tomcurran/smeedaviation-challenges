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
package io.swagger.client.apis

import io.swagger.client.models.ActivityStats
import io.swagger.client.models.DetailedAthlete
import io.swagger.client.models.Fault
import io.swagger.client.models.Zones

import io.swagger.client.infrastructure.*

class AthletesApi(basePath: kotlin.String = "https://www.strava.com/api/v3") : ApiClient(basePath) {

    /**
     * Get Authenticated Athlete
     * Returns the currently authenticated athlete. Tokens with profile:read_all scope will receive a detailed athlete representation; all others will receive a summary representation.
     * @return DetailedAthlete
     */
    @Suppress("UNCHECKED_CAST")
    fun getLoggedInAthlete(): DetailedAthlete {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/athlete"
        )
        val response = request<DetailedAthlete>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as DetailedAthlete
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get Zones
     * Returns the the authenticated athlete&#x27;s heart rate and power zones. Requires profile:read_all.
     * @return Zones
     */
    @Suppress("UNCHECKED_CAST")
    fun getLoggedInAthleteZones(): Zones {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/athlete/zones"
        )
        val response = request<Zones>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Zones
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Get Athlete Stats
     * Returns the activity stats of an athlete. Only includes data from activities set to Everyone visibilty.
     * @param id The identifier of the athlete. Must match the authenticated athlete. 
     * @return ActivityStats
     */
    @Suppress("UNCHECKED_CAST")
    fun getStats(id: kotlin.Int): ActivityStats {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/athletes/{id}/stats".replace("{" + "id" + "}", "$id")
        )
        val response = request<ActivityStats>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ActivityStats
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * Update Athlete
     * Update the currently authenticated athlete. Requires profile:write scope.
     * @param weight The weight of the athlete in kilograms. 
     * @return DetailedAthlete
     */
    @Suppress("UNCHECKED_CAST")
    fun updateLoggedInAthlete(weight: kotlin.Float): DetailedAthlete {
        
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/athlete".replace("{" + "weight" + "}", "$weight")
        )
        val response = request<DetailedAthlete>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as DetailedAthlete
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
