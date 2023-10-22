package com.kenkoro.retrofit2.feature_http.data.repository

import com.kenkoro.retrofit2.feature_http.data.source.DummyProductApi
import com.kenkoro.retrofit2.feature_http.domain.model.Product
import com.kenkoro.retrofit2.feature_http.domain.repository.Repository

class RepositoryImpl(
  private val api: DummyProductApi
) : Repository {
  override suspend fun getProductById(id: Int): Product {
    return api.getProductById(id)
  }
}