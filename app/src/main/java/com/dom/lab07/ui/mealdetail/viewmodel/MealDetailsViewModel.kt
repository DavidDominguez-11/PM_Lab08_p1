package com.dom.lab07.ui.mealdetail.viewmodel

import androidx.lifecycle.ViewModel
import com.dom.lab07.networking.responce.lookup_D
import com.dom.lab07.ui.mealdetail.repository.MealDetailRepository

class MealDetailsViewModel(private val repository: MealDetailRepository = MealDetailRepository()) : ViewModel() {
    fun getMealById(
        mealId: String,
        successCallback: (response: lookup_D?) -> Unit
    ) {
        repository.getMealById(mealId) { response ->
            successCallback(response)
        }
    }
}