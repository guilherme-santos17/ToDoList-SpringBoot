package com.exemplo.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.todolist.entity.ToDoItem;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}
