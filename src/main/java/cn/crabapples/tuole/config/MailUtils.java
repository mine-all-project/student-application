package cn.crabapples.tuole.config;

import ch.ethz.ssh2.crypto.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * TODO SMTP发送邮件演示
 *
 *
 * 2020/3/20 21:15
 *
 *
 * pc-name 29404
 */
public class MailUtils {
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);
//    private static final String FILE_PATH = "d:/123.txt";
//    private static final String FILE_NAME = "喜欢你D D .txt";

    /**
     * 初始化session
     *
     * @param configure 邮件配置
     * @return session
     */
    private static Session initSession(MailUtilsConfigure configure) {
        logger.info("开始初始化session");
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", configure.getProtocol());
        properties.put("mail.smtp.host", configure.getHost());
        properties.put("mail.smtp.auth", configure.isAuth());
        properties.put("mail.smtp.port", configure.getPort());
        properties.put("mail.smtp.socketFactory.class", configure.getSocketFactory());
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(configure.isDebug());
        logger.info("初始化session完成:[{}]", session);
        return session;
    }

    /**
     * 初始化邮件数据
     *
     * @param configure 邮件配置
     * @param session   session连接
     * @param title     邮件标题
     * @param target    收件人
     * @return 邮件数据
     */
    public static MimeMessage initMessage(MailUtilsConfigure configure, Session session, String title, String target) {
        try {
            logger.info("开始初始化message");
            MimeMessage message = new MimeMessage(session);
            message.setSubject(title);
            message.setFrom(new InternetAddress(configure.getSource()));
            logger.debug("发件人设置为:[{}]", configure.getSource());
            /*
             * ↑ ↑ ↑ ↑ ↑ ↑ ↑
             * 设置发件人地址
             *
             * 设置收件人地址
             * ↓ ↓ ↓ ↓ ↓ ↓ ↓
             */
            message.addRecipients(Message.RecipientType.TO, target);
            logger.info("初始化message完成:[{}]", message);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送邮件
     *
     * @param configure 邮件配置
     * @param session   连接信息
     * @param message   邮件信息
     */
    private static void sendEmail(MailUtilsConfigure configure, Session session, MimeMessage message) {
        try {
            logger.info("开始发送邮件");
            Transport transport = session.getTransport(configure.getProtocol());
            transport.connect(configure.getHost(), configure.getUsername(), configure.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            logger.info("邮件发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sendMail(String title, String content, String target) throws MessagingException {
            MailUtilsConfigure configure = new MailUtilsConfigure();
            configure.setDebug(true);
            Session session = initSession(configure);
            MimeMessage message = initMessage(configure, session, title, target);
            Multipart multipart = new MimeMultipart();
            addMailContentText(multipart, content);
            assert message != null;
            message.setContent(multipart);
            message.saveChanges();
            sendEmail(configure, session, message);
    }

    /**
     * 设置正文
     *
     * @param multipart   邮件数据信息
     * @param contentText 邮件正文
     * @throws MessagingException 邮件相关异常
     */
    public static void addMailContentText(Multipart multipart, String contentText) throws MessagingException {
        /*
         * BodyPart类型对象用来存储邮件Body相关信息
         */
        BodyPart content = new MimeBodyPart();
        logger.debug("设置邮件正文:[{}]", contentText);
        content.setText(contentText);
        multipart.addBodyPart(content);
    }

    /**
     * 添加附件
     *
     * @param multipart 邮件数据信息
     * @param filePath  附件在本地文件路径
     * @param fileName  附件邮件中文件名
     * @throws MessagingException 邮件相关异常
     */
    public static void addMailContentFile(Multipart multipart, String filePath, String fileName) throws MessagingException {
        BodyPart filePart = new MimeBodyPart();
        logger.debug("设置邮件附件:[{}]", filePath);
        filePart.setDataHandler(new DataHandler(new FileDataSource(filePath)));
        filePart.setFileName("=?UTF-8?B?" + new String(Base64.encode(fileName.getBytes())) + "?=");
        multipart.addBodyPart(filePart);
    }
}
