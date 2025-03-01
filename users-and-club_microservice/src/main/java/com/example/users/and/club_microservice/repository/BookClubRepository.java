package com.example.users.and.club_microservice.repository;

import com.example.users.and.club_microservice.entity.BookClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClubRepository extends JpaRepository<BookClub, Long> {
}
