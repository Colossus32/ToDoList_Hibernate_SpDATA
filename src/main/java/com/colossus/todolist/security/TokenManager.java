package com.colossus.todolist.security;

import java.util.Date;

public class TokenManager {

    private final String secretKey;

    public TokenManager(String secretKey) {
        this.secretKey = secretKey;
    }

    public String createToken (TokenPayload payload){

        String mixedPayload = createMixedTokenPayload(payload);
        String signature = createSignature(mixedPayload);

        return String.format("%s#%s", mixedPayload, signature);
    }

    private String createMixedTokenPayload (TokenPayload payload){

        String id = String.valueOf(payload.getUserId());
        String email = String.valueOf(payload.getEmail());
        String timeOfCreation = String.valueOf(payload.getTimeOfCreation().getTime());

        return String.format("%s#%s#%s#", id,email,timeOfCreation);
    }

    private String createSignature (String mixedPayload){

        return "" + mixedPayload.charAt(0) + secretKey.charAt(1) + mixedPayload.charAt(2)
                + secretKey.charAt(3) + mixedPayload.charAt(mixedPayload.length() -1);
    }

    public boolean verifyToken (String token){

        TokenPayload payload = extractPayload(token);
        String trustedToken = createToken(payload);

        return token.equals(trustedToken);
    }

    public TokenPayload extractPayload (String token){

        String[] tokenParts = token.split("#");

        long id = Long.parseLong(tokenParts[0]);
        String email = tokenParts[1];
        Date timeOfCreation = new Date(Long.parseLong(tokenParts[2]));

        return new TokenPayload(id,email, timeOfCreation);
    }
}
