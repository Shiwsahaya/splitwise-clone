package split.wise.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import split.wise.web.model.Expense;
import split.wise.web.model.Mail;
import split.wise.web.model.Notify;
import split.wise.web.model.Users;
import split.wise.web.service.impl.GroupImpl;
import split.wise.web.service.impl.UsersImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private JavaMailSender javaMailSender;
    @Autowired
    private NotifyService notifyService;

    @Autowired
    private GroupImpl groupImpl;

    @Autowired
    private UsersImpl usersImpl;
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        System.out.println(mail.getEmail());
        mailMessage.setTo(mail.getEmail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getText() + " " + "Link: " + mail.getLink());
        javaMailSender.send(mailMessage);
    }


    public void sendEmailWithAttachment(Mail mail) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
        helper.setTo("splitwise11@gmail.com");
        helper.setSubject("Testing Mail Api With Attachment");
        helper.setText("your message received correctly");

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);
        javaMailSender.send(mimeMailMessage);
    }

    public void sendExpenseEmail(Expense expense, String valueForUpdateOrEdit) {
        expense.getContributors().forEach((contributor -> {
            Notify notify = notifyService.findByUser(contributor.getUser());
            if (notify.getForAddingExpense()) {
                Mail mail = new Mail();
                if(valueForUpdateOrEdit.equals("just added "))
                mail.setSubject("adding Expense");
                if(valueForUpdateOrEdit.equals("just updated "))
                    mail.setSubject("updated expense");
                if(valueForUpdateOrEdit.equals("just deleted "))
                    mail.setSubject(expense.getDescription()+ "("+expense.getAmount()+") deleted");
                mail.setEmail(notify.getUser().getEmail());
                System.out.println(notify.getUser().getEmail());
                String getBackOrOwe;
                if (contributor.getNetBalance() > 0) {
                    getBackOrOwe = "you get back " + contributor.getNetBalance();
                } else {
                    getBackOrOwe = "You owe " + Math.abs(contributor.getNetBalance());
                }
                if (expense.getGroup() != null) {
                    String groupName=groupImpl.get(expense.getGroup().getId()).getName();
                    mail.setText(notify.getUser().getName() + valueForUpdateOrEdit + expense.getDescription() +
                            " to the group " + groupName + " Total Balance: " + expense.getAmount() + " " + getBackOrOwe);
                    mail.setLink("Link: ");
                    sendEmail(mail);
                } else {
                    mail.setText(notify.getUser().getName() + valueForUpdateOrEdit + expense.getDescription() + " Total Balance: "
                            + expense.getAmount()+ " " + getBackOrOwe);
                    mail.setLink("Link: ");
                    sendEmail(mail);
                }
            }

        }));
    }
public void settleUpEmail(Expense expense){
    System.out.println("insert in settle up");
    expense.getRepayments().forEach(repayments -> {
        System.out.println(1);
        Mail mail=new Mail();
        Users usersTO=usersImpl.get(repayments.getTo().getId());
        mail.setSubject(usersTO.getName()+" paid you "+ repayments.getAmount()+ " on Splitwiese");
        mail.setText(usersTO.getName()+" just recorded a payment to you in the group: "+ expense.getGroup());
        Users usersFrom=usersImpl.get(repayments.getFrom().getId());
        mail.setEmail(usersFrom.getEmail());
        mail.setLink("http://http://localhost:8080/");
        sendEmail(mail);

    });
}
    public void sendMailForAddingFriend(Users users) {
            Mail mail=new Mail();
            mail.setEmail(users.getEmail());
            mail.setSubject("adding friend");
            mail.setText("you are adding by "+users.getName()+" "+users.getEmail());
            sendEmail(mail);

    }
}
