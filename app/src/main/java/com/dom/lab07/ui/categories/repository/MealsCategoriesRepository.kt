package com.dom.lab07.ui.categories.repository

import com.dom.lab07.networking.MealsWebService
import com.dom.lab07.networking.responce.categories_D
import com.dom.lab07.database.categories.MealCategoryDao
import com.dom.lab07.database.categories.MealCategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsCategoriesRepository(
    private val webService: MealsWebService = MealsWebService(),
    private val mealCategoryDao: MealCategoryDao
) {
    // Método para obtener las categorías de la base de datos o sincronizar con la API si es necesario
    suspend fun getMealCategories(): List<MealCategoryEntity> {
        return withContext(Dispatchers.IO) {
            val cachedCategories = mealCategoryDao.getAllMealCategories()
            if (cachedCategories.isNotEmpty()) {
                // Devuelve las categorías almacenadas en la base de datos
                cachedCategories
            } else {
                // Si la base de datos está vacía, sincroniza con la API
                val response = webService.getMealCategories().execute()
                if (response.isSuccessful) {
                    response.body()?.let { categoriesData ->
                        // Guarda los datos en Room
                        val entities = categoriesData.toEntities()
                        mealCategoryDao.insertAll(entities)
                        entities
                    } ?: emptyList()
                } else {
                    emptyList()
                }
            }
        }
    }

    // Convierte los datos de la API a entidades de Room
    private fun categories_D.toEntities(): List<MealCategoryEntity> {
        return categories.map { category ->
            MealCategoryEntity(
                id = category.idCategory,
                name = category.strCategory,
                imageUrl = category.strCategoryThumb,
                description = category.strCategoryDescription
            )
        }
    }
}
