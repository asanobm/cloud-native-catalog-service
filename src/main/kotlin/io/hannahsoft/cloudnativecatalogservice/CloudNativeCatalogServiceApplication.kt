package io.hannahsoft.cloudnativecatalogservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudNativeCatalogServiceApplication

fun main(args: Array<String>) {
  runApplication<CloudNativeCatalogServiceApplication>(*args)
}
