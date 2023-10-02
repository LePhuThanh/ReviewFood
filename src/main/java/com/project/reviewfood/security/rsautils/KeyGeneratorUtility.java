package com.project.reviewfood.security.rsautils;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

//Compare normally encode JWT and encode JWT = RSA
//A regular JWT is encrypted with a secret key using the HMAC encryption algorithm (ex: HS256).

//Generate pair key RSA (public key & private key)
@Component
public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey(){
        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Long 2048 bit
            keyPair = keyPairGenerator.generateKeyPair();
        }catch (Exception e){
            throw new IllegalStateException();
        }
        return keyPair;
    }
}

// Test RSA https://www.devglan.com/online-tools/rsa-encryption-decryption
