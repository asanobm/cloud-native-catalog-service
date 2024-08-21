package io.hannahsoft.cloudnativecatalogservice.exception

class BookAlreadyExistsException: RuntimeException {
    constructor(isbn: String): super("Book with ISBN $isbn already exists")
}