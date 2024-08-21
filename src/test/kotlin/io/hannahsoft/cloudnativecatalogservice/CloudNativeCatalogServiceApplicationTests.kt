package io.hannahsoft.cloudnativecatalogservice

import io.hannahsoft.cloudnativecatalogservice.domain.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudNativeCatalogServiceApplicationTests(@Autowired private val webTestClient: WebTestClient) {
  @Test
  fun contextLoads() {
  }

  @Test
  fun `when post request then book created`() {
    val book = Book("1234567890", "Spring Boot Guide", "Spring Boot Guide Description", 123.0)
    webTestClient.post().uri("/books")
      .bodyValue(book)
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("$.isbn").isEqualTo("1234567890")
      .jsonPath("$.title").isEqualTo("Spring Boot Guide")
      .jsonPath("$.author").isEqualTo("Spring Boot Guide Description")
      .jsonPath("$.price").isEqualTo(123.0)
  }



}
