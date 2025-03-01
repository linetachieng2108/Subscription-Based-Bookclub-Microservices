package com.example.users.and.club_microservice.service;

import com.example.users.and.club_microservice.entity.BookClub;
import com.example.users.and.club_microservice.entity.ClubMember;
import com.example.users.and.club_microservice.repository.BookClubRepository;
import com.example.users.and.club_microservice.repository.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClubMemberService {
    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private BookClubRepository bookClubRepository;

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${user.service.url}") // Load from application.properties
//    private String userServiceUrl;


//    public List<ClubMember> getAllMembers() {
//        return clubMemberRepository.findAll();
//    }
//
//    public List<ClubMember> getMembersByClub(Long id) {
//        return clubMemberRepository.findByBookClubClubId(id);
//    }
//
//    public Optional<ClubMember> getMemberById(Long id) {
//        return clubMemberRepository.findById(id);
//    }

    public ClubMember addMember(Long clubId, Long userId) {
//        Optional<BookClub> bookClub = bookClubRepository.findById(clubId);
//        if (bookClub.isEmpty()) {
//            throw new RuntimeException("Book club not found");
//        }

        //Call User Service to check if user exists
        Boolean userExists = restTemplate.getForObject("http://localhost:8080/users/exists/{userId}" , Boolean.class, userId);

        if (Boolean.FALSE.equals(userExists)) {
//            throw new RuntimeException("User does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");

        }

        // Check if club exists
        Optional<BookClub> club = bookClubRepository.findById(clubId);
        if (club.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Club does not exist");
        }

//ADD THE USER TO THE CLUB
        ClubMember clubMember = new ClubMember();
        clubMember.setBookClub(club.get());
//        clubMember.setBookClub(new BookClub());
        clubMember.setUserId(userId);
//        clubMember.getBookClub().setClubId(clubId);
        return clubMemberRepository.save(clubMember);
    }

    public void removeMember(Long id) {
        clubMemberRepository.deleteById(id);
    }

//    public String addMember(ClubMember clubMember) {
//        return "user added successfully";
//    }
}
