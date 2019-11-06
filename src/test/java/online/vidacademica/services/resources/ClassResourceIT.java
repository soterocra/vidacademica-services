package online.vidacademica.services.resources;

import online.vidacademica.services.security.JWTUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassResourceIT {

    private static final String URI_PATH = "/class";
    private static final String STUDENT_USER_LOGIN = "soso@gmail.com";

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private HttpHeaders headers = new HttpHeaders();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        String token = jwtUtil.generateToken(STUDENT_USER_LOGIN);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    @Test
    public void findAll_sucess() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get(URI_PATH).accept(MediaType.APPLICATION_JSON_UTF8).headers(headers);
        mockMvc.perform(request).andExpect(status().isOk());
    }

}
