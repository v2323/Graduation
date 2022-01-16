package ru.javaops.graduation.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.util.JsonUtil;
import ru.javaops.graduation.web.AbstractControllerTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.graduation.web.restaurant.RestaurantController.REST_URL;
import static ru.javaops.graduation.web.restaurant.RestaurantTestData.*;
import static ru.javaops.graduation.web.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.graduation.web.user.UserTestData.USER_ID;

@Transactional
public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String ADMIN_URL = REST_URL + "/admin/";
    private static final String USER_URL = REST_URL + '/';

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

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_URL + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(restaurantRepository.findById(USER_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_URL + RESTAURANT_NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Restaurant updated = getUpdatedRestaurant();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(ADMIN_URL + ITALIAN_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANTS_WITH_MENUS_MATCHER.assertMatch(restaurantRepository.getById(ITALIAN_RESTAURANT_ID), getUpdatedRestaurant());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = getNewRestaurant();
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        Restaurant created = RESTAURANTS_WITH_MENUS_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANTS_WITH_MENUS_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANTS_WITH_MENUS_MATCHER.assertMatch(restaurantRepository.getById(newId), newRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANTS_MATCHER.contentJson(List.of(italianRestaurant, japaneseRestaurant, russianRestaurant)));
    }
}
