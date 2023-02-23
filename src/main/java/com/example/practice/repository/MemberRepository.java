package com.example.practice.repository;


import com.example.practice.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member memberDTO);

    Optional<Member> findByUserId(String id);

    List<Member> findAll();

}
