package com.cyz.hepan.util;
import java.io.UnsupportedEncodingException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
public class TestMail {

        String un;
        String pw;
        String toMail;
        String title;
        String content;

        public TestMail(String un, String pw, String toMail, String title, String content) {
            this.un = un;
            this.pw = pw;
            this.toMail = toMail;
            this.title = title;
            this.content = content;
        }

        public String getUn() {
            return un;
        }

        public void setUn(String un) {
            this.un = un;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }

        public String getToMail() {
            return toMail;
        }

        public void setToMail(String toMail) {
            this.toMail = toMail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void send() throws MessagingException, UnsupportedEncodingException {
            Properties props = new Properties();
            props.put("mail.smtp.host", "mail.cyz.ink");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(un, pw);
                }
            });
            Transport ts = session.getTransport("smtp");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("abc@cyz.ink", "我们"));
            message.setSubject(title);
            message.setText(content);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            ts.send(message);


        }
}
