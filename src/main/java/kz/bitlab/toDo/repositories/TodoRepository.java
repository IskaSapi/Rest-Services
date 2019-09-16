package kz.bitlab.toDo.repositories;

import kz.bitlab.toDo.models.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDo,Long> {

}
