package com.tmd.learn.domain.post.postLike.entity;

import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.post.post.entity.Post;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EqualsAndHashCode
@Getter
public class PostLikeId implements Serializable {
    @ManyToOne(fetch = LAZY)
    private Post post;
    @ManyToOne(fetch = LAZY)
    private Member member;
}
