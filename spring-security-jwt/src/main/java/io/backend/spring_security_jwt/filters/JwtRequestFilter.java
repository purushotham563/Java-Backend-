package io.backend.spring_security_jwt.filters;

import io.backend.spring_security_jwt.JWT.JWTUtil;
import io.backend.spring_security_jwt.Services.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    protected void
    doFilterInternal(HttpServletRequest request,
                     HttpServletResponse response,
                     FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader=
                request.getHeader("Authorization");
        String userName=null;
        String jwt=null;
        if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")){
            jwt=authorizationHeader.substring(7);
            userName=jwtUtil.extractUserName(jwt);
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=
                    this.myUserDetailService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken
                                (userDetails,null,userDetails.
                                        getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);

    }

}
