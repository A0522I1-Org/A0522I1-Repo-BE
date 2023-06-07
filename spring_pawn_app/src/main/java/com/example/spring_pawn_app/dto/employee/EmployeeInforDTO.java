package com.example.spring_pawn_app.dto.employee;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.time.LocalDate;


public class EmployeeInforDTO implements Validator {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private Integer gender;
    private String address;
    private String idCard;
    private String avatar;
    private String userName;
    private String password;
    private String confirmPassword;

    public EmployeeInforDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public EmployeeInforDTO(Integer id, String name, LocalDate dateOfBirth, String phone, String email, Integer gender, String address, String idCard, String avatar, String userName, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.idCard = idCard;
        this.avatar = avatar;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeInforDTO employeeInforDTO = (EmployeeInforDTO) target;
        if (!(employeeInforDTO.dateOfBirth == null)) {
            LocalDate today = LocalDate.now();
            LocalDate minAgeDate = today.minusYears(23);
            LocalDate maxAgeDate = today.minusYears(50);
            if (employeeInforDTO.dateOfBirth.isAfter(minAgeDate)) {
                errors.rejectValue("dateOfBirth", "", "chưa đủ 23 tuổi");
            }
            if (employeeInforDTO.dateOfBirth.isBefore(maxAgeDate)) {
                errors.rejectValue("dateOfBirth", "", "lớn hơn 50 tuổi");
            }
        }

    }
}