package com.example.users.and.club_microservice.service;

import com.example.users.and.club_microservice.entity.BookClub;
import com.example.users.and.club_microservice.repository.BookClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookClubService {
    @Autowired
    private BookClubRepository bookClubRepository;

    public List<BookClub> getAllClubs() {
        return bookClubRepository.findAll();
    }

    public Optional<BookClub> getClubById(Long clubId) {
        return bookClubRepository.findById(clubId);
    }

    public BookClub createClub(BookClub bookClub) {
        return bookClubRepository.save(bookClub);
    }

    public void deleteClub(Long clubId) {
        bookClubRepository.deleteById(clubId);
    }

//    public BookClub updateBookClub(Long clubId, BookClub clubDetails) {
//        BookClub bookClub = bookClubRepository.findById(clubId).orElseThrow(() -> new RuntimeException("BookClub not found"));
//        bookClub.setname(clubDetails.getName());
//        bookClub.setDescription(clubDetails.getDescription());
//        return bookClubRepository.save(bookClub);
//    }

}
