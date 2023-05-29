package com.example.spring_pawn_app.service.mail_sender;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class MailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IContractRepository iContractRepository;

    @Scheduled(cron = "0 00 10 * * ?")
    public void sendEmailMindToPay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate expiryDate = LocalDate.now();
        List<String> listMail = new ArrayList<>();
        List<Contract> contracts = iContractRepository.findAll();
        for (Contract contract: contracts) {
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
                this.mailSender.send(message);
            }
        }
        System.out.println("End");
    }
}
