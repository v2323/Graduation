package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.graduation.HasId;
import ru.javaops.graduation.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"menus"})
public class Restaurant extends NamedEntity implements HasId, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(max = 1000)
    @NoHtml   // https://stackoverflow.com/questions/17480809
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dayOfWeak DESC")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    private List<Menu> menus;

    public Restaurant(String description) {
        this.description = description;
    }

    public Restaurant(Restaurant r) {
        this(r.id, r.name, r.description);
    }

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }
}
