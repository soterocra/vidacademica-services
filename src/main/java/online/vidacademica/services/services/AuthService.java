package online.vidacademica.services.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import online.vidacademica.services.dto.CredentialsDTO;
import online.vidacademica.services.dto.TokenDTO;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.security.JWTUtil;
import online.vidacademica.services.services.exceptions.JWTAuthenticationException;

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
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public TokenDTO authenticate(CredentialsDTO dto) {
	try {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
		authenticationManager.authenticate(authToken);
		String token = jwtUtil.generateToken(dto.getEmail());
		
		return new TokenDTO(dto.getEmail(),token);
	}catch (AuthenticationException e) {
		throw new JWTAuthenticationException("Bad credentials");
	}	
	}
	
	public User authenticated() {
		try {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.findByEmail(userDetails.getUsername());
		}catch (Exception e) {
		throw new JWTAuthenticationException("Access denied");
	}
	}
	}