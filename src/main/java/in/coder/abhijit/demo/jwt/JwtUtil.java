package in.coder.abhijit.demo.jwt;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKeyBase64;

    @Value("${jwt.expiration-time}")
    private long JWT_EXPIRATION_TIME;

    // Decode the Base64 encoded key to SecretKey
    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");
    }
    
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_TIME))
            .signWith(getSecretKey(), SignatureAlgorithm.HS512)
            .compact();
    }
}

