package org.birnbickl.fitness;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthFlowIT {

    @Autowired MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void register_login_and_access_protected_endpoint() throws Exception {

        String email = "tester@example.com";
        String password = "password123456";
        String username = "Tester";


        // 1. Registrierung , sollte 201 Created zurückgeben
        MvcResult result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"email": "%s", "password": "%s", "username": "Tester"}
                        """.formatted(email, password, username)))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn();

        // 2. Login, sollte 200 OK zurückgeben und ein Token enthalten
        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"email": "%s", "password": "%s"}
                        """.formatted(email, password)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token").isString())
                .andReturn();

        String loginJson = loginResult.getResponse().getContentAsString();
        JsonNode node = objectMapper.readTree(loginJson);
        String token = node.get("token").asText();

        // 3. Zugriff auf geschützten Endpunkt OHNE Token, sollte Fehlschlagen (401 Unauthorized)
        mockMvc.perform(get("/api/user/me"))
                .andExpect(status().isUnauthorized());

        // 4. Zugriff auf geschützten Endpunkt MIT Token, sollte 200 OK zurückgeben und ein "Hello Username" enthalten
        mockMvc.perform(get("/api/user/me")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Hello")));

    }

}
