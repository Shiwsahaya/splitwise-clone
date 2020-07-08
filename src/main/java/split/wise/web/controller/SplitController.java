package split.wise.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import split.wise.web.model.Mail;
import split.wise.web.model.Notify;
import split.wise.web.model.Users;
import split.wise.web.service.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class SplitController {

    @Autowired
    private UsersService service;

    @Autowired
    private StackOverflowResolverService jsonService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private NotifyService notifyService;
    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody Users user) {
        user.setMoney(0L);
        Notify notify = notifyService.defaultSave(service.save(user));
        user.setNotify(notify);
        System.out.println(notify.getUser().getId());
        return new ResponseEntity<>(jsonService.resolveUser(service.get(notify.getUser().getId())), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(jsonService.resolveUsersList(service.getAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(jsonService.resolveUser(service.get(id)), HttpStatus.OK);
    }

    @PostMapping("/friend")
    public ResponseEntity<?> newFriend(@RequestBody Users friend) {

        int noOfFriends = service.get(2L).getFriends().size();

        service.makeFriends(service.get(2L), service.get(friend.getEmail()));
        service.makeFriends(service.get(friend.getEmail()), service.get(2L));

        if(noOfFriends != service.get(2L).getFriends().size()) {
            Notify notify = notifyService.findByUser(service.get(friend.getEmail()));
            System.out.println(4);
            if (notify.getForAddingFriend()) {
                mailService.sendMailForAddingFriend(service.get(friend.getEmail()));
            }
        }
        return new ResponseEntity<>(jsonService.resolveUser(service.get(friend.getEmail())), HttpStatus.OK);
    }
    @PostMapping("/invite-friend")
    public ResponseEntity<?>inviteFriend(@RequestBody Users friend){
        String responseString;
        Users loginUser=service.get(2L);
        Users users=service.get(friend.getEmail());
        if(users==null){
            System.out.println("enter in else");
            Mail mail=new Mail();
            System.out.println(loginUser.getName());
            mail.setEmail(friend.getEmail());
            mail.setSubject(loginUser.getName()+" wants to share bills with you on Splitwise!");
            mail.setText(loginUser.getEmail() +" just invited you to join Splitwise!");
            mail.setLink("http://signpulink");
            responseString="invite sent";
            mailService.sendEmail(mail);
        }
        else
        {
            responseString="user already registered";
        }

        return new ResponseEntity<>(responseString,HttpStatus.OK);
    }

    @GetMapping("/activities")
    public ResponseEntity<?> recentActivities() {
        return new ResponseEntity<>(jsonService.resolveActivityList(activityService.getAllByUser(service.get(2L))), HttpStatus.OK);
    }

    @PutMapping("/notify")
    public ResponseEntity<?> notify(@RequestBody Notify notify) {
        notify.setId(service.get(2L).getNotify().getId());
        return new ResponseEntity<>(notifyService.save(notify), HttpStatus.OK);
    }

}

