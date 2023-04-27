package com.fsse2212.eshop.api;

import com.fsse2212.eshop.config.EnvConfig;
import com.fsse2212.eshop.data.cartitem.CartItemStatusResponseDto;
import com.fsse2212.eshop.data.cartitem.CartItemData;
import com.fsse2212.eshop.data.cartitem.CartItemResponseDto;
import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.jwt.util.JwtUtil;
import com.fsse2212.eshop.service.CartItemService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cart")
@CrossOrigin({EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl})
public class CartItemApi {
    private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public CartItemStatusResponseDto addCartItem(JwtAuthenticationToken jwtToken,
                                                 @PathVariable Integer pid,
                                                 @PathVariable Integer quantity) {

        FirebaseUserData data =  JwtUtil.getFirebaseUser((jwtToken));
        return new CartItemStatusResponseDto(cartItemService.addCartItem(data, pid, quantity));
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCart(JwtAuthenticationToken jwtToken){
        List<CartItemResponseDto> dtoList = new ArrayList<>();
        for (CartItemData data: cartItemService.getUserCart(JwtUtil.getFirebaseUser(jwtToken))) {
            dtoList.add(new CartItemResponseDto(data));
        }
        return dtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto updateCartQuantity(JwtAuthenticationToken jwtToken,
                                                  @PathVariable Integer pid,
                                                  @PathVariable Integer quantity){
        return new CartItemResponseDto(
                cartItemService.updateCartQuantity(
                        JwtUtil.getFirebaseUser(jwtToken),pid,quantity
                )
        );
    }

    @DeleteMapping("/{pid}")
    public CartItemStatusResponseDto deleteCartItem(JwtAuthenticationToken jwtToken,
                                                    @PathVariable Integer pid){
        return new CartItemStatusResponseDto(
                cartItemService.deleteCartItem(JwtUtil.getFirebaseUser(jwtToken), pid));
    }
}
