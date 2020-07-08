package split.wise.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import split.wise.web.model.*;
import split.wise.web.service.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService service;

    @Autowired
    UsersService usersService;

    @Autowired
    GroupBalanceService groupBalanceService;

    @Autowired
    DebtService debtService;

    @Autowired
    StackOverflowResolverService jsonService;

    @Autowired
    ActivityService activityService;

    @Autowired
    private NotifyService notifyService;
    @Autowired
    private MailService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroup(@PathVariable Long id) {
        return new ResponseEntity<>(jsonService.resolveGroup(service.get(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllGroup() {
        return new ResponseEntity<>(jsonService.resolveGroupList(service.getAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> group(@RequestBody Group group) {
        if(group.getMembers().size()<2){
            return new  ResponseEntity<>("atleast two member should be added", HttpStatus.BAD_REQUEST);
        }
        Group group1 = service.save(group);
        group1.getMembers().forEach((member) -> {
            groupBalanceService.save(member, group1);
            if(member.getId()!=group1.getCreatedBy().getId()){
                System.out.println(member.getId());
            }
            group1.getMembers().forEach((teammate) -> {
                if (!(member.getId().equals(teammate.getId()))) {
                    Notify notify=notifyService.findByUser(usersService.get(member.getId()));
                    if(notify.getForAddingGroup()){
                        Mail mail=new Mail();
                        mail.setEmail(notify.getUser().getEmail());
                        mail.setSubject("Adding Group");
                        mail.setText("you are adding by: "+notify.getUser().getName()+", in this group: "+group1.getName());
                        mail.setLink("http://ec2-3-84-167-235.compute-1.amazonaws.com:8080/group/"+group1.getId());
                        notificationService.sendEmail(mail);
                    }
                    debtService.save(group1, member, teammate);
                    if (debtService.get(member, teammate, null) == null)
                        usersService.makeFriends(usersService.get(member.getId()), usersService.get(teammate.getId()));
                }
            });
        });
        Activity activity = new Activity();
        activity.setAction("added");
        activity.setGroup(group1);
        activityService.save(activity);
        return new ResponseEntity<>(jsonService.resolveGroup(service.get(group1.getId())), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateGroupName(@RequestBody Group updatedGroup) {
        Group group = service.get(updatedGroup.getId());
        if(updatedGroup.getName() != null)
            group.setName(updatedGroup.getName());
        return new ResponseEntity<>(jsonService.resolveGroup(service.save(group)), HttpStatus.OK);
    }

    @PostMapping("/{id}/member")
    public ResponseEntity<?> addGroupMember(@PathVariable Long id, @RequestBody Users newMember) {
        Group group = service.get(id);
        if (debtService.get(group.getCreatedBy(), newMember, null) == null) {
            usersService.makeFriends(group.getCreatedBy(), usersService.get(newMember.getId()));
            usersService.makeFriends(usersService.get(newMember.getId()), group.getCreatedBy());
        }
        groupBalanceService.save(newMember, group);
        group.getMembers().forEach((member -> {
            debtService.save(group, newMember, member);
            debtService.save(group, member, newMember);
        }));
        List<Users> members = group.getMembers();
        members.add(newMember);
        group.setMembers(members);
        service.save(group);
        Activity activity = new Activity();
        activity.setAction("added " + usersService.get(newMember.getId()).getName());
        activity.setGroup(service.get(id));
        activityService.save(activity);
        return new ResponseEntity<>(jsonService.resolveGroup(service.get(id)), HttpStatus.OK);
    }

}
