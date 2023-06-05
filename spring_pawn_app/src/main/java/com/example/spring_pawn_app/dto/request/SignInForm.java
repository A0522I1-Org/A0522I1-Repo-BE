package com.example.spring_pawn_app.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignInForm {
    @NotNull
    @Size(min = 4,max = 45,message = "username trong khoảng 4-45 kí tự")
    @Pattern(regexp = "^[a-zA-Z0-9!@#&]*$", message = "Không được chứa kí tự đặc biệt ngoại trừ (!@#&)")
    private String username;
    @NotNull
    @Size(min = 4,max = 100,message = "password trong khoảng 4-100 kí tự")
    private String password;

    public SignInForm() {
    }

    public SignInForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
