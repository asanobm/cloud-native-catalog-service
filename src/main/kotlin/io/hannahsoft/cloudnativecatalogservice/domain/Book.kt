package io.hannahsoft.cloudnativecatalogservice.domain

import lombok.Data

@Data
data class Book(
  val isbn: String,
  val title: String,
  val author: String,
  val price: Double
)
