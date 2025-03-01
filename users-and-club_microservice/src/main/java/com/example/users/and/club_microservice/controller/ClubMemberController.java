package com.example.users.and.club_microservice.controller;

import com.example.users.and.club_microservice.service.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club-members")
public class ClubMemberController {
    @Autowired
    private ClubMemberService clubMemberService;




//    @GetMapping
//    public List<ClubMember> getAllMembers() {
//        return clubMemberService.getAllMembers();
//    }
//
//    @GetMapping("/club/{clubId}")
//    public List<ClubMember> getMembersByClub(@PathVariable Long clubId) {
//        return clubMemberService.getMembersByClub(clubId);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ClubMember> getMemberById(@PathVariable Long id) {
//        Optional<ClubMember> member = clubMemberService.getMemberById(id);
//        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addMember(@RequestParam Long userId, @RequestParam Long clubId) {
//        try {
//            ClubMember clubMember = clubMemberService.addMember(userId, clubId);
//            return ResponseEntity.ok(clubMember);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error adding user to club: " + e.getMessage());
//        }
        clubMemberService.addMember(userId, clubId);
        return ResponseEntity.ok("User added to club successfully");
    }

//    @PostMapping
//    public ClubMember addMember(@RequestParam Long clubId, @RequestParam Long user_id) {
//        return clubMemberService.addMember(clubId, user_id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMember(@PathVariable Long id) {
        clubMemberService.removeMember(id);
        return ResponseEntity.noContent().build();
    }
}
