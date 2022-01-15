package ru.javaops.graduation.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.User;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.graduation.web.restaurant.RestaurantTestData.*;
import static ru.javaops.graduation.web.user.UserTestData.*;
import static ru.javaops.graduation.web.user.UserTestData.user;

@Transactional
public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String ADMIN_URL = RestaurantController.REST_URL + '/';
    private static final String USER_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_URL + ITALIAN_RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANTS_WITH_MENUS_MATCHER.contentJson(italianRestaurant));
    }

//    @Test
//    void getNotFound() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getByEmail() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + "by-email?email=" + admin.getEmail()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_MATCHER.contentJson(admin));
//    }
//
//    @Test
//    void delete() throws Exception {
//        perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//        assertFalse(restaurantRepository.findById(USER_ID).isPresent());
//    }
//
//    @Test
//    void deleteNotFound() throws Exception {
//        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    void enableNotFound() throws Exception {
//        perform(MockMvcRequestBuilders.patch(REST_URL + NOT_FOUND)
//                .param("enabled", "false")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    void getUnAuth() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void getForbidden() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    void update() throws Exception {
//        User updated = getUpdated();
//        updated.setId(null);
//        perform(MockMvcRequestBuilders.put(REST_URL + USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonWithPassword(updated, "newPass")))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        USER_MATCHER.assertMatch(restaurantRepository.getById(USER_ID), getUpdated());
//    }
//
//    @Test
//    void createWithLocation() throws Exception {
//        User newUser = getNew();
//        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonWithPassword(newUser, "newPass")))
//                .andExpect(status().isCreated());
//
//        User created = USER_MATCHER.readFromJson(action);
//        int newId = created.id();
//        newUser.setId(newId);
//        USER_MATCHER.assertMatch(created, newUser);
//        USER_MATCHER.assertMatch(restaurantRepository.getById(newId), newUser);
//    }
//
//    @Test
//    void getAll() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_MATCHER.contentJson(admin, user));
//    }
}
