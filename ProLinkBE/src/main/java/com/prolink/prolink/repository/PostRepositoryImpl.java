package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostJpaRepo {
    private final PostJpaRepo postJpaRepo;

    public PostRepositoryImpl(PostJpaRepo postJpaRepo){
        this.postJpaRepo=postJpaRepo;
    }

    @Override
    public Optional<Post> findByIdPost(Long idPost){
      return postJpaRepo.findByIdPost(idPost)
              .map(this :: domain);
    }
}
