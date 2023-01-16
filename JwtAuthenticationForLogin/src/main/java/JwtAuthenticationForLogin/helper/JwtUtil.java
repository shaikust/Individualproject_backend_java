package JwtAuthenticationForLogin.helper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

//    private String SECRET_KEY = "java";
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
////    public String generateToken(Authentication authentication) {
////        Map<String, Object> claims = new HashMap<>();
////        return createToken(claims, authentication.getName());
////    }
//public String generateToken(Authentication authentication) {
//    String userName=authentication.getName();
//    Date currentDate=new Date();
////    Date expireDate=new Date(currentDate.getTime() + jwtExpirationInMs);
//    String token= Jwts.builder()
//            .setSubject(userName)
//            .setIssuedAt(new Date())
////
//    return token;
//
//    private String createToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
@Value("${app.jwt-secret}")
private String JwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String userName=authentication.getName();
        Date currentDate=new Date();
        Date expireDate=new Date(currentDate.getTime() + jwtExpirationInMs);
        String token= Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,JwtSecret).compact();
        return token;
    }
    public String getUsernameFromJWT(String token){
        Claims claims=Jwts.parser().setSigningKey(JwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(JwtSecret)
                    .parseClaimsJws(token);
            return true;
        }catch(SignatureException e){
            throw new SignatureException(e.getMessage(), e);
        }
    }
}