package com.kenkoro.retrofit2.feature_http.domain.repository

import com.kenkoro.retrofit2.feature_http.domain.model.Product

interface Repository {
  suspend fun getProductById(id: Int): Product
}