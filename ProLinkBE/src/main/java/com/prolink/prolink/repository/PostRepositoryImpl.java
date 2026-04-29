package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Post;
import com.prolink.prolink.entity.PostEntity;
import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepo postJpaRepo;

    public PostRepositoryImpl(PostJpaRepo postJpaRepo) {
        this.postJpaRepo = postJpaRepo;
    }

    @Override
    public Optional<Post> findByIdPost(Long idPost) {
        return postJpaRepo.findByIdPost(idPost)
                .map(this::toDomain);
    }

    @Override
    public List<Post> findByProfileId(Long profileId) {
        return postJpaRepo.findByProfileEntity_IdProfile(profileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Post save(Post post) {
        PostEntity entity = toEntity(post);
        PostEntity saved = postJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Post toDomain(PostEntity entity) {
        return new Post(
                entity.getIdPost(),
                entity.getPostTitle(),
                entity.getPostText(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getProfileEntity().getId()
        );
    }

    private PostEntity toEntity(Post post) {
        PostEntity entity = new PostEntity();

        entity.setIdPost(post.getIdPost());
        entity.setPostTitle(post.getPostTitle());
        entity.setPostText(post.getPostText());
        entity.setCreatedAt(post.getCreatedAt());
        entity.setUpdatedAt(post.getUpdatedAt());

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(post.getIdProfile());
        entity.setProfileEntity(profileEntity);

        return entity;
    }
}