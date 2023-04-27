package com.fsse2212.eshop.service;

import com.fsse2212.eshop.data.cartitem.CartItemEntity;
import com.fsse2212.eshop.data.cartitem.CartItemStatusData;
import com.fsse2212.eshop.data.cartitem.CartItemData;
import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.data.user.UserEntity;

import java.util.List;

public interface CartItemService {
    CartItemStatusData addCartItem(FirebaseUserData userData, Integer pid, Integer quantity);

    List<CartItemData> getUserCart(FirebaseUserData userData);

    CartItemData updateCartQuantity(FirebaseUserData userData, Integer pid, Integer quantity);

    CartItemStatusData deleteCartItem(FirebaseUserData userData, Integer pid);

    void deleteAllCartItem(UserEntity user);

    List<CartItemEntity> getAllCartItem(UserEntity user);
}
