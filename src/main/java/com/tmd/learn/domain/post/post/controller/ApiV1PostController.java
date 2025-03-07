package com.tmd.learn.domain.post.post.controller;

import com.tmd.learn.domain.base.genFile.dto.GenFileDto;
import com.tmd.learn.domain.base.genFile.entity.GenFile.GenFile;
import com.tmd.learn.domain.base.genFile.service.GenFileService.GenFileService;
import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.member.member.service.MemberService;
import com.tmd.learn.domain.post.post.dto.PostDto;
import com.tmd.learn.domain.post.post.dto.PostWithBodyDto;
import com.tmd.learn.domain.post.post.entity.Post;
import com.tmd.learn.domain.post.post.service.PostService;
import com.tmd.learn.global.app.AppConfig;
import com.tmd.learn.global.exceptions.GlobalException;
import com.tmd.learn.global.rq.Rq;
import com.tmd.learn.global.rsData.RsData;
import com.tmd.learn.standard.base.Empty;
import com.tmd.learn.standard.base.KwTypeV1;
import com.tmd.learn.standard.base.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.*;


@RestController
@RequestMapping(value = "/api/v1/posts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "ApiV1PostController", description = "글 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1PostController {
    private final PostService postService;
    private final MemberService memberService;
    private final GenFileService genFileService;
    private final Rq rq;


    public record MakeTempResponseBody(@NonNull PostDto item) {
    }

    @Transactional
    @PostMapping(value = "/temp", consumes = ALL_VALUE)
    @Operation(summary = "임시 글 생성")
    public RsData<MakeTempResponseBody> makeTemp() {
        RsData<Post> findTempOrMakeRsData = postService.findTempOrMake(rq.getMember());

        return findTempOrMakeRsData.newDataOf(
                new MakeTempResponseBody(
                        postToDto(findTempOrMakeRsData.getData())
                )
        );
    }


    public record GetPostsResponseBody(@NonNull PageDto<PostDto> itemPage) {
    }

    @GetMapping(value = "", consumes = ALL_VALUE)
    @Operation(summary = "글 다건조회")
    public RsData<GetPostsResponseBody> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "ALL") KwTypeV1 kwType
    ) {

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));
        Page<Post> itemPage = postService.findByKw(kwType, kw, null, true, true, pageable);

        if (rq.isLogin()) {
            postService.loadLikeMap(itemPage.getContent(), rq.getMember());
        }

        Page<PostDto> _itemPage = itemPage.map(this::postToDto);

        return RsData.of(
                new GetPostsResponseBody(
                        new PageDto<>(_itemPage)
                )
        );
    }


    public record GetMineResponseBody(@NonNull PageDto<PostDto> itemPage) {
    }

    @GetMapping(value = "/mine", consumes = ALL_VALUE)
    @Operation(summary = "내글 다건조회")
    public RsData<GetMineResponseBody> getMine(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "ALL") KwTypeV1 kwType
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));
        Page<Post> itemPage = postService.findByKw(kwType, kw, rq.getMember(), null, null, pageable);

        if (rq.isLogin()) {
            postService.loadLikeMap(itemPage.getContent(), rq.getMember());
        }

        Page<PostDto> _itemPage = itemPage.map(this::postToDto);

        return RsData.of(
                new GetMineResponseBody(
                        new PageDto<>(_itemPage)
                )
        );
    }


    public record GetByAuthorResponseBody(@NonNull PageDto<PostDto> itemPage) {
    }

    @GetMapping(value = "/byAuthor/{authorId}", consumes = ALL_VALUE)
    @Operation(summary = "특정 회원의글 다건조회")
    public RsData<GetByAuthorResponseBody> getByAuthor(
            @PathVariable long authorId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "ALL") KwTypeV1 kwType
    ) {
        Member member = memberService.findById(authorId).orElseThrow(GlobalException.E404::new);

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));
        Page<Post> itemPage = postService.findByKw(kwType, kw, member, null, null, pageable);

        if (rq.isLogin()) {
            postService.loadLikeMap(itemPage.getContent(), rq.getMember());
        }

        Page<PostDto> _itemPage = itemPage.map(this::postToDto);

        return RsData.of(
                new GetByAuthorResponseBody(
                        new PageDto<>(_itemPage)
                )
        );
    }

    public record GetPostResponseBody(@NonNull PostWithBodyDto item) {
    }

    @GetMapping(value = "/{id}", consumes = ALL_VALUE)
    @Operation(summary = "글 단건조회")
    public RsData<GetPostResponseBody> getPost(
            @PathVariable("id") long id
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canRead(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        PostWithBodyDto dto = postToWithBodyDto(post);

        return RsData.of(
                new GetPostResponseBody(dto)
        );
    }


    public record EditRequestBody(
            @NotBlank String title,
            @NotBlank String body,
            @NotNull List<String> tagContents,
            @NotNull boolean published,
            @NotNull boolean listed
    ) {
    }

    public record EditResponseBody(@NonNull PostWithBodyDto item) {
    }

    @PutMapping("/{id}")
    @Operation(summary = "글 편집")
    @Transactional
    public RsData<EditResponseBody> edit(
            @PathVariable("id") long id,
            @Valid @RequestBody EditRequestBody requestBody
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        System.out.println(">>> Received ID: " + id);

        if (!postService.canEdit(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        postService.edit(post, requestBody.title, requestBody.tagContents, requestBody.body, requestBody.published, requestBody.listed);

        return RsData.of(
                "%d번 글이 수정되었습니다.".formatted(id),
                new EditResponseBody(postToWithBodyDto(post))
        );
    }


    @PutMapping(value = "/{id}/mainVideo/{fileNo}", consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "글의 비디오 업로드")
    @Transactional
    public RsData<Empty> uploadVideo(
            @PathVariable long id,
            @PathVariable int fileNo,
            @RequestParam("file") MultipartFile file
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canEdit(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        genFileService.save(post, "common", "mainVideo", fileNo, file);

        return RsData.OK;
    }


    @DeleteMapping(value = "/{id}/mainVideo/{fileNo}", consumes = ALL_VALUE)
    @Operation(summary = "글의 비디오 삭제")
    @Transactional
    public RsData<Empty> deleteVideo(
            @PathVariable long id,
            @PathVariable int fileNo
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canEdit(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        genFileService.delete(post, "common", "mainVideo", fileNo);

        return RsData.OK;
    }


    public record EditBodyRequestBody(@NotBlank String body) {
    }

    @PutMapping("/{id}/body")
    @Operation(summary = "글 본문 편집")
    @Transactional
    public RsData<Empty> editBody(
            @PathVariable long id,
            @Valid @RequestBody EditBodyRequestBody requestBody
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canEdit(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        postService.editBody(post, requestBody.body);

        return RsData.of(
                "저장"
        );
    }


    public record GetPostBodyResponseBody(
            @NotNull LocalDateTime modifyDate,
            @NotNull String body
    ) {
    }

    @GetMapping(value = "/{id}/body", consumes = ALL_VALUE)
    @Operation(summary = "글(본문) 단건조회")
    @Transactional
    public RsData<GetPostBodyResponseBody> getPostBody(
            @PathVariable long id,
            @RequestParam LocalDateTime lastModifyDate
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canRead(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        if (post.getModifyDate().isBefore(lastModifyDate) || post.getModifyDate().equals(lastModifyDate))
            throw new GlobalException("400-1", "변경사항이 없습니다.");

        return RsData.of(
                "새 본문을 불러옵니다.",
                new GetPostBodyResponseBody(
                        post.getModifyDate(),
                        post.getDetailBody().getVal()
                )
        );
    }


    public record GetPostFilesResponseBody(
            @NonNull List<GenFileDto> items
    ) {
    }

    @GetMapping(value = "/{id}/files", consumes = ALL_VALUE)
    @Operation(summary = "글의 파일들 조회")
    @Transactional
    public RsData<GetPostFilesResponseBody> getPostFiles(
            @PathVariable long id
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canRead(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        List<GenFile> files = genFileService.findByRel(post);

        return RsData.of(
                new GetPostFilesResponseBody(
                        files
                                .stream()
                                .map(GenFileDto::new)
                                .toList()
                )
        );
    }


    @DeleteMapping(value = "/{id}", consumes = ALL_VALUE)
    @Operation(summary = "글 삭제")
    @Transactional
    public RsData<Empty> delete(
            @PathVariable long id
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canDelete(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        postService.delete(post);

        return RsData.of(
                "%d번 글이 삭제되었습니다.".formatted(id)
        );
    }


    public record LikeResponseBody(@NonNull PostDto item) {
    }

    @PostMapping(value = "/{id}/like", consumes = ALL_VALUE)
    @Transactional
    @Operation(summary = "글 추천")
    public RsData<LikeResponseBody> like(
            @PathVariable long id
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canLike(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        postService.like(rq.getMember(), post);

        PostDto dto = postToDto(post);

        return RsData.of(
                "%d번 글을 추천하였습니다.".formatted(id),
                new LikeResponseBody(dto)
        );
    }


    public record CancelLikeResponseBody(@NonNull PostDto item) {
    }

    @DeleteMapping(value = "/{id}/cancelLike", consumes = ALL_VALUE)
    @Operation(summary = "글 추천취소")
    @Transactional
    public RsData<CancelLikeResponseBody> cancelLike(
            @PathVariable long id
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canCancelLike(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        postService.cancelLike(rq.getMember(), post);

        PostDto dto = postToDto(post);

        return RsData.of(
                "%d번 글을 추천취소하였습니다.".formatted(id),
                new CancelLikeResponseBody(dto)
        );
    }

    private PostDto postToDto(Post post) {
        PostDto dto = new PostDto(post);

        loadAdditionalInfo(dto, post);

        return dto;
    }

    private PostWithBodyDto postToWithBodyDto(Post post) {
        PostWithBodyDto dto = new PostWithBodyDto(post);

        loadAdditionalInfo(dto, post);

        return dto;
    }

    private void loadAdditionalInfo(PostDto dto, Post post) {
        dto.setActorCanRead(postService.canRead(rq.getMember(), post));
        dto.setActorCanEdit(postService.canEdit(rq.getMember(), post));
        dto.setActorCanDelete(postService.canDelete(rq.getMember(), post));
        dto.setActorCanLike(postService.canLike(rq.getMember(), post));
        dto.setActorCanCancelLike(postService.canCancelLike(rq.getMember(), post));
    }
}
