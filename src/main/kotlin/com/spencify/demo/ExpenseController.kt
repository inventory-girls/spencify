package com.spencify.demo


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/expenses")
class ExpenseController(private val expenseDao: ExpenseDao) {

    @GetMapping
    fun getAllExpenses(): List<Expense> {
        return expenseDao.getAllExpenses()
    }

    @GetMapping("/{category}")
    fun getExpensesByCategory(@PathVariable category: String): List<Expense> {
        return expenseDao.getExpensesByCategory(category)
    }

    @PostMapping
    fun createExpense(@RequestBody expense: Expense) {
        expenseDao.addExpense(expense)
    }

    @GetMapping("/{id}")
    fun getExpense(@PathVariable id: Int): Expense? {
        return expenseDao.getExpense(id)
    }

    @PutMapping("/{id}")
    fun updateExpense(@PathVariable id: Int, @RequestBody expense: Expense) {
        expenseDao.updateExpense(expense.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deleteExpense(@PathVariable id: Int) {
        expenseDao.deleteExpense(id)
    }
}

import org.springframework.web.bind.annotation.RestController
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
    val date: Int
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


//@Controller
//class ExpenseController {
//
//    @GetMapping("/expenses")
//    fun getAllExpenses(): ResponseEntity<String> {
//        val htmlFilePath = "demo.main/templates/index/index.html"
//        val htmlContent = File(htmlFilePath).readText()
//        return ResponseEntity.ok(htmlContent)
//    }
//}

