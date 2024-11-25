package mk.ukim.finki.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private String userId;
    Long eventId;
    private String comment;
}
