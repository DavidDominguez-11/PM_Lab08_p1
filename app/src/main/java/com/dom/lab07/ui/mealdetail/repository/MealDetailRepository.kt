package com.dom.lab07.ui.mealdetail.repository

import com.dom.lab07.networking.MealsWebService
import com.dom.lab07.networking.responce.lookup_D
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailRepository(private val webService: MealsWebService= MealsWebService()) {
    fun getMealById(mealId: String, successCallback: (response: lookup_D?) -> Unit){
        return webService.getMealById(mealId).enqueue(object: Callback<lookup_D>{
            override fun onResponse(
                call: Call<lookup_D>,
                response: Response<lookup_D>
            ){
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<lookup_D>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}