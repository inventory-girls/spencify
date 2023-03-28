package controller


import model.Expense
import model.ExpenseDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
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

