package com.codestates.flyaway.domain.record.repository;

import com.codestates.flyaway.domain.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    Optional<Record> findByMemberIdAndDate(long memberId, String date);
}

