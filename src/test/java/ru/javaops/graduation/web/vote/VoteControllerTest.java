package ru.javaops.graduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.repository.VoteRepository;
import ru.javaops.graduation.util.JsonUtil;
import ru.javaops.graduation.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.graduation.web.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.graduation.web.vote.VoteController.REST_URL;
import static ru.javaops.graduation.web.vote.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String ADMIN_URL = REST_URL + "/admin/";
    private static final String USER_URL = REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_URL + USER_VOTE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(userVote));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_URL + USER_VOTE_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(voteRepository.findById(USER_VOTE_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_URL + VOTE_NOT_FOUND))
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
        Vote updated = getUpdatedVote();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(ADMIN_URL + USER_VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteRepository.getById(USER_VOTE_ID), getUpdatedVote());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Vote newVote = getNewVote();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getById(newId), newVote);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(votes));
    }
}
