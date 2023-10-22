package com.kenkoro.retrofit2.feature_http.domain.model

data class User(
  val id: Int,
  val userName: String,
  val email: String,
  val firstName: String,
  val lastName: String,
  val gender: String,
  val image: String,
  val token: String
)
