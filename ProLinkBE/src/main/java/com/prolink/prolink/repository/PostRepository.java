package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
   Optional<Post> findByIdPost(Long idPost);
   List<Post> findByProfileId(Long profileId);
   Post save(Post post);
}
