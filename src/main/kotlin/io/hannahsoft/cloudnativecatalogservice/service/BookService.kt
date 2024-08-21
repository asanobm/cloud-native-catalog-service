package io.hannahsoft.cloudnativecatalogservice.service

import io.hannahsoft.cloudnativecatalogservice.domain.Book
import io.hannahsoft.cloudnativecatalogservice.domain.BookRepository
import io.hannahsoft.cloudnativecatalogservice.exception.BookAlreadyExistsException
import io.hannahsoft.cloudnativecatalogservice.exception.BookNotFoundException
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

  fun viewBookList() = bookRepository.findAll()

  fun viewBookDetails(isbn: String): Book {
    return bookRepository.findByIsbn(isbn) ?: throw BookNotFoundException(isbn)
  }

  fun addBookToCatalog(book: Book): Book {
    // Check if book already exists
    if (bookRepository.existsByIsbn(book.isbn)) {
      throw BookAlreadyExistsException(book.isbn)
    }
    return bookRepository.save(book)
  }

  fun removeBookFromCatalog(isbn: String) {
    if (!bookRepository.existsByIsbn(isbn)) {
      throw BookNotFoundException(isbn)
    }
    bookRepository.deleteByIsbn(isbn)
  }

  fun updateBookDetails(isbn: String, book: Book): Book {
    return bookRepository.findByIsbn(isbn)?.let {
      bookRepository.save(book)
    } ?: addBookToCatalog(book)
  }
}