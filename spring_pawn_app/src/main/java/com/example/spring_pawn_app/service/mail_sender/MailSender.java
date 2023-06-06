package com.example.spring_pawn_app.service.mail_sender;

import com.example.spring_pawn_app.dto.contract.ContractCreateDto;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import com.example.spring_pawn_app.dto.contract.ContractDto;
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
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@EnableScheduling
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IContractRepository iContractRepository;


    /**
     * Created By: HoangVV
     * Date create: 15/05/2023
     * @param contract
     * @throws MessagingException
     */
    public void sendEmailPay(ContractDto contract) throws MessagingException {
        LocalDate currentDate = LocalDate.now();
        MimeMessage messages = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true,"UTF-8");
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        context.setVariable("name",contract.getCustomer().getName());
        context.setVariable("dateOfBirth",contract.getCustomer().getDateOfBirth());
        context.setVariable("gender",contract.getCustomer().getGender());
        context.setVariable("address",contract.getCustomer().getAddress());
        context.setVariable("phone",contract.getCustomer().getPhone());
        context.setVariable("time",currentDate);


        String html = templateEngine.process("addContractSuccess", context);
        messages.setContent(html, "text/html; charset=UTF-8");
        helper.setTo(contract.getCustomer().getEmail());
        helper.setSubject("Thông báo thanh toán thành công");

        this.javaMailSender.send(messages);
    }

    public void sendEmailCreate(ContractCreateDto contract) throws MessagingException {
        LocalDate localDate = LocalDate.now();
        Customer customer = iCustomerService.findCustomerById(contract.getCustomer().getId());
        MimeMessage messages = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true, "UTF-8");
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("name_customer", customer.getName());
        System.out.println(contract.getCustomer());
        context.setVariable("phone", contract.getCustomer().getPhone());
        context.setVariable("beginDate", contract.getBeginDate());
        context.setVariable("endDate", contract.getEndDate());
        context.setVariable("nameProduct", contract.getNameProduct());
        context.setVariable("price", contract.getPrice());
        context.setVariable("interest", contract.getInterest());
        context.setVariable("time", localDate);

        String html = templateEngine.process("createContractSuccess", context);
        messages.setContent(html, "text/html; charset=UTF-8");
        helper.setTo(contract.getCustomer().getEmail());
        helper.setSubject("Thông báo thêm mới thành công");

        this.javaMailSender.send(messages);
    }

    /**
     * Created by: PhongTD
     * Date created: 28/05/2023
     * Function: Send email reminding customers to pay the contract
     */
    @Scheduled(cron = "0 23 19 * * ?")
    public void sendEmailMindToPay() throws MessagingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate expiryDate = LocalDate.now();
        List<Contract> contracts = iContractRepository.findAll();
        for (Contract contract : contracts) {
            if (contract.getEndDate().getYear() == expiryDate.getYear()
                    && contract.getEndDate().getMonthValue() == expiryDate.getMonthValue()
                    && contract.getEndDate().getDayOfMonth() - 1 == expiryDate.getDayOfMonth()) {
                ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
                templateResolver.setPrefix("/templates/");
                templateResolver.setSuffix(".html");
                templateResolver.setTemplateMode("HTML");
                SpringTemplateEngine templateEngine = new SpringTemplateEngine();
                templateEngine.setTemplateResolver(templateResolver);
                System.out.println(contract.getEndDate().getDayOfMonth());
                MimeMessage messages = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(messages, true, "UTF-8");
                Context context = new Context();
                context.setVariable("name", contract.getCustomer().getName());
                context.setVariable("contractCode", contract.getContractCode());
                context.setVariable("productName", contract.getProduct().getName());
                context.setVariable("beginDate", contract.getBeginDate().format(formatter));
                context.setVariable("endDate", contract.getEndDate().format(formatter));
                context.setVariable("interest", contract.getInterest());
                context.setVariable("address", contract.getCustomer().getAddress());
                context.setVariable("phone", contract.getCustomer().getPhone());

                String html = templateEngine.process("mindPayContract", context);
                messages.setContent(html, "text/html; charset=UTF-8");
                helper.setTo(contract.getCustomer().getEmail());
                helper.setSubject("Nhắc nhở thanh toán hợp đồng");
                this.javaMailSender.send(messages);
            }
        }
    }
    public void sendOtpMail(String email,String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Xác thực đổi mật khẩu");
        message.setText("Mã OTP của bạn là " + otp);
        javaMailSender.send(message);
    }
}
