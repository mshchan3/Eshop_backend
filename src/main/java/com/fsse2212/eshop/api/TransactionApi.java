package com.fsse2212.eshop.api;

import com.fsse2212.eshop.config.EnvConfig;
import com.fsse2212.eshop.data.transaction.TransactionDetailData;
import com.fsse2212.eshop.data.transaction.TransactionResponseDto;
import com.fsse2212.eshop.data.transaction.TransactionStatusResponseDto;
import com.fsse2212.eshop.jwt.util.JwtUtil;
import com.fsse2212.eshop.service.TransactionService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin({EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl})
public class TransactionApi {
    private final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createTransaction(JwtAuthenticationToken jwtToken){
        return new TransactionResponseDto(transactionService.createTransaction(JwtUtil.getFirebaseUser(jwtToken)));
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransaction(JwtAuthenticationToken jwtToken,
                                                 @PathVariable Integer tid){
        return new TransactionResponseDto(transactionService.getTransaction(
                JwtUtil.getFirebaseUser(jwtToken), tid));
    }

    @PatchMapping("/{tid}/pay")
    public TransactionStatusResponseDto payment(JwtAuthenticationToken jwtToken,
                                                @PathVariable Integer tid){
        return new TransactionStatusResponseDto(transactionService.payment(
                JwtUtil.getFirebaseUser(jwtToken), tid));
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finish(JwtAuthenticationToken jwtToken,
                                         @PathVariable Integer tid){
        return new TransactionResponseDto(transactionService.finish(
                JwtUtil.getFirebaseUser(jwtToken), tid));
    }

    @GetMapping("/get-all")
    public List<TransactionResponseDto> getAllByUser(JwtAuthenticationToken jwtToken){
        List<TransactionResponseDto> list = new ArrayList<>();
        for (TransactionDetailData data: transactionService.getAllTransaction(JwtUtil.getFirebaseUser(jwtToken))) {
            list.add(new TransactionResponseDto(data));
        }
        return list;
    }
}
