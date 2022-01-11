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
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "price", nullable = false)
    @NotBlank
//    @Size(max = 1000)
    @NoHtml   // https://stackoverflow.com/questions/17480809
    private Double price;

//    @Column(name = "day_of_weak", nullable = false)
//    @NotBlank
//    @Size(max = 100)
//    @NoHtml   // https://stackoverflow.com/questions/17480809
//    private String dayOfWeak;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    private Menu menu;
}
