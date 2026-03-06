package org.birnbickl.fitness.security;



import org.birnbickl.fitness.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;


    // Methode zur Generierung eines JWT
    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());

        return Jwts.builder().setClaims(claims).setSubject(user.getEmail()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSigningkey(), SignatureAlgorithm.HS256).compact();

    }

    // Methode erstellt den kryptografischen Schlüssel zum Signieren des JWT.
    private Key getSigningkey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Hilfsmethode für isTokenValid und extractEmail
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningkey()).build().parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String emailFromToken = extractEmail(token);
        final boolean tokenNotExpired = extractAllClaims(token).getExpiration().after(new Date());
        return (emailFromToken.equals(userDetails.getUsername()) && tokenNotExpired);
    }



}


