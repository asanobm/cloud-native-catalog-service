package io.hannahsoft.cloudnativecatalogservice.controller

import io.hannahsoft.cloudnativecatalogservice.exception.BookAlreadyExistsException
import io.hannahsoft.cloudnativecatalogservice.exception.BookNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BookControllerAdvice {
  @ExceptionHandler(BookNotFoundException::class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  fun handleBookNotFoundException(e: BookNotFoundException): String {
    return e.message!!
  }

  @ExceptionHandler(BookAlreadyExistsException::class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  fun handleBookAlreadyExistsException(e: BookAlreadyExistsException): String {
    return e.message!!
  }

  /**
   * !FIXME: This is not working as expected. The error message is not being returned in the response.
   */
  @ExceptionHandler(MethodArgumentNotValidException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): Map<String, String> {
    var errors = mutableMapOf<String, String>()
    e.bindingResult.fieldErrors.forEach { error ->
      errors[error.field] = error.defaultMessage!!
    }
    return errors
  }

  @ExceptionHandler(HttpMessageNotReadableException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): String {
    return "Invalid request body: " + e.message
  }
}