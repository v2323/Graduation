package ru.javaops.graduation.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.model.Vote;

import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    void getByUserId(int userId);

    Optional<Vote> findByUserId(int userId);
}
