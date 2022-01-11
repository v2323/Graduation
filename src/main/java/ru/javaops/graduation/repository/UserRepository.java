package ru.javaops.graduation.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}