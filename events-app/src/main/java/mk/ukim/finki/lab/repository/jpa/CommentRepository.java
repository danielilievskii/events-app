package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
