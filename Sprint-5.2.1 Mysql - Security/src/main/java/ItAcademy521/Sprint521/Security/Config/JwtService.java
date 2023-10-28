package ItAcademy521.Sprint521.Security.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    public String getToken(UserDetails user) {

        return getToken(new HashMap<>(),user);
    }

    public String getToken(Map<String,Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){

        final Claims claims = getClaims(token);

            return claimsResolver.apply(claims);
    }
    public String getUsernameFromToken(String token) {

        return getClaim(token,Claims::getSubject);
    }

    private Claims getClaims(String token) {


        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpires(token);

    }

    private Date getExpiration(String token){

        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpires(String token){

        return getExpiration(token).before(new Date());

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
