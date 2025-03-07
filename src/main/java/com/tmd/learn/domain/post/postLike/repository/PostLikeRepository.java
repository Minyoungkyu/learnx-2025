package com.tmd.learn.domain.post.postLike.repository;

import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.post.post.entity.Post;
import com.tmd.learn.domain.post.postLike.entity.PostLike;
import com.tmd.learn.domain.post.postLike.entity.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    List<PostLike> findByIdPostInAndIdMember(List<Post> posts, Member member);
}
