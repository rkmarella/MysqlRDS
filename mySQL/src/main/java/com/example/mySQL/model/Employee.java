package com.example.mySQL.model;


import jakarta.persistence.*;

@Entity
@Table(name="employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="emp_name")
    private String name;
    @Column
    private float salary;
    @Column
    private String mail;



    public Employee() {
    }

    public Employee(int id, String name, float salary,String mail) {
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
}
