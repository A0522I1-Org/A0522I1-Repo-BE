package com.example.spring_pawn_app.service.mail_sender;

import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class MailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IContractRepository iContractRepository;

    /**
     * Created by: PhongTD
     * Date created: 28/05/2023
     * Function: Send email reminding customers to pay the contract
     */
    @Scheduled(cron = "0 00 10 * * ?")
    public void sendEmailMindToPay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate expiryDate = LocalDate.now();
        List<String> listMail = new ArrayList<>();
        List<Contract> contracts = iContractRepository.findAll();
        for (Contract contract : contracts) {
            if (contract.getEndDate().getYear() == expiryDate.getYear()
                    && contract.getEndDate().getMonthValue() == expiryDate.getMonthValue()
                    && contract.getEndDate().getDayOfMonth() + 1 == expiryDate.getDayOfMonth()) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(contract.getCustomer().getEmail());
//                message.setTo("trinhduyphong20701@gmail.com");
                message.setSubject("Nhắc nhở lịch thanh toán hợp đồng ");
                message.setText("Hệ thống DANA88 xin thông báo tới khách hàng " + contract.getCustomer().getName() +
                        "có hợp đồng số " + contract.getContractCode() + "cần thanh toán vào ngày mai "
                        + contract.getEndDate() + ". Quý khách vui lòng thanh toán đúng hạn!");
                this.javaMailSender.send(message);
            }
        }
        System.out.println("End");
    }


    public void sendEmailPay(ContractCreateDto contract) throws MessagingException {
        MimeMessage messages = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true, "utf-8");
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("name", contract.getCustomer().getName());
        context.setVariable("dateOfBirth", contract.getCustomer().getDateOfBirth());
        context.setVariable("gender", contract.getCustomer().getGender());
        context.setVariable("address", contract.getCustomer().getAddress());
        context.setVariable("phone", contract.getCustomer().getPhone());

        String html = templateEngine.process("addContractSuccess", context);
        messages.setContent(html, "text/html");
        helper.setTo(contract.getCustomer().getEmail());
        helper.setSubject("Thông báo thêm mới thành công");

        this.javaMailSender.send(messages);
    }

    public void sendOtpMail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Xác thực đổi mật khẩu");
        message.setText("Mã OTP của bạn là " + otp);
        javaMailSender.send(message);
    }
}