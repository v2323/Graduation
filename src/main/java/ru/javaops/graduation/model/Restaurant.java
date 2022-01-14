package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.graduation.HasIdAndEmail;
import ru.javaops.graduation.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Restaurant extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(max = 1000)
    @NoHtml   // https://stackoverflow.com/questions/17480809
    private String description;

    //    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles"))
//    @Column(name = "role")
//    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id") //https://stackoverflow.com/a/62848296/548473
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dayOfWeak DESC")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    private List<Menu> menus;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @JsonManagedReference
//    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
//    private List<Vote> votes;
}
