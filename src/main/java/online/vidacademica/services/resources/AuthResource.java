package online.vidacademica.services.resources;

import java.net.URI;

import online.vidacademica.services.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import online.vidacademica.services.dto.CredentialsDTO;
import online.vidacademica.services.dto.TokenDTO;
import online.vidacademica.services.services.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private AuthService service;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody CredentialsDTO dto){
		TokenDTO tokenDTO = service.authenticate(dto);
		return ResponseEntity.ok().body(tokenDTO);
	}

	@PostMapping("/refresh")
	public ResponseEntity<TokenDTO> refresh(){
		TokenDTO tokenDTO = service.tokenRefresh();
		return ResponseEntity.ok().body(tokenDTO);
	}

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@RequestBody EmailDTO dto){
        service.sendNewPassword(dto.getEmail());
        return ResponseEntity.noContent().build();
    }

	
}
