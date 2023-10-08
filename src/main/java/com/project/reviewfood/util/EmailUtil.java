package com.project.reviewfood.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify email using the OTP provided");
        mimeMessageHelper.setText(
                """
                        Please verify your email using the OTP provided via the link attached below.
                        <div>
                            <a href = "http://localhost:8080/verify-email?email=%s&otp=%s" target = "_blank">Click link to verify </a>
                        </div>
                        """
                        .formatted(email, otp),
                true);
        javaMailSender.send(mimeMessage);
    }

    public void sendResetPasswordEmail(String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set a new password and don't forget again it!");
        mimeMessageHelper.setText(
                """
                        Please reset your new password via the link attached below.
                        <div>
                            <a href = "http://localhost:8080/setPassword?email=%s" target = "_blank">Click link to set password </a>
                        </div>
                        """
                        .formatted(email),
                true);
        javaMailSender.send(mimeMessage);
    }
}
