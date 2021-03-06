package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.UserEntity;
import com.site2go.dao.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<UserEntity> implements UserRepository {
    @Override
    public UserEntity findByEmail(String email) {
        return this.createNamedQuery("findUserByEmail")
            .setParameter("email", email)
            .getSingleResult();
    }
}
