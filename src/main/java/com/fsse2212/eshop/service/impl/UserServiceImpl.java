package com.fsse2212.eshop.service.impl;

import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.data.user.UserEntity;
import com.fsse2212.eshop.repository.UserRepository;
import com.fsse2212.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUser(FirebaseUserData userData){
        Optional<UserEntity> userEntity =
                userRepository.getUserEntityByFirebaseUid(userData.getFirebaseUid());
        if (!userEntity.isPresent()){
            return userRepository.save(new UserEntity(userData));
        }
        return userEntity.get();
    }
}
