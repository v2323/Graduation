package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    public Dish(Double price) {
        this.price = price;
    }

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }
}
