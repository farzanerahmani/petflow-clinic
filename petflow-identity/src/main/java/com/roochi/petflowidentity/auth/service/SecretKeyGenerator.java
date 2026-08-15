package com.roochi.petflowidentity.auth.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public class SecretKeyGenerator {
    public static  void main(String[] args){
        String key = Encoders.BASE64.encode(
                Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded()
        );
        System.out.println(key);
    }
}
