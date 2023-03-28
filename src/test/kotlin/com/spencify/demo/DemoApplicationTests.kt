package com.spencify.demo

import com.spencify.demo.Expense
import io.restassured.RestAssured.*
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.boot.test.web.server.LocalServerPort
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.*
import com.google.gson.Gson
import java.util.*



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

class MyHttpRequestTests(@LocalServerPort val port: Int) {

	@Test
	fun `test GET request`() {
//        fun greaterThan(number: Int, value: Int): Boolean {
//            return value > number
//        }
		given()
			.port(port)
			.get("/")
			.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
	}

	@Test
	fun `test GET expenses by category`() {
		given()
			.port(port)
			.get("/expenses?category=unpaid")
			.then()
			.statusCode(200)
			.body("size()", equalTo(1))
			.body("[0].category", equalTo("Rent"))

		// get paid expenses
		given()
			.port(port)
			.get("/expenses?category=paid")
			.then()
			.statusCode(200)
			.body("size()", equalTo(2))
			.body("[0].category", equalTo("food"))
			.body("[1].category", equalTo("Gas"))

		// get expenses from current date
		given()
			.port(port)
			.get("/expenses?category=current")
			.then()
			.statusCode(200)
			.body("size()", equalTo(3))
	}

}