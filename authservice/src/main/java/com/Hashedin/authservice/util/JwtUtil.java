package com.Hashedin.authservice.util;


import com.Hashedin.authservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 1000 * 60 * 60;


    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        return Jwts.builder().setSubject(user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setIssuedAt(new Date()).addClaims(claims)
                .signWith(key).compact();
    }

    public String validateToken(String tokne) {
        return Jwts.parserBuilder().
                setSigningKey(key).build().
                parseClaimsJws(tokne).getBody().getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role",String.class);
    }
}
