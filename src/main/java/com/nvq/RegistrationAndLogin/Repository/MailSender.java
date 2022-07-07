package com.nvq.RegistrationAndLogin.Repository;

import com.nvq.RegistrationAndLogin.pojo.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailSender {
    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
}
