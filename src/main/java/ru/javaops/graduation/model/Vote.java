package ru.javaops.graduation.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Vote extends BaseEntity implements Serializable {

    @Column(name = "rest_id")
    private int restId;

    @Column(name = "user_id")
    private int userId;

//    @Column(name = "vote_time")
//    private LocalTime voteTime;

    @Column(name = "vote_date")
    private LocalDate voteDate;

}
