package com.kairova.simple.model;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class FinancialAidApplication {
    private Long id;
    
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;
    
    @NotBlank(message = "Student ID is required")
    @Pattern(regexp = "^[A-Za-z0-9-]+", message = "Invalid student ID format")
    private String studentId;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+()\\- ]+$", message = "Invalid phone number format")
    private String phone;
    
    @NotBlank(message = "Program is required")
    private String program;
    
    @NotNull(message = "Current semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester cannot be more than 12")
    private Integer currentSemester;
    
    @NotNull(message = "Current GPA is required")
    @DecimalMin(value = "0.0", message = "GPA cannot be negative")
    @DecimalMax(value = "4.0", message = "GPA cannot be more than 4.0")
    private Double currentGPA;
    
    @NotNull(message = "Requested amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double requestedAmount;
    
    @NotBlank(message = "Currency is required")
    private String currency;
    
    @NotBlank(message = "Aid type is required")
    private String aidType; // Scholarship, Emergency Fund, Book Grant, etc.
    
    @NotBlank(message = "Please provide a reason for your application")
    @Size(min = 10, max = 1000, message = "Reason must be between 10 and 1000 characters")
    private String reason;
    
    private String supportingDocuments; // In a real app, this would be file uploads
    
    private String status; // Pending, Approved, Rejected
    
    private LocalDate applicationDate;
    
    @Size(max = 2000, message = "Additional information cannot exceed 2000 characters")
    private String additionalInfo;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Double getCurrentGPA() {
        return currentGPA;
    }

    public void setCurrentGPA(Double currentGPA) {
        this.currentGPA = currentGPA;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAidType() {
        return aidType;
    }

    public void setAidType(String aidType) {
        this.aidType = aidType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSupportingDocuments() {
        return supportingDocuments;
    }

    public void setSupportingDocuments(String supportingDocuments) {
        this.supportingDocuments = supportingDocuments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
