package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String userId;

    @ManyToOne
    private Event event;

    private String comment;

    public Comment(String userId, Event event, String comment) {
        this.userId = userId;
        this.event = event;
        this.comment = comment;
    }
}
