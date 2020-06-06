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
 * An enumeration of the types an activity may have.
 * Values: alpineSki,backcountrySki,canoeing,crossfit,eBikeRide,elliptical,golf,handcycle,hike,iceSkate,inlineSkate,kayaking,kitesurf,nordicSki,ride,rockClimbing,rollerSki,rowing,run,sail,skateboard,snowboard,snowshoe,soccer,stairStepper,standUpPaddling,surfing,swim,velomobile,virtualRide,virtualRun,walk,weightTraining,wheelchair,windsurf,workout,yoga
 */
enum class ActivityType(val value: kotlin.String){
    alpineSki("AlpineSki"),// :/
    backcountrySki("BackcountrySki"),// :/
    canoeing("Canoeing"),// :/
    crossfit("Crossfit"),// :/
    eBikeRide("EBikeRide"),// :/
    elliptical("Elliptical"),// :/
    golf("Golf"),// :/
    handcycle("Handcycle"),// :/
    hike("Hike"),// :/
    iceSkate("IceSkate"),// :/
    inlineSkate("InlineSkate"),// :/
    kayaking("Kayaking"),// :/
    kitesurf("Kitesurf"),// :/
    nordicSki("NordicSki"),// :/
    ride("Ride"),// :/
    rockClimbing("RockClimbing"),// :/
    rollerSki("RollerSki"),// :/
    rowing("Rowing"),// :/
    run("Run"),// :/
    sail("Sail"),// :/
    skateboard("Skateboard"),// :/
    snowboard("Snowboard"),// :/
    snowshoe("Snowshoe"),// :/
    soccer("Soccer"),// :/
    stairStepper("StairStepper"),// :/
    standUpPaddling("StandUpPaddling"),// :/
    surfing("Surfing"),// :/
    swim("Swim"),// :/
    velomobile("Velomobile"),// :/
    virtualRide("VirtualRide"),// :/
    virtualRun("VirtualRun"),// :/
    walk("Walk"),// :/
    weightTraining("WeightTraining"),// :/
    wheelchair("Wheelchair"),// :/
    windsurf("Windsurf"),// :/
    workout("Workout"),// :/
    yoga("Yoga");// :/
}