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


import com.squareup.moshi.Json

/**
* An enumeration of the types an activity may have.
* Values: alpineSki,backcountrySki,canoeing,crossfit,eBikeRide,elliptical,golf,handcycle,hike,iceSkate,inlineSkate,kayaking,kitesurf,nordicSki,ride,rockClimbing,rollerSki,rowing,run,sail,skateboard,snowboard,snowshoe,soccer,stairStepper,standUpPaddling,surfing,swim,velomobile,virtualRide,virtualRun,walk,weightTraining,wheelchair,windsurf,workout,yoga
*/

enum class ActivityType(val value: kotlin.String){


    @Json(name = "AlpineSki")
    alpineSki("AlpineSki"),


    @Json(name = "BackcountrySki")
    backcountrySki("BackcountrySki"),


    @Json(name = "Canoeing")
    canoeing("Canoeing"),


    @Json(name = "Crossfit")
    crossfit("Crossfit"),


    @Json(name = "EBikeRide")
    eBikeRide("EBikeRide"),


    @Json(name = "Elliptical")
    elliptical("Elliptical"),


    @Json(name = "Golf")
    golf("Golf"),


    @Json(name = "Handcycle")
    handcycle("Handcycle"),


    @Json(name = "Hike")
    hike("Hike"),


    @Json(name = "IceSkate")
    iceSkate("IceSkate"),


    @Json(name = "InlineSkate")
    inlineSkate("InlineSkate"),


    @Json(name = "Kayaking")
    kayaking("Kayaking"),


    @Json(name = "Kitesurf")
    kitesurf("Kitesurf"),


    @Json(name = "NordicSki")
    nordicSki("NordicSki"),


    @Json(name = "Ride")
    ride("Ride"),


    @Json(name = "RockClimbing")
    rockClimbing("RockClimbing"),


    @Json(name = "RollerSki")
    rollerSki("RollerSki"),


    @Json(name = "Rowing")
    rowing("Rowing"),


    @Json(name = "Run")
    run("Run"),


    @Json(name = "Sail")
    sail("Sail"),


    @Json(name = "Skateboard")
    skateboard("Skateboard"),


    @Json(name = "Snowboard")
    snowboard("Snowboard"),


    @Json(name = "Snowshoe")
    snowshoe("Snowshoe"),


    @Json(name = "Soccer")
    soccer("Soccer"),


    @Json(name = "StairStepper")
    stairStepper("StairStepper"),


    @Json(name = "StandUpPaddling")
    standUpPaddling("StandUpPaddling"),


    @Json(name = "Surfing")
    surfing("Surfing"),


    @Json(name = "Swim")
    swim("Swim"),


    @Json(name = "Velomobile")
    velomobile("Velomobile"),


    @Json(name = "VirtualRide")
    virtualRide("VirtualRide"),


    @Json(name = "VirtualRun")
    virtualRun("VirtualRun"),


    @Json(name = "Walk")
    walk("Walk"),


    @Json(name = "WeightTraining")
    weightTraining("WeightTraining"),


    @Json(name = "Wheelchair")
    wheelchair("Wheelchair"),


    @Json(name = "Windsurf")
    windsurf("Windsurf"),


    @Json(name = "Workout")
    workout("Workout"),


    @Json(name = "Yoga")
    yoga("Yoga");



	/**
	This override toString avoids using the enum var name and uses the actual api value instead.
	In cases the var name and value are different, the client would send incorrect enums to the server.
	**/
	override fun toString(): String {
        return value
    }

}

