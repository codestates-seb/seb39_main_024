package com.codestates.flyaway.domain.video.repository;

import com.codestates.flyaway.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query(value = "select * from video where member_id = :memberId order by id desc limit 4", nativeQuery = true)
    List<Video> findRecent(@Param("memberId") long memberId);

    Optional<Video> findByVideoIdAndMemberId(long videoId, long memberId);
}
