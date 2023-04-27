package com.fsse2212.eshop.data.transaction;

public class TransactionStatusResponseDto {
    private String result;

    public TransactionStatusResponseDto(TransactionStatusData data) {
        this.result = data.getResult();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
