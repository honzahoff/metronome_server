package com.example.metronome_server.MvcTests;

import com.example.metronome_server.controllers.UserController;
import com.example.metronome_server.exceptions.EntityNotFoundException;
import com.example.metronome_server.models.Settings;
import com.example.metronome_server.models.User;
import com.example.metronome_server.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addNewUserTest() throws Exception{
        User user = new User();
        user.setName("test");
        user.setEmail("test");
        user.setFavorites(null);
        user.setSettings(null);

        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer <TOKEN HERE>").content(mapper
                .writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username")
                        .value(user.getName()))
                .andExpect(jsonPath("$.email")
                        .value(user.getEmail()))
                .andExpect(jsonPath("$.favorites")
                        .value(user.getFavorites()))
                .andExpect(jsonPath("$.settings")
                        .value(user.getSettings()))
        ;

    }

    @Test
    public void testUpdateSettings() throws Exception {
        Integer userId = 1;
        Settings newSettings = new Settings();
        newSettings.setHapticFeedback(true);

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setSettings(new Settings());


        when(userService.updateSettings(userId, newSettings)).thenReturn(existingUser);

        mockMvc.perform(put("/users/" + userId + "/settings").header("Authorization", "Bearer <TOKEN HERE>")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newSettings)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingUser.getId()))
                .andExpect(jsonPath("$.settings.hapticFeedback").value(true));
    }
}
