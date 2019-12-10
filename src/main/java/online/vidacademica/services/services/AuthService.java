package online.vidacademica.services.services;

import online.vidacademica.services.dto.CredentialsDTO;
import online.vidacademica.services.dto.TokenDTO;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.security.JWTUtil;
import online.vidacademica.services.services.exceptions.JWTAuthenticationException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public TokenDTO authenticate(CredentialsDTO dto) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

            String email = dto.getEmail();

            Optional<User> obj = Optional.ofNullable(userRepository.findByEmail(email));
            User user = obj.orElseThrow(() -> new ResourceNotFoundException(email));

            String roleUser;
            if (user.hasRole("ROLE_STUDENT")) {
                roleUser = "Student";
            } else {
                roleUser = "Teacher";
            }
            authenticationManager.authenticate(authToken);
            String token = jwtUtil.generateToken(dto.getEmail());


            return new TokenDTO(dto.getEmail(), token, roleUser);
        } catch (AuthenticationException e) {
            throw new JWTAuthenticationException("Bad credentials");
        }
    }

    public User authenticated() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userRepository.findByEmail(userDetails.getUsername());
        } catch (Exception e) {
            throw new JWTAuthenticationException("Access denied");
        }
    }

    public TokenDTO tokenRefresh() {
        User user = authenticated();
        return new TokenDTO(user.getEmail(), jwtUtil.generateToken(user.getEmail()));
    }

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Email not found");
        }
        String newPass = newPassword();
        user.setPassword(passwordEncoder.encode(newPass));

        userRepository.save(user);
        LOG.info("New password: " + newPass);
    }

    private String newPassword() {
        char[] vect = new char[10];
        for (int i = 0; i < 10; i++) {
            vect[i] = randomChar();
        }
        return new String(vect);
    }

    private char randomChar() {
        Random rand = new Random();
        int opt = rand.nextInt(3);
        if (opt == 0) {//generate digit
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) {//generate uppercase letter
            return (char) (rand.nextInt(26) + 65);
        } else {//generate lowercase letter
            return (char) (rand.nextInt(26) + 97);
        }

    }

    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();
        if (user == null || user.getId() != userId || user.hasRole("ROLE_TEACHER")) {
            if (user == null || user.getId() != userId && user.hasRole("ROLE_STUDENT")) {
                throw new JWTAuthenticationException("Access denied");
            }
        }
    }

    public void validateOwnSubjectOrAdmin(Subject subject) {
        User user = authenticated();
        if (user == null || user.getId() != subject.getTeacher().getId() && user.hasRole("ROLE_STUDENT")) {
            throw new JWTAuthenticationException("Access denied");
        }
    }


}