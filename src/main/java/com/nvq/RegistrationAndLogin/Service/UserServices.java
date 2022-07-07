package com.nvq.RegistrationAndLogin.Service;

import com.nvq.RegistrationAndLogin.Repository.MailSender;
import com.nvq.RegistrationAndLogin.Repository.UserRepository;
import com.nvq.RegistrationAndLogin.config.PasswordEncoder;
import com.nvq.RegistrationAndLogin.pojo.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailService;
    public void register(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {

        String encodePassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnable(false);

        userRepo.save(user);

        mailService.sendVerificationEmail(user,siteURL);

        //sendVerificationEmail(user,siteURL);
    }

    public boolean verify(String code){
        User user = userRepo.findByVerificationCode(code);
        if (user == null || user.isEnable())
            return false;
        else {
            user.setVerificationCode(null);
            user.setEnable(true);
            userRepo.save(user);
            return true;
        }
    }

//    private void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
//        String toAddress = user.getEmail();
//        String fromAddress = "Your email address";
//        String senderName = "Your company name";
//        String subject = "Please verify your registration";
//        String content = "Dear [[name]],<br>"
//                + "Please click the link below to verify your registration:<br>"
//                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
//                + "Thank you,<br>"
//                + "Your company name.";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom(fromAddress, senderName);
//        helper.setTo(toAddress);
//        helper.setSubject(subject);
//
//        content = content.replace("[[name]]", user.getFullName());
//        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
//
//        content = content.replace("[[URL]]", verifyURL);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
}
