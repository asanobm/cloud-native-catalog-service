package io.hannahsoft.cloudnativecatalogservice.persistence

import io.hannahsoft.cloudnativecatalogservice.domain.Book
import io.hannahsoft.cloudnativecatalogservice.domain.BookRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryBookRepository: BookRepository {
  private val books = mutableMapOf<String, Book>()
  override fun findAll(): Iterable<Book> {
    return books.values
  }

  override fun findByIsbn(isbn: String): Book? {
    return if (existsByIsbn(isbn)) books[isbn] else null
  }

  override fun existsByIsbn(isbn: String): Boolean {
    return books.containsKey(isbn)
  }

  override fun save(book: Book): Book {
    books[book.isbn] = book
    return book
  }

  override fun deleteByIsbn(isbn: String) {
    books.remove(isbn)
  }
}