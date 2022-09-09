package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.DetailRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
): DetailRepository {

    override suspend fun getProductDetailById(id: String): Resource<ProductDetailResponse> {
        return try {
            Resource.Success(productApi.getProductDetail(id))
        } catch(e: IOException) {
            return Resource.Error(Constants.ERROR_IO_EXCEPTION)
        } catch(e: HttpException) {
            Resource.Error(Constants.ERROR_HTTP_EXCEPTION)
        }
    }

}