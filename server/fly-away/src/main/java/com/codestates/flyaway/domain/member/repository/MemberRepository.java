package com.codestates.flyaway.domain.member.repository;

import com.codestates.flyaway.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select distinct m from Member m join fetch m.records where m.id  = :memberId")
    Optional<Member> findByIdFetch(@Param("memberId") long memberId);

}
