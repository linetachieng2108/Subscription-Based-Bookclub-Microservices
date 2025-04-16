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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMember(@PathVariable Long id) {
        clubMemberService.removeMember(id);
        return ResponseEntity.noContent().build();
    }
}
