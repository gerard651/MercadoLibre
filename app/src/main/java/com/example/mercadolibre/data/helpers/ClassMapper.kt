package com.example.mercadolibre.data.helpers

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.database.CurrencyDb
import com.example.mercadolibre.data.entities.database.SearchHistoryDb
import com.example.mercadolibre.data.entities.dto.ProductDetailDto
import com.example.mercadolibre.data.entities.dto.ProductDto
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto

fun CurrencyResponse.toCurrency(): CurrencyDb {
    return CurrencyDb(
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

fun SearchHistoryDb.toSearchDto(): SearchHistoryDto {
    return SearchHistoryDto(text)
}

fun List<SearchHistoryDb>.toListOfSearchDto(): List<SearchHistoryDto> {
    return this.map { it.toSearchDto() }
}

fun List<CurrencyResponse>.toListOfCurrency(): List<CurrencyDb> {
    return this.map { it.toCurrency() }
}
