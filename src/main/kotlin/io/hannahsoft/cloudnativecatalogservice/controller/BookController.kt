package io.hannahsoft.cloudnativecatalogservice.controller

import io.hannahsoft.cloudnativecatalogservice.domain.Book
import io.hannahsoft.cloudnativecatalogservice.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

  @GetMapping

  fun get(): Iterable<Book> {
    return bookService.viewBookList()
  }

  @GetMapping("{isbn}")
  fun get(@PathVariable isbn: String): Book {
    return bookService.viewBookDetails(isbn)
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun post(@RequestBody book: Book): Book {
    return bookService.addBookToCatalog(book)
  }

  @DeleteMapping("{isbn}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable isbn: String) {
    bookService.removeBookFromCatalog(isbn)
  }

  @PatchMapping("{isbn}")
  fun patch(@PathVariable isbn: String, @RequestBody book: Book): Book {
    return bookService.updateBookDetails(isbn, book)
  }
}