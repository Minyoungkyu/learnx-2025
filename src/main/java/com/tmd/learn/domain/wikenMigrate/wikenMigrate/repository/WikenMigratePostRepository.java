package com.tmd.learn.domain.wikenMigrate.wikenMigrate.repository;

import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WikenMigratePostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorAndAddi1(Member author, String username);
}
