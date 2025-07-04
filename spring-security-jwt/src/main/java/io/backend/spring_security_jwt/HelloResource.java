package io.backend.spring_security_jwt;

import io.backend.spring_security_jwt.JWT.JWTUtil;
import io.backend.spring_security_jwt.Services.MyUserDetailService;
import io.backend.spring_security_jwt.models.AuthenticationRequest;
import io.backend.spring_security_jwt.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping("/hello")
    public String hello(){
        return "Hello world";
    }
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationRequest
            (@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect userName and Password", e);
        }
        final UserDetails userDetails=
                myUserDetailService.loadUserByUsername
                (authenticationRequest.getUserName());
        final String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
