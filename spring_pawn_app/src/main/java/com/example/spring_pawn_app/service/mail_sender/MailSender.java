package com.example.spring_pawn_app.service.mail_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailPay() throws MessagingException {
        ////     Create a Simple MailMessage.
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(student.getEmail());
//        message.setSubject("Spring mail");
//        message.setText("Hello, Im testing Simple Email");
//        this.emailSender.send(message);


//         Create HTML Mail
        MimeMessage messages = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true,"utf-8");
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable();

        String html = templateEngine.process("payment", context);
        messages.setContent(html,"text/html");
        helper.setTo();
        helper.setSubject("Thông báo thanh toán");

//        String file = "com/example/demo/res/helloWorld.txt";

        // Attachment 1
        FileSystemResource file1 = new FileSystemResource(new File("/static/text.html"));

        helper.addAttachment(file1.getFilename(), file1);


//        // Send Message!
        this.javaMailSender.send(messages);

    }

}
