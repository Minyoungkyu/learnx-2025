package com.tmd.learn.domain.post.post.repository;

import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.post.post.entity.Post;
import com.tmd.learn.standard.base.KwTypeV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> findByKw(KwTypeV1 kwType, String kw, Member author, Boolean published, Boolean listed, Pageable pageable);
}
