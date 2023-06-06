package com.example.spring_pawn_app.dto.employee;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


public class EmployeeInforDTO implements Validator {
    private Integer id;
    //    @NotBlank(message = "Vui lòng nhập họ tên")
//    @Pattern(regexp = "^(?:[A-Z][a-zÀ-ỹ]*(?: [A-Z][a-zÀ-ỹ]*)+)$",message = "Họ và tên chưa đúng định dạng")
//    @Length(min = 5,max = 50,message = "Họ và tên phải chứa ít nhất 5 kí tự và tối đa 50 kí tự")
    private String name;
    //    @NotNull(message = "Vui lòng nhập ngày sinh")
    private LocalDate dateOfBirth;
    //    @NotBlank(message = "Vui lòng nhập số điện thoại")
//    @Pattern(regexp = "^(09|08)\\d{8}$",message = "số điện thoại chỉ được phép 10 số và bắt đầu 09 hoặc 08")
    private String phone;
    //    @NotBlank(message = "Vui lòng nhập email")
//    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@gmail.com+$",message = "Email không đúng định dạng, vui lòng nhập lại. Ex: tên_email@gmail.com")
//    @Length(min = 6,max = 30,message = "Tên email chỉ được phép chứa từ 6 đến 30 kí tự")
    private String email;
    //    @NotNull(message = "Vui lòng chọn giới tính")
    private Integer gender;
    //    @NotBlank(message = "Vui lòng nhập địa chỉ")
//    @Pattern(regexp = "^[^!@#$%^&*()_+<>?'\"{}\\`~|/\\\\]+$",message = "Địa chỉ không được chứa các kí tự đặc biệt")
//    @Length(min = 5,max = 50,message = "Địa chỉ phải có ít nhất 5 và tối đa 50 kí tự")
    private String address;
    //    @NotBlank(message = "Hộ chiếu/CMND không được để trống")
//    @Pattern(regexp = "^\\d{12}$",message = "Hộ chiếu/CMND phải chứa 12 số")
    private String idCard;
    private String avatar;
    private String userName;
    //    @Length(min = 10,max = 30,message = "Mật khẩu phải chứa ít nhất 10 kí tự và tối đa 30 kí tự")
//    @Pattern(regexp ="^(?=.*[A-Z])(?=.*\\d)(?=.*[a-zA-Z0-9])[\\w!@#$%^&*()-=_+<>?'\"{}`~/|]*\\d?$",message = "Mật khẩu phải chứa kí tự viết hoa, số và kí tự đặt biệt")
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
        EmployeeInforDTO employeeInforDTO= (EmployeeInforDTO) target;
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
//        if(!(employeeInforDTO.confirmPassword==null)){
//            if (!employeeInforDTO.password.equals(employeeInforDTO.confirmPassword)){
//                errors.rejectValue("confirmPassword", "", "Không trùng khớp với mật khẩu vừa nhập, vui lòng nhập lại");
//            }
//        }

    }
}