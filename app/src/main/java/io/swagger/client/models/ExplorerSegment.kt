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

import io.swagger.client.models.LatLng

/**
 * 
 * @param id The unique identifier of this segment
 * @param name The name of this segment
 * @param climbCategory The category of the climb [0, 5]. Higher is harder ie. 5 is Hors catégorie, 0 is uncategorized in climb_category. If climb_category = 5, climb_category_desc = HC. If climb_category = 2, climb_category_desc = 3.
 * @param climbCategoryDesc The description for the category of the climb
 * @param avgGrade The segment's average grade, in percents
 * @param startLatlng 
 * @param endLatlng 
 * @param elevDifference The segments's evelation difference, in meters
 * @param distance The segment's distance, in meters
 * @param points The polyline of the segment
 */
data class ExplorerSegment (

    /* The unique identifier of this segment */
    val id: kotlin.Long? = null,
    /* The name of this segment */
    val name: kotlin.String? = null,
    /* The category of the climb [0, 5]. Higher is harder ie. 5 is Hors catégorie, 0 is uncategorized in climb_category. If climb_category = 5, climb_category_desc = HC. If climb_category = 2, climb_category_desc = 3. */
    val climbCategory: kotlin.Int? = null,
    /* The description for the category of the climb */
    val climbCategoryDesc: ExplorerSegment.ClimbCategoryDesc? = null,
    /* The segment's average grade, in percents */
    val avgGrade: kotlin.Float? = null,
    val startLatlng: LatLng? = null,
    val endLatlng: LatLng? = null,
    /* The segments's evelation difference, in meters */
    val elevDifference: kotlin.Float? = null,
    /* The segment's distance, in meters */
    val distance: kotlin.Float? = null,
    /* The polyline of the segment */
    val points: kotlin.String? = null
) {
    /**
    * The description for the category of the climb
    * Values: nC,4,3,2,1,hC
    */
    enum class ClimbCategoryDesc(val value: kotlin.String){
        nC("NC"),
        4("4"),
        3("3"),
        2("2"),
        1("1"),
        hC("HC");
    }
}