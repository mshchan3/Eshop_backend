package com.fsse2212.eshop.data.cartitem;

public class CartItemStatusResponseDto {
    private String result;

    public CartItemStatusResponseDto(CartItemStatusData data) {
        this.result = data.getResult();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
