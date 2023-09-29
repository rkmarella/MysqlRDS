package com.example.mySQL.Rules;


import com.example.mySQL.model.Employee;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmployeeValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    private final Pattern emailPattern;

    public EmployeeValidator() {
        this.emailPattern = Pattern.compile(EMAIL_REGEX);
    }

    public boolean isValid(Employee emp) {
        if (emp == null) {
            return false;
        }
        return emailPattern.matcher(emp.getMail()).matches();
    }

    public boolean isValidEmail(String email) {
        // You can use more complex regex for better validation
        String regex = "^(.+)@(.+)$";
        return Pattern.matches(regex, email);
    }

    public boolean isValidPassword(String password) {
        // Dummy validation - ensures password is at least 8 characters
        return password.length() >= 8;
    }

}