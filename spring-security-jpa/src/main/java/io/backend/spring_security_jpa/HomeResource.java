package io.backend.spring_security_jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
    @GetMapping("/")
    public String home(){
        return ("<h1> Welcome </h1>");
    }
    @GetMapping("/user")
    public String user(){
        return ("<h1> Welcome user </h1>");
    }
    @GetMapping("/admin")
    public String admin(){
        return ("<h1> Welcome admin </h1>");
    }

}
