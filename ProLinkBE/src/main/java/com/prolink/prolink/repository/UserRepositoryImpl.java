package com.prolink.prolink.repository;
import com.prolink.prolink.domain.UserD;
import com.prolink.prolink.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepo userJpaRepo;

    public UserRepositoryImpl(UserJpaRepo userJpaRepo){
        this.userJpaRepo=userJpaRepo;
    }

    @Override
    public Optional<UserD> findByEmail(String email) {
        return userJpaRepo.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public UserD save(UserD userD) {
        User entity = toEntity(userD);
        User saved = userJpaRepo.save(entity);
        return toDomain(saved);
    }


    private UserD toDomain(User user) {
        return new UserD(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }


    private User toEntity(UserD userD) {
        User user = new User();
        user.setId(userD.getId());
        user.setEmail(userD.getEmail());
        user.setPassword(userD.getPassword());
        return user;
    }
}
