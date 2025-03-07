package com.tmd.learn.domain.post.post.service;

import com.tmd.learn.domain.base.genFile.service.GenFileService.GenFileService;
import com.tmd.learn.domain.member.member.entity.Member;
import com.tmd.learn.domain.post.post.entity.Post;
import com.tmd.learn.domain.post.post.entity.PostDetail;
import com.tmd.learn.domain.post.post.repository.PostDetailRepository;
import com.tmd.learn.domain.post.post.repository.PostRepository;
import com.tmd.learn.domain.post.postLike.entity.PostLike;
import com.tmd.learn.domain.post.postLike.repository.PostLikeRepository;
import com.tmd.learn.global.rqCache.RqCache;
import com.tmd.learn.global.rsData.RsData;
import com.tmd.learn.standard.base.KwTypeV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;
    private final PostLikeRepository postLikeRepository;
    private final GenFileService genFileService;
    private final RqCache rqCache;

    @Transactional
    public Post write(Member author, String title, String body, boolean published, boolean listed) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .published(published)
                .listed(listed)
                .build();

        postRepository.save(post);

        post.setDetailBody(
                PostDetail
                        .builder()
                        .post(post)
                        .name("common__body")
                        .build()
        );

        saveBody(post, body);

        return post;
    }

    private void saveBody(Post post, String body) {
        PostDetail detailBody = post.getDetailBody();

        if (detailBody == null) {
            detailBody = findDetailOrMake(post, "common__body");
            post.setDetailBody(detailBody);
        }

        if (body.equals(detailBody.getVal())) return;
        if (!post.isNew()) {
            post.setModified();
        }

        detailBody.setVal(body);
    }

    private PostDetail findDetailOrMake(Post post, String name) {
        Optional<PostDetail> opDetailBody = postDetailRepository.findByPostAndName(post, name);

        PostDetail detailBody = opDetailBody.orElseGet(() -> postDetailRepository.save(
                PostDetail.builder()
                        .post(post)
                        .name(name)
                        .build()
        ));

        return detailBody;
    }

    public List<Post> findByPublished(boolean published) {
        return postRepository.findByPublishedOrderByIdDesc(published);
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void delete(Post post) {
        genFileService.deleteByRel(post);
        postRepository.delete(post);
    }

    @Transactional
    public void edit(Post post, String title, List<String> tagContents, String body, boolean published, boolean listed) {
        post.setTitle(title);
        post.setPublished(published);
        post.setListed(listed);
        post.setTagContents(tagContents);

        editBody(post, body);
    }

    public boolean canRead(Member actor, Post post) {
        if (post == null) return false;

        if (post.isPublished()) return true; // 공개글이면 가능

        if (actor == null) return false;
        if (actor.isAdmin()) return true; // 관리자이면 가능

        return actor.equals(post.getAuthor()); // 그것도 아니라면 본인이 쓴 글이여야 함
    }

    public boolean canEdit(Member actor, Post post) {
        if (actor == null) return false;
        if (post == null) return false;

        return actor.equals(post.getAuthor()); // 무조건 본인만 가능
    }

    public Boolean canDelete(Member actor, Post post) {
        if (actor == null) return false;
        if (post == null) return false;

        if (actor.isAdmin()) return true; // 관리자이면 가능
        return actor.equals(post.getAuthor()); // 본인이면 가능
    }

    public Boolean canLike(Member actor, Post post) {
        if (actor == null) return false;
        if (post == null) return false;

        Map<Long, Boolean> likeMap = rqCache.get("likeMap");
        if (likeMap != null && likeMap.containsKey(post.getId())) return !likeMap.get(post.getId());

        return !post.hasLike(actor);
    }

    public Boolean canCancelLike(Member actor, Post post) {
        if (actor == null) return false;
        if (post == null) return false;

        Map<Long, Boolean> likeMap = rqCache.get("likeMap");
        if (likeMap != null && likeMap.containsKey(post.getId())) return likeMap.get(post.getId());

        return post.hasLike(actor);
    }

    @Transactional
    public void like(Member actor, Post post) {
        post.addLike(actor);
    }

    @Transactional
    public void cancelLike(Member actor, Post post) {
        post.deleteLike(actor);
    }


    private List<PostLike> findLikesByPostInAndMember(List<Post> posts, Member member) {
        return postLikeRepository.findByIdPostInAndIdMember(posts, member);
    }

    @Transactional
    public RsData<Post> findTempOrMake(Member author) {
        AtomicBoolean isNew = new AtomicBoolean(false);

        Post post = postRepository.findTop1ByAuthorAndPublishedAndTitleOrderByIdDesc(
                author,
                false,
                "임시글"
        ).orElseGet(() -> {
            isNew.set(true);
            return write(author, "임시글", "", false, false);
        });

        return RsData.of(
                isNew.get() ? "임시글이 생성되었습니다." : "%d번 임시글을 불러왔습니다.".formatted(post.getId()),
                post
        );
    }

    public Page<Post> findByKw(KwTypeV1 kwType, String kw, Member author, Boolean published, Boolean listed, Pageable pageable) {
        return postRepository.findByKw(kwType, kw, author, published, listed, pageable);
    }

    @Transactional
    public void editBody(Post post, String body) {
        saveBody(post, body);
    }

    public void loadLikeMap(List<Post> posts, Member member) {
        List<PostLike> likes = findLikesByPostInAndMember(posts, member);

        Map<Long, Boolean> likeMap_ = likes
                .stream()
                .collect(
                        HashMap::new,
                        (map, like) -> map.put(like.getPost().getId(), true),
                        HashMap::putAll
                );

        Map<Long, Boolean> likeMap = posts
                .stream()
                .collect(
                        HashMap::new,
                        (map, post) -> map.put(post.getId(), likeMap_.getOrDefault(post.getId(), false)),
                        HashMap::putAll
                );

        rqCache.put("likeMap", likeMap);
    }
}
