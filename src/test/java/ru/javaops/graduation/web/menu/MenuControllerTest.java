package ru.javaops.graduation.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.graduation.model.Menu;
import ru.javaops.graduation.repository.MenuRepository;
import ru.javaops.graduation.util.JsonUtil;
import ru.javaops.graduation.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.graduation.web.menu.MenuController.REST_URL;
import static ru.javaops.graduation.web.menu.MenuTestData.*;
import static ru.javaops.graduation.web.user.UserTestData.ADMIN_MAIL;

public class MenuControllerTest extends AbstractControllerTest {

    private static final String ADMIN_URL = REST_URL + "/admin/";
    private static final String USER_URL = REST_URL + '/';

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_URL + ITALIAN_MENU_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENUS_WITH_DISHES_MATCHER.contentJson(italianMenu));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_URL + ITALIAN_MENU_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(menuRepository.findById(ITALIAN_MENU_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_URL + MENU_NOT_FOUND))
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
        Menu updated = getUpdatedMenu();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(ADMIN_URL + ITALIAN_MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MENUS_WITH_DISHES_MATCHER.assertMatch(menuRepository.getById(ITALIAN_MENU_ID), getUpdatedMenu());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Menu newMenu = getNewMenu();
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated());

        Menu created = MENUS_WITH_DISHES_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENUS_WITH_DISHES_MATCHER.assertMatch(created, newMenu);
        MENUS_WITH_DISHES_MATCHER.assertMatch(menuRepository.getById(newId), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENUS_MATCHER.contentJson(menus));
    }
}
