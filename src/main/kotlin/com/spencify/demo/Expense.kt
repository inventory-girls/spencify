package com.spencify.demo

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


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

@Repository
class ExpenseDao {
    private val url = "jdbc:mysql://localhost:3306/yourDatabaseName"
    private val username = "yourDatabaseUsername"
    private val password = "yourDatabasePassword"

    fun getAllExpenses(): List<Expense> {
        val sql = "SELECT * FROM expenses"
        val resultSet = executeQuery(sql)
        val expenses = mutableListOf<Expense>()
        while (resultSet.next()) {
            expenses.add(
                Expense(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("amount"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getBoolean("isPaid"),
                    resultSet.getString("date"),
                    resultSet.getString("image")
                )
            )
        }
        return expenses
    }

    fun getExpensesByCategory(category: String): List<Expense> {
        val sql = "SELECT * FROM expenses WHERE category = '$category'"
        val resultSet = executeQuery(sql)
        val expenses = mutableListOf<Expense>()
        while (resultSet.next()) {
            expenses.add(
                Expense(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("amount"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getBoolean("isPaid"),
                    resultSet.getString("date"),
                    resultSet.getString("image")
                )
            )
        }
        return expenses
    }


    fun addExpense(expense: Expense) {
        val sql = "INSERT INTO expenses (title, amount, description, category, isPaid, date, image) VALUES ('${expense.title}', " +
                "'${expense.amount}', " +
                "'${expense.description}', " +
                "'${expense.category}', " +
                "'${expense.isPaid}', " +
                "'${expense.date}', " +
                "'${expense.image}')"
        executeUpdate(sql)
    }

    fun getExpense(id: Int): Expense? {
        val sql = "SELECT * FROM expenses WHERE id = $id"
        val resultSet = executeQuery(sql)
        if (resultSet.next()) {
            return Expense(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getInt("amount"),
                resultSet.getString("description"),
                resultSet.getString("category"),
                resultSet.getBoolean("isPaid"),
                resultSet.getString("date"),
                resultSet.getString("image")
            )
        }
        return null
    }

    fun updateExpense(expense: Expense) {
        val sql = "UPDATE expenses SET title = '${expense.title}', " +
                "amount = '${expense.amount}', " +
                "description = '${expense.description}', " +
                "category = '${expense.category}', " +
                "isPaid = '${expense.isPaid}', " +
                "date = '${expense.date}', " +
                "image = '${expense.image}' " +
                "WHERE id = ${expense.id}"
        executeUpdate(sql)
    }

    fun deleteExpense(id: Int) {
        val sql = "DELETE FROM expenses WHERE id = $id"
        executeUpdate(sql)
    }

    private fun executeUpdate(sql: String) {
        val connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement()
        statement.executeUpdate(sql)
        statement.close()
        connection.close()
    }

    private fun executeQuery(sql: String): ResultSet {
        val connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(sql)
        connection.close()
        return resultSet
    }
}

