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
    @Length(min = 5,max = 100,message = "Tiêu đề chứa ít nhất 5 kí tự và tối đa 100 kí tự")
    private String title;
    @NotBlank(message = "Chưa chọn ảnh")
    private String img;
    @NotBlank(message = "Vui lòng nhập nội dung")
    @Length(min = 50,max = 50000,message = "Nội dung phải có tối thiểu 50 kí tự ")

    private String content;
    private LocalDate publicDate;
    private boolean isFeature;
    private boolean isFlag;
    private Integer employee;

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

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public ArticleDTO(Integer id, String title, String img, String content, LocalDate publicDate, Boolean isFeature, boolean isFlag, Integer employee) {
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
