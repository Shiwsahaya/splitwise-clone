package split.wise.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Comment extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Expense expense;

    @NotNull
    @ManyToOne
    private Users author;

    @NotNull
    private String content;

    @JsonCreator
    public Comment(@JsonProperty("expense") Long expenseId,
                   @JsonProperty("author") Long authorId) {
        this.expense = new Expense();
        expense.setId(expenseId);
        this.author = new Users();
        author.setId(authorId);
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
