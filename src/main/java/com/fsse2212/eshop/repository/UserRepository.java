package com.fsse2212.eshop.repository;

import com.fsse2212.eshop.data.user.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query(
            value = "SELECT * FROM FSSE2212_Eshop_Project.user where firebase_uid = ?1",
            nativeQuery = true
    )
    Optional<UserEntity> getUserEntityByFirebaseUid(String firebaseUid);
}
