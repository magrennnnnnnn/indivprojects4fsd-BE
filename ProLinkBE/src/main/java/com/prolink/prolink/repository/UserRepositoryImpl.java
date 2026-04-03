package com.prolink.prolink.repository;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepo userJpaRepo;

    public UserRepositoryImpl(UserJpaRepo userJpaRepo){
        this.userJpaRepo=userJpaRepo;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepo.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = userJpaRepo.save(entity);
        return toDomain(saved);
    }


    private User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }


    private UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
