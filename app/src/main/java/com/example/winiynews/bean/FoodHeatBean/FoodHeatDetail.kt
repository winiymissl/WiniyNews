package com.example.winiynews.bean.FoodHeatBean

/**
 * @Author winiymissl
 * @Date 2024-06-02 13:04
 * @Version 1.0
 */
data class FoodHeatDetail(
    val code: Int,
    val data: FoodData,
    val msg: String
)

data class FoodData(
    val alcohol: String,
    val alcoholUnit: String,
    val biotin: String,
    val biotinUnit: String,
    val calcium: String,
    val calciumUnit: String,
    val calory: String,
    val caloryUnit: String,
    val carbohydrate: String,
    val carbohydrateUnit: String,
    val carotene: String,
    val caroteneUnit: String,
    val cholesterol: String,
    val cholesterolUnit: String,
    val choline: String,
    val cholineUnit: String,
    val copper: String,
    val copperUnit: String,
    val fat: String,
    val fatUnit: String,
    val fattyAcid: String,
    val fattyAcidUnit: String,
    val fiberDietary: String,
    val fiberDietaryUnit: String,
    val fluorine: String,
    val fluorineUnit: String,
    val folacin: String,
    val folacinUnit: String,
    val foodId: String,
    val glycemicInfoData: GlycemicInfoData,
    val healthLight: Int,
    val healthSuggest: String,
    val healthTips: String,
    val iodine: String,
    val iodineUnit: String,
    val iron: String,
    val ironUnit: String,
    val joule: String,
    val jouleUnit: String,
    val kalium: String,
    val kaliumUnit: String,
    val lactoflavin: String,
    val lactoflavinUnit: String,
    val magnesium: String,
    val magnesiumUnit: String,
    val manganese: String,
    val manganeseUnit: String,
    val mufa: String,
    val mufaUnit: String,
    val name: String,
    val natrium: String,
    val natriumUnit: String,
    val niacin: String,
    val niacinUnit: String,
    val pantothenic: String,
    val pantothenicUnit: String,
    val phosphor: String,
    val phosphorUnit: String,
    val protein: String,
    val proteinUnit: String,
    val pufa: String,
    val pufaUnit: String,
    val saturatedFat: String,
    val saturatedFatUnit: String,
    val selenium: String,
    val seleniumUnit: String,
    val sugar: String,
    val sugarUnit: String,
    val thiamine: String,
    val thiamineUnit: String,
    val vitaminA: String,
    val vitaminAUnit: String,
    val vitaminB12: String,
    val vitaminB12Unit: String,
    val vitaminB6: String,
    val vitaminB6Unit: String,
    val vitaminC: String,
    val vitaminCUnit: String,
    val vitaminD: String,
    val vitaminDUnit: String,
    val vitaminE: String,
    val vitaminEUnit: String,
    val vitaminK: String,
    val vitaminKUnit: String,
    val zinc: String,
    val zincUnit: String
)

data class GlycemicInfoData(
    val gi: Gi,
    val gl: Gl
)

data class Gi(
    val label: String,
    val value: String
)

data class Gl(
    val label: String,
    val value: String
)