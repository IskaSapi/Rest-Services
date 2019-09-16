package kz.bitlab.toDo.controllers.rest;

import kz.bitlab.toDo.models.entities.ToDo;
import kz.bitlab.toDo.models.responses.ResponseMessaege;
import kz.bitlab.toDo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController extends BaseController {

    private TodoRepository todoRepository;

    @Autowired
    public ToDoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @GetMapping
    public ResponseEntity<?> index() {
        return buildMessage(HttpStatus.OK, todoRepository.findAll());
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<ToDo> todoOpt = todoRepository.findById(id);
        if (todoOpt.isPresent()) {
            return buildMessage(HttpStatus.OK, todoOpt.get());
        }
        return buildMessage(HttpStatus.NOT_FOUND,
                ResponseMessaege.builder()
                        .message("No entitiy with such id")
                        .success(false)
                        .build());
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody ToDo todo) {
        if (todo.getId() != null) {
            return buildMessage(HttpStatus.BAD_REQUEST,
                    ResponseMessaege.builder()
                            .success(false)
                            .message("this is entity is already exists")
                            .build());
        }
        return buildMessage(HttpStatus.CREATED,todoRepository.save(todo));
    }



    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ToDo todo) {
        Optional<ToDo> todoOpt = todoRepository.findById(id);

        if (todo.getId() == null || !todo.getId().equals(id) || !todoOpt.isPresent()) {
            return buildMessage(HttpStatus.BAD_REQUEST);
        }
        return buildMessage(HttpStatus.CREATED, todoRepository.save(todo));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return buildMessage(HttpStatus.OK);
    }




}
