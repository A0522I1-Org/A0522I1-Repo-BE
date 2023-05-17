package com.example.spring_pawn_app.service.mail_sender;

import com.example.spring_pawn_app.dto.contract.ContractDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailPay(ContractDto contract) throws MessagingException {
        MimeMessage messages = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true,"utf-8");
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

        String html = templateEngine.process("payment", context);
        messages.setContent(html,"text/html");
        helper.setTo(contract.getCustomer().getEmail());
        helper.setSubject("Thông báo thanh toán thành công");

        this.javaMailSender.send(messages);

    }

//    public void sendEmailSub(Contract contract) throws MessagingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//
//        Context context = new Context();
//        context.setVariable("name",contract.getCustomer().getName());
//        context.setVariable("dateOfBirth",contract.getCustomer().getDob());
//        context.setVariable("gender",contract.getCustomer().getGender());
//        context.setVariable("address",contract.getCustomer().getAddress());
//        context.setVariable("phone",contract.getCustomer().getPhone());
//
//        String html = templateEngine.process("payment", context);
//        message.setContent(html,"text/html");
//        helper.setTo(contract.getCustomer().getEmail());
//        helper.setSubject("Thông báo thanh toán thành công");
//
//        this.javaMailSender.send(message);
//
//    }

}
