package eci.ieti.data.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Todo { 

    @Id
    private long id;
    private String description;
    private Integer priority;
    private String dueDate;
    private String responsible;
    private String status;

    public Todo(){
    }

    public Todo(long id, String description, Integer priority, String dueDate, String responsible, String status) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.responsible = responsible;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo [id = " + id + ", description=" + description + ", dueDate=" + dueDate + ", priority=" + priority + ", responsible="
                + responsible + ", status=" + status + "]";
    }

  
}