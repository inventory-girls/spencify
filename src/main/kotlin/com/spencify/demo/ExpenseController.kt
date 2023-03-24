package com.spencify.demo

import org.springframework.web.bind.annotation.*

data class AllExpenses (
    val id: Int,
    val amount: Int,
    val description: String,
    val category: String,
    val image: String

)
data class NewExpense(
    val id : Int,
    val amount: Int,
    val description: String,
    val category: String,
    val image: String,
//    val date: Int
)

@RestController
@RequestMapping("/expenses")
class ExpenseController {
    @GetMapping("/")
    fun getAllExpenses(): Iterable<AllExpenses> =
        listOf(
            AllExpenses(
                id = 1,
                amount = 34,
                description = "Food for a client",
                category = "Food",
                image = "https://en.wikipedia.org/wiki/Receipt"
            )
        )


    @PostMapping("/added/{id}")
    fun create(@PathVariable id: Int, @RequestBody request: NewExpense): AllExpenses = AllExpenses(
        id = request.id,
        amount = request.amount,
        description = request.description,
        category = request.category,
        image = request.image
    )
}