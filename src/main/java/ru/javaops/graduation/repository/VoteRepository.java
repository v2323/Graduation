package ru.javaops.graduation.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Vote;

import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    Optional<Vote> findByUserId(int userId);
}
