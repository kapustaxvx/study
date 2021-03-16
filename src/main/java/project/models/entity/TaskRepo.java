package project.models.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
    Optional<TaskEntity> findById(long id);
    List<TaskEntity> findByUserIdAndIsSolved(long userId, boolean solved);
    List<TaskEntity> findByUserId(long userId);

}
