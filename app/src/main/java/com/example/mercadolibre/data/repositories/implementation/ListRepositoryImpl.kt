package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.api.SearchApi
import com.example.mercadolibre.data.entities.api.SearchResponse
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.ListRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
): ListRepository {

    override suspend fun getProductsByName(productName: String): Resource<SearchResponse> {
        return try {
            Resource.Success(searchApi.getProductsByName(productName))
        } catch(e: IOException) {
            return Resource.Error(Constants.ERROR_IO_EXCEPTION)
        } catch(e: HttpException) {
            Resource.Error(Constants.ERROR_HTTP_EXCEPTION)
        }
    }

}