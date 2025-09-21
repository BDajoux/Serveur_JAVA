package com.uca.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class doLogin {

    private final static String TOKEN = "RcEqEHfapLkIO1b6Kx8amBqw7AvXs5dKDotqq4ZzqJTEPWpdzM";

    public static Map<String, String> introspect(String token){
        Claims claims = Jwts.parser().setSigningKey(TOKEN).parseClaimsJws(token).getBody();
        Map<String, String> map = new HashMap<>();
        map.put("username", claims.get("username", String.class));
        map.put("id", claims.get("id", String.class));
        map.put("fname", claims.get("fname", String.class));
        map.put("lname", claims.get("lname", String.class));
        map.put("role", claims.get("role", String.class));
        map.put("uuid", claims.get("uuid", String.class));

        return map;
    }

    public static String createToken(String username, String fname, String lname, String id, String role,String uuid){
        Map<String, String> content = new HashMap<>();
        content.put("username", username);
        content.put("id", id);
        content.put("fname", fname);
        content.put("lname", lname);
        content.put("role", role);
        content.put("uuid", uuid);

        return Jwts.builder().setClaims(content)
            .setId(UUID.randomUUID().toString())
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
            .signWith(SignatureAlgorithm.HS256, TOKEN)
            .compact();
    }
}
