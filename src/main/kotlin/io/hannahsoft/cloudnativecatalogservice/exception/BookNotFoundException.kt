package io.hannahsoft.cloudnativecatalogservice.exception

class BookNotFoundException: RuntimeException {
    constructor(isbn: String): super("Book with ISBN $isbn not found")
}