package gb.SpringJWT;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.List;
@Entity
@Setter
@Getter
@Table(name = "task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    private Status status;
    @OneToMany
    @JoinColumn(name = "performer_id")
    private List<Performer> performers;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String date;
    public enum Status {
        ToDo,
        InProgress,
        Done;
    }
}