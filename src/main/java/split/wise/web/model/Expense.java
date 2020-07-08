package split.wise.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
public class Expense extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted;

    @NotNull
    private Long amount;

    @NotNull
    private String description;

    @NotNull
    private Date paymentDate;

    @NotNull
    private String creationMethod;

    @NotNull
    @ManyToOne
    private Users addedBy;

    @ManyToOne
    private Group group;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @OneToMany(mappedBy = "expense")
    private List<ExpenseDebts> contributors;

    @OneToMany(mappedBy = "expense")
    private List<Repayments> repayments;

    @OneToMany(mappedBy = "expense")
    private List<Comment> comments;

    @OneToMany(mappedBy = "expense")
    private List<Activity> activities;

    @JsonCreator
    public Expense(@JsonProperty("addedBy") Long addedById,
                   @JsonProperty("category") Long categoryId,
                   @JsonProperty("group") Long groupId) {
        this.addedBy = new Users();
        addedBy.setId(addedById);
        this.category = new Category();
        category.setId(categoryId);
        this.group = new Group();
        group.setId(groupId);
    }

    public Expense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCreationMethod() {
        return creationMethod;
    }

    public void setCreationMethod(String creationMethod) {
        this.creationMethod = creationMethod;
    }

    public Users getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Users addedBy) {
        this.addedBy = addedBy;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ExpenseDebts> getContributors() {
        return contributors;
    }

    public void setContributors(List<ExpenseDebts> contributors) {
        this.contributors = contributors;
    }

    public List<Repayments> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<Repayments> repayments) {
        this.repayments = repayments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
