package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.graduation.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"dishes"})
public class Menu extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "day_of_weak", nullable = false)
    @NotBlank
    @Size(max = 1000)
    @NoHtml   // https://stackoverflow.com/questions/17480809
    private String dayOfWeak;

    //    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles"))
//    @Column(name = "role")
//    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id") //https://stackoverflow.com/a/62848296/548473
//    @OnDelete(action = OnDeleteAction.CASCADE)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dish_id", nullable = false)
//    @JsonBackReference
//    private Dish dish;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("name DESC")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    private List<Dish> dishes;

    public Menu(String dayOfWeak) {
        this.dayOfWeak = dayOfWeak;
    }

    public Menu(Integer id, String dayOfWeak) {
        super(id);
        this.dayOfWeak = dayOfWeak;
    }

    public Menu(Integer id, String dayOfWeak, Restaurant restaurant, List<Dish> dishes) {
        super(id);
        this.dayOfWeak = dayOfWeak;
        this.dishes = dishes;
    }

    public Menu(String dayOfWeak, Restaurant restaurant, List<Dish> dishes) {
        this.dayOfWeak = dayOfWeak;
        this.dishes = dishes;
    }
}
