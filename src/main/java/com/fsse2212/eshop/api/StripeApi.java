package com.fsse2212.eshop.api;


import com.fsse2212.eshop.config.EnvConfig;
import com.fsse2212.eshop.data.transaction.TransactionDetailData;
import com.fsse2212.eshop.data.transactionproduct.TransactionProductDetailData;
import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.service.TransactionService;
import com.stripe.Stripe;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl})
public class StripeApi {
    private final TransactionService transactionService;

    public StripeApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/create-checkout-session/{tid}")
    public String createStipeCheckoutSession(JwtAuthenticationToken jwtToken,
                                                   @PathVariable Integer tid) {
        Stripe.apiKey = "sk_test_51Mqpt2G7HFtLdXezIBkHxYSXAoX98OLnAkW0ooWFb9vCZOkuuyU46Qt4OQn2zOYbF3YZYDPYna3lZz9QCmiwE0Zq00088M8fvP";
        TransactionDetailData transactionDetailData = transactionService.getTransaction(new FirebaseUserData(jwtToken), tid);
        List<SessionCreateParams.LineItem> lineItemList = new ArrayList<>();
        for (TransactionProductDetailData data: transactionDetailData.getProduct()){
            SessionCreateParams.LineItem lineItem = new SessionCreateParams.LineItem.Builder()
                    .setQuantity(data.getQuantity().longValue())
                    .setPrice(data.getProduct().getPriceId())
                    .build();
            lineItemList.add(lineItem);
        }
        String baseUrl = EnvConfig.prodBaseUrl;
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(baseUrl + "/#/checkout/success?tid=" + tid)
                        .setCancelUrl(baseUrl + "/#/checkout/cancelled")
                        .addAllLineItem(lineItemList)
                        .build();
        try {
            Session session = Session.create(params);
            return session.getUrl();
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }
}
