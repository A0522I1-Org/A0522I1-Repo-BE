package com.example.spring_pawn_app.service.forgotpassword;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.repository.user.IUserRepository;
import com.example.spring_pawn_app.service.employee.IEmployeeService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgotPasswordService implements IForgotPasswordService{

    @Autowired private IEmployeeService employeeService;
    @Autowired private IUserRepository userRepository;
    @Autowired
    private CacheManager cacheManager;
    @Autowired private MailSender mailSender;

    @Override
    public void sendOtpByEmail(String email) {
        Employee employee = employeeService.findByEmail(email);
        if(employee == null){
            throw new RuntimeException("Email khong tồn tại trong hệ thống");
        }
        String otp = OtpGenerator.generateOtp();
        // Gửi email
        mailSender.sendOtpMail(email,otp);

        // Lưu OTP vào cache
        Cache otpCache = cacheManager.getCache("otpCache");
        otpCache.put(email,otp);

    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        // Lấy OTP từ cache
        Cache otpCache = cacheManager.getCache("otpCache");
        String cacheOtp = otpCache.get(email,String.class);
        Employee employee = employeeService.findByEmail(email);
        // kiểm tra mã OTP
        if(cacheOtp == null || !otp.equals(cacheOtp) || employee == null){
            throw new RuntimeException("OTP khong hợp lệ");
        }
        else {
            // cập nhật mật khẩu mới
            User user = userRepository.findUserByEmployee(employee).get();
            user.setPassword(newPassword);
            userRepository.save(user);

            //Xoa OTP ra khoi cache
            otpCache.evict(email);

        }

    }
}
