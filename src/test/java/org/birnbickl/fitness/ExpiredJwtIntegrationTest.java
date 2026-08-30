package org.birnbickl.fitness;

import org.birnbickl.fitness.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static java.lang.reflect.Array.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest (properties = {"jwt.expiration=-1000"})
@AutoConfigureMockMvc
public class ExpiredJwtIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;

    @Test
    public void testExpiredJwt() throws Exception {


        // Zugriff auf geschützten Endpunkt mit abgelaufenem Token, sollte 401 Unauthorized zurückgeben
        String expiredToken = jwtService.generateToken(
                new org.birnbickl.fitness.user.entity.UserEntity("test@example.com", "Password", "Tester"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/me")
                .header("Authorization", "Bearer " + expiredToken))
                .andExpect(status().isUnauthorized());
    }
}
