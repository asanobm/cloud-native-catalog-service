package io.hannahsoft.cloudnativecatalogservice.domain

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import lombok.Data

data class Book(
  @get:NotNull(message = "ISBN is required")
  @get:Pattern(regexp = "[0-9]{10}|[0-9]{13}", message = "ISBN must be a valid ISBN-10 or ISBN-13")
  val isbn: String,
  @get:NotNull(message = "Title is required")
  val title: String,
  @get:NotNull(message = "Author is required")
  val author: String,
  @get:NotNull(message = "Publisher is required")
  @get:Positive(message = "Publisher must be a positive number")
  val price: Double
)
