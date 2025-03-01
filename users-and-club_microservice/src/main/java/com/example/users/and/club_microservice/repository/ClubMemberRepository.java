package com.example.users.and.club_microservice.repository;

import com.example.users.and.club_microservice.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
//    List<ClubMember> findByBookClubClubId(Long clubId);
}
