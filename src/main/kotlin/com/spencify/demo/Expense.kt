package com.spencify.demo

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
//import org.springframework.stereotype.Repository
//import java.sql.DriverManager
//import java.sql.ResultSet
data class Expense(val id: Int,
                   @field:NotBlank(message = "Title must not be blank")
                   val title: String,
                   @field:Positive(message = "Amount must be greater than zero")
                   val amount: Int,
                   @field:Size(max = 300, message = "Description must not exceed 300 characters")
                   val description: String,
                   val category: String,
                   val isPaid: Boolean,
                   val date: String,
                   val image: String )
