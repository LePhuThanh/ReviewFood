package com.project.reviewfood.services.Auth;


import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.project.reviewfood.entities.User;
import com.project.reviewfood.handlers.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import com.nimbusds.jwt.SignedJWT;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;
    private RSAPublicKey publicKey;


    public String generateJwt(Authentication auth) {
        Instant now = Instant.now();
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("seft")
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofMinutes(60 * 24))) // 1 day
                .subject(auth.getName())
                .claim("roles", scope)
                .build();
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    // EXTRACT

    // Extract all information from JWT via function return Map<String, Object>
    public Map<String, Object> extractAllClaims(String token) throws Exception {
        JWSObject jwsObject = JWSObject.parse(token);
        RSASSAVerifier verifier = new RSASSAVerifier(publicKey);
        if (jwsObject.verify(verifier)) {
            return jwsObject.getPayload().toJSONObject();
        }
        throw new Exception("Token verification failed.");
    }
    // Extract username from JWT
    public String extractUsername(String token) throws Exception {
        Map<String, Object> claims = extractAllClaims(token);
        return claims.get("sub").toString();
    }
    // Extract expiration of JWT
    public Date extractExpiration(String token) throws Exception {
        try {
            // Parse String JWT => SignedJWT
            SignedJWT signedJWT = SignedJWT.parse(token);
            // verify signing by public key
            if (signedJWT.verify(new RSASSAVerifier(publicKey))) {
                // expiration time
                return signedJWT.getJWTClaimsSet().getExpirationTime();
            } else {
                throw new Exception("Failed to verify the signature of the token.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // CHECK
    public boolean isTokenExpired(String token) throws Exception {
        Date expiration = extractExpiration(token);
        Date now = new Date();
        return expiration.before(now);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String tokenUsername = extractUsername(token);
            return tokenUsername.equals(token) && !isTokenExpired(token);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (Exception e) {
            throw new CustomException("500","There are exception " + e);
        }
        return false;
    }
}
