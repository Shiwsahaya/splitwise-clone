package split.wise.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import split.wise.web.model.Mail;
import split.wise.web.model.Users;
import split.wise.web.service.MailService;
import split.wise.web.service.impl.UsersImpl;


@RestController
public class MailRegistrationController {

    @Autowired
    private MailService notificationService;

    @Autowired
    private UsersImpl usersImpl;

    @PostMapping("/send-reminder/{id}")
    public ResponseEntity<?> sendReminder(@PathVariable Long id, @RequestBody Mail mail){
        Users users=usersImpl.get(id);
        mail.setEmail(users.getEmail());
        System.out.println(mail.getEmail());
        try {
            notificationService.sendEmail(mail);
        }
        catch (MailException mailExc){
            System.out.println(mailExc);
        }
        return new ResponseEntity<>("mail is send", HttpStatus.OK);
    }

}
