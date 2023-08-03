package com.promet.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.promet.exception.AuthManagerException;
import com.promet.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class JwtTokenProvider {
    @Value("${secretkey}")
    String secretkey;
    @Value("${audience}")
    String audience;
    @Value("${issuer}")
    String issuer;

    public Optional<String> createToken(Long id, List<String> roles) {
        String token = null;
        Date date = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        try {
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id", id)
                    .withClaim("roles", Arrays.asList(roles.toArray()))
                    .sign(Algorithm.HMAC512(secretkey));
            return Optional.of(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
    public Optional<String> getActivationCodeFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretkey);
            JWTVerifier verifier = JWT.require(algorithm).withAudience(audience).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new RuntimeException("hata");
            }
            String activationCode = decodedJWT.getClaim("activationCode").toString();
            return Optional.of(activationCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Optional<Long> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretkey);
            JWTVerifier verifier = JWT.require(algorithm).withAudience(audience).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new RuntimeException("hata");
            }
            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("hata");
        }
    }

    public List<String> getRolesFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretkey);
            JWTVerifier verifier = JWT.require(algorithm).withAudience(audience).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new RuntimeException("hata");
            }
            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
            return roles;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("hata");
        }
    }

    //----------------------------------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
