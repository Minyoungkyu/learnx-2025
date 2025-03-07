package com.tmd.learn.domain.post.post.repository;

import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    List<Post> findByPublishedOrderByIdDesc(boolean published);

    Optional<Post> findTop1ByAuthorAndPublishedAndTitleOrderByIdDesc(Member author, boolean published, String title);
}
