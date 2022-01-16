package ru.javaops.graduation.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.graduation.error.DuplicateUsersVoteException;
import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.repository.VoteRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ru.javaops.graduation.util.validation.ValidationUtil.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "votes")
public class VoteController {
    static final String REST_URL = "/api/votes";

    @Autowired
    protected VoteRepository repository;

    @GetMapping("/{id}")
    public Optional<Vote> get(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping()
    @Cacheable
    public List<Vote> getAll() {
        return repository.findAll();
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote) {
        log.info("create {}", vote);
        checkNew(vote);
        if (repository.findByUserId(vote.getUserId()).isPresent()) {
            throw new DuplicateUsersVoteException("User can't have more than one vote");
        }
        Vote created = repository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody Vote vote, @PathVariable int id) {
        log.info("update {} with id={}", vote, id);
        checkVoteDateTime(vote);
        assureIdConsistent(vote, id);
        repository.save(vote);
    }
}
