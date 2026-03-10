package com.devops.todo.controller;

import com.devops.todo.model.Todo;
import com.devops.todo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // GET /api/todos — List all todos
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) Todo.Priority priority) {

        List<Todo> todos;

        if (completed != null) {
            todos = todoRepository.findByCompletedOrderByCreatedAtDesc(completed);
        } else if (priority != null) {
            todos = todoRepository.findByPriorityOrderByCreatedAtDesc(priority);
        } else {
            todos = todoRepository.findAllByOrderByCreatedAtDesc();
        }

        return ResponseEntity.ok(todos);
    }

    // GET /api/todos/:id — Get a single todo
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/todos — Create a new todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo saved = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/todos/:id — Update a todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,
                                           @Valid @RequestBody Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedTodo.getTitle());
                    existing.setDescription(updatedTodo.getDescription());
                    existing.setCompleted(updatedTodo.isCompleted());
                    existing.setPriority(updatedTodo.getPriority());
                    return ResponseEntity.ok(todoRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/todos/:id/toggle — Toggle completion status
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggleTodo(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(existing -> {
                    existing.setCompleted(!existing.isCompleted());
                    return ResponseEntity.ok(todoRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/todos/:id — Delete a todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(existing -> {
                    todoRepository.delete(existing);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/todos/stats — Get todo statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        long total = todoRepository.count();
        long completed = todoRepository.findByCompletedOrderByCreatedAtDesc(true).size();
        long pending = total - completed;

        return ResponseEntity.ok(Map.of(
                "total", total,
                "completed", completed,
                "pending", pending
        ));
    }
}
