package com.example.mercadolibre.data.helpers

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.database.CurrencyEntity
import com.example.mercadolibre.data.entities.database.SearchHistoryEntity
import com.example.mercadolibre.data.entities.dto.ProductDetailDto
import com.example.mercadolibre.data.entities.dto.ProductDto
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto

/**
 * Clase encargada de mapear de una clase a otra para optimizar
 * el envio de datos
 */

fun CurrencyResponse.toCurrency(): CurrencyEntity {
    return CurrencyEntity(
        id = this.id,
        symbol = this.symbol
    )
}

fun ProductDetailResponse.toProductDetailDto(): ProductDetailDto {
    return ProductDetailDto(
        title, price, officialStoreId, availableQuantity, soldQuantity, condition, pictures
    )
}

fun ProductResponse.toProductDto(): ProductDto {
    return ProductDto(
        id, thumbnail, title, price, officialStoreId, availableQuantity, soldQuantity, condition, pictures
    )
}

fun SearchHistoryEntity.toSearchDto(): SearchHistoryDto {
    return SearchHistoryDto(text)
}

fun SearchHistoryDto.toSearchEntityTest(): SearchHistoryEntity {
    return SearchHistoryEntity(
        id = System.currentTimeMillis().toInt(),
        text = text,
        timestamp = System.currentTimeMillis()
    )
}

fun List<SearchHistoryEntity>.toListOfSearchDto(): List<SearchHistoryDto> {
    return this.map { it.toSearchDto() }
}

fun List<CurrencyResponse>.toListOfCurrency(): List<CurrencyEntity> {
    return this.map { it.toCurrency() }
}
