package com.fsse2212.eshop.service;

import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.data.user.UserEntity;

public interface UserService {
    UserEntity getEntityByFirebaseUser(FirebaseUserData userData);
}
