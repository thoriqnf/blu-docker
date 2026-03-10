package com.devops.todo.repository;

import com.devops.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCompletedOrderByCreatedAtDesc(boolean completed);

    List<Todo> findAllByOrderByCreatedAtDesc();

    List<Todo> findByPriorityOrderByCreatedAtDesc(Todo.Priority priority);
}
