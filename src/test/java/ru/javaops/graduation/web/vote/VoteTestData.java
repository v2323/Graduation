package ru.javaops.graduation.web.vote;

import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int USER_VOTE_ID = 1;
    public static final int ADMIN_VOTE_ID = 2;
    public static final int VOTE_NOT_FOUND = 100;

    public static final Vote userVote = new Vote(USER_VOTE_ID, 1, 1, LocalDate.of(2022, 1, 14));
    public static final Vote adminVote = new Vote(ADMIN_VOTE_ID, 2, 2, LocalDate.of(2022, 1, 13));

    public static final List<Vote> votes = List.of(userVote, adminVote);

    public static Vote getNewVote() {
        return new Vote(3, 3, LocalDate.of(2022, 1, 15));
    }

    public static Vote getUpdatedVote() {
        return new Vote(USER_VOTE_ID, 3, 1, LocalDate.of(2022, 1, 15));
    }
}
