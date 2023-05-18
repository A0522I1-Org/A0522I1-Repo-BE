package com.example.spring_pawn_app.dto.article;

import com.example.spring_pawn_app.model.Employee;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class ArticleDTO implements Validator {
    private Integer id;
    @NotBlank(message = "Vui lòng nhập tiêu đề")
    @Length(min = 5,max = 50,message = "Tiêu đề chứa ít nhất 5 kí tự và tối đa 50 kí tự")
    private String title;
    @NotBlank(message = "Chưa chọn ảnh")
    private String img;
    @NotBlank(message = "Vui lòng nhập nội dung")
    private String content;
    private LocalDate publicDate;
    private boolean isFeature;
    private boolean isFlag;
    private Employee employee;

    public ArticleDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public Boolean isFeature() {
        return isFeature;
    }

    public void setIsFeature(Boolean feature) {
        isFeature = feature;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setIsFlag(boolean flag) {
        isFlag = flag;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ArticleDTO(Integer id, String title, String img, String content, LocalDate publicDate, Boolean isFeature, boolean isFlag, Employee employee) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.publicDate = publicDate;
        this.isFeature = isFeature;
        this.isFlag = isFlag;
        this.employee = employee;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
