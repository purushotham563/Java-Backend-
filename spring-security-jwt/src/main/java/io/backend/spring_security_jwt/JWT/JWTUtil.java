package io.backend.spring_security_jwt.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


@Service
public class JWTUtil {
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkeymy".
                    getBytes());
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object>claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }
    private String createToken(Map<String,Object>claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)).
                signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    public Boolean validateToken(String token,UserDetails userDetails){
        final String userName=extractUserName(token);
        return (userName.equals(userDetails.getUsername()))&&!isTokenExpired(token);
    }

    public <T> T extractClaim(String token , Function<Claims,T>claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().
                setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }
}
