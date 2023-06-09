package shop.mtcoding.security_app.controller;

import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.security_app.core.auth.MyUserDetails;
import shop.mtcoding.security_app.dto.ResponseDTO;
import shop.mtcoding.security_app.dto.UserRequest;
import shop.mtcoding.security_app.dto.UserResponse;
import shop.mtcoding.security_app.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> userCheck(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        String username = myUserDetails.getUser().getUsername();
        String role = myUserDetails.getUser().getRole();
        return ResponseEntity.ok().body(username + " : " + role);

    }

    @GetMapping("/")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(UserRequest.joinDTO joinDTO) {
        UserResponse.joinDTO data = userService.회원가입(joinDTO);
        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(data);
        return ResponseEntity.ok().body(responseDTO);
    }

}
