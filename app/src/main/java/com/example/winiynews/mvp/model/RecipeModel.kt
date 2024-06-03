package com.example.winiynews.mvp.model

import com.example.winiynews.bean.RecipeBean.RecipeCategoryBean
import com.example.winiynews.bean.RecipeBean.SearchRecipeBean
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

/**
 * @Author winiymissl
 * @Date 2024-06-02 14:53
 * @Version 1.0
 */
class RecipeModel {
    fun getRecipeCategory(id: String): Observable<RecipeCategoryBean> {
        return RetrofitHub.roll_api.getRecipeCategory(category_id = id)
            .compose(SchedulerUtils.ioToMain())
    }

    fun searchRecipe(keyword: String, page: String): Observable<SearchRecipeBean> {
        return RetrofitHub.roll_api.searchRecipe(keyword = keyword, page = page).compose(SchedulerUtils.ioToMain())
    }
}
