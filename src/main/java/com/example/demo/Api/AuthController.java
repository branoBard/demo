package test.Lekarennwm.Api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.Lekarennwm.Model.User;
import test.Lekarennwm.Model.UserState;
import test.Lekarennwm.dao.UserRepository;
import test.Lekarennwm.security.jwt.JwtUtils;
import test.Lekarennwm.security.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        JwtResponseDTO jwtResponse = this.modelMapper.map(userDetails, JwtResponseDTO.class);
        jwtResponse.token = jwt;
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping(value =  "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponseDTO> registerUser(@RequestBody RegisterDTO signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.email);
        user.setFirstName(signUpRequest.firstName);
        user.setLastName(signUpRequest.lastName);
        user.setPassword(encoder.encode(signUpRequest.password));
        user.setUserState(UserState.Active);
        userRepository.save(user);
        MessageResponseDTO response = new MessageResponseDTO();
        response.message = "User registered successfully!";
        return ResponseEntity.ok(response);
    }
}
