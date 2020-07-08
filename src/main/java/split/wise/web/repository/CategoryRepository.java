package split.wise.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
