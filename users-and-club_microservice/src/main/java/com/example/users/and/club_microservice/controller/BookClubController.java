package com.example.users.and.club_microservice.controller;

import com.example.users.and.club_microservice.entity.BookClub;
import com.example.users.and.club_microservice.service.BookClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookclub")
public class BookClubController {
    @Autowired
    private BookClubService bookClubService;

    @GetMapping
    public List<BookClub> getAllClubs() {
        return bookClubService.getAllClubs();
    }

    @GetMapping("/{ClubId}")
    public ResponseEntity<BookClub> getClubById(@PathVariable Long ClubId) {
        Optional<BookClub> club = bookClubService.getClubById(ClubId);
        return club.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookClub createClub(@RequestBody BookClub bookClub) {
        return bookClubService.createClub(bookClub);
    }

    @DeleteMapping("/{ClubId}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long ClubId) {
        bookClubService.deleteClub(ClubId);
        return ResponseEntity.noContent().build();
    }

}
