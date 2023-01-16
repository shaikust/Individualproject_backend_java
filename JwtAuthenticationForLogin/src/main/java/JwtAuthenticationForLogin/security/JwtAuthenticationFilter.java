package JwtAuthenticationForLogin.security;

import JwtAuthenticationForLogin.helper.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String requestTokenheader=request.getHeader("Authorization");
//        String emailid=null;
//        String jwtToken=null;
//        if(requestTokenheader!=null&& requestTokenheader.startsWith("Bearer ")){
//            jwtToken=requestTokenheader.substring(7);
//            try {
//                emailid=this.jwtUtil.getUsernameFromJWT(jwtToken);
//
//            }catch (Exception e){
//                e.printStackTrace();
//
//            }
//            UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(emailid);
//            if(emailid!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
//               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//            else {
//                System.out.println("invalid token");
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token=getJWTfromRequest(request);
    if(StringUtils.hasText(token)&&jwtUtil.validateToken(token)){
        String userName=jwtUtil.getUsernameFromJWT(token);
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities()
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request,response);
}
    private String getJWTfromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
