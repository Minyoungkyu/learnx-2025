package com.tmd.learn.domain.member.member.dto;

import com.tmd.learn.domain.member.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class MemberDto {
    @NonNull
    private long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private LocalDateTime modifyDate;
    @NonNull
    private String name;
    @NonNull
    private String profileImgUrl;
    @NonNull
    private List<String> authorities;
    @NonNull
    private boolean social;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.createDate = member.getCreateDate();
        this.modifyDate = member.getModifyDate();
        this.name = member.getName();
        this.profileImgUrl = member.getProfileImgUrlOrDefault();
        this.authorities = member.getAuthoritiesAsStringList();
        this.social = member.isSocial();
    }
}
