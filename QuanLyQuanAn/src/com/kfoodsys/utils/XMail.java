/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kfoodsys.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import java.util.Base64;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author phuho
 */
public class XMail {

    
    private static String userMail;
    private static String passMail;
    
    /**
     * Thực hiện gửi mail
     *
     * @param to là mail người nhận
     * @param content là nội dung của mail cần gửi đi
     * @param subject là tiêu đề của mail
     */    
    public static void sendMail(String to, String content, String subject) throws Exception {
        ResultSet rs = checkSetUpMailer();
        if(rs == null) {
            throw new Exception("Hệ thống chưa set up tài khoản gửi mail");
        }
        userMail = rs.getString("UsernameMailer");
        passMail = rs.getString("PasswordMailer");
        byte[] decodedBytes = Base64.getDecoder().decode(passMail);
        passMail = new String(decodedBytes);
        
        if (!userMail.equals("") && !passMail.equals("")) {
            try {
                Session session = GetSessionLogin(userMail, passMail);
                
                if(session == null) {
                    throw new Exception("Thất bại! Đã xảy ra lỗi khi gửi Email.");
                }

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(userMail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject, "UTF-8");
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(setContentHtml(content), "text/html; charset=UTF-8");
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                DataSource fds = new FileDataSource("imgs\\logo.png");
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<image-logo>");
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                MailSender.queue(message);
                XVerify.jDialog.setVisible(true);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Hệ thống chưa setup tài khoản gửi mail");
        }
    }
    
    private static Session GetSessionLogin(String userMail, String passMail) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            
            Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userMail, passMail);
                    }
            });
            return session;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static ResultSet checkSetUpMailer() {
        ResultSet rs;
        try {
            rs = XJdbc.query("Select * from Mailer");
            if(!rs.next()) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return rs;
    }
    
    public static String setContentHtml(String content) {
        String template =   
                "<div style=\"background-color: #e37c4117; padding: 15px;font-family: alata,sans-serif;\">\n" +
                "    <div style=\"margin: auto; background-color: #ffffff; max-width: 500px; padding: 10px; border-top: 6px solid #e37c41; border-radius: 5px;\">\n" +
                "        <div>\n" +
                "            <div style=\"width: 100%;text-align: center;\">\n" +
                "                <img src=\"cid:image-logo\" width=\"120\" height= \"120\" alt=\"logo\"/>\n" +
                "                <span style=\"display: block; font-size: 20px; font-weight: bold; color: #ff0561; text-align: center; border: 3px solid; border-radius: 10px;\">KFOOD</span>\n" +
                "            </div>\n" +
                "        </div><br>\n" +
                "        <p style=\"line-height: 22px;\">\n" +
                            content +
                "            \n" +
                "        </p><br>\n" +
                "        <hr style=\"border-top: 1px solid;\" />\n" +
                "        <div style=\"font-style: italic;\">\n" +
                "            <span>Đây là mail tự động của hệ thống. Vui lòng không phản hồi mail này!</span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";
        
        return template;
    }
}
