package split.wise.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
