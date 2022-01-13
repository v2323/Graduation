package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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

//    @Column(name = "rest_id", nullable = false)
//    private int restId;

    @Column(name = "user_id", nullable = false)
    private int userId;

//    @Column(name = "vote_time")
//    private LocalTime voteTime;

    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

}
