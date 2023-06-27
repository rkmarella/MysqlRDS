package com.example.mySQL.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "emp_name")
    private String name;

    @Column
    private float salary;

    @Column
    private String mail;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public Employee() {
    }

    public Employee(int id, String name, float salary, String mail) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
