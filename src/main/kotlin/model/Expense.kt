package model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Entity
@Table(name = "expenses")
class Expense() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @field:NotBlank(message = "Title must not be blank")
    var title: String = ""

    @field:Positive(message = "Amount must be greater than zero")
    var amount: Int = 0

    @field:Size(max = 300, message = "Description must not exceed 300 characters")
    var description: String = ""

    var category: String = ""

    var isPaid: Boolean = false

    var date: String = ""

    var image: String = ""

    constructor(
        id: Long,
        title: String,
        amount: Int,
        description: String,
        category: String,
        isPaid: Boolean,
        date: String,
        image: String
    ) : this() {
        this.id = id
        this.title = title
        this.amount = amount
        this.description = description
        this.category = category
        this.isPaid = isPaid
        this.date = date
        this.image = image
    }
}


