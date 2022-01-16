package ru.javaops.graduation.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Vote extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "rest_id", nullable = false)
    private int restId;

    @Column(name = "user_id", nullable = false, unique = true)
    private int userId;

    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;

    public Vote(Integer id, int restId, int userId, LocalDate voteDate) {
        super(id);
        this.restId = restId;
        this.userId = userId;
        this.voteDate = voteDate;
    }

    public Vote(int restId, int userId, LocalDate voteDate) {
        this.restId = restId;
        this.userId = userId;
        this.voteDate = voteDate;
    }
}
