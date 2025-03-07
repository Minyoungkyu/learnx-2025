package com.tmd.learn.domain.test.test.controller;

import com.tmd.learn.domain.base.genFile.service.GenFileService.GenFileService;
import com.tmd.learn.domain.member.member.service.MemberService;
import com.tmd.learn.domain.post.post.service.PostService;
import com.tmd.learn.global.rq.Rq;
import com.tmd.learn.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/v1/test", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "test", description = "글 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1TestController {
    private final PostService postService;
    private final MemberService memberService;
    private final GenFileService genFileService;
    private final Rq rq;


    public record TestResponseBody(@NonNull Long id) {
    }

    @GetMapping(value = "/test", consumes = ALL_VALUE)
    @Operation(summary = "임시 글 생성")
    public RsData<TestResponseBody> test() {

        Long id = 1L;

        rq.getMember().getId();

        return RsData.of (
                new TestResponseBody(
                       id
                )
        );
    }
}
