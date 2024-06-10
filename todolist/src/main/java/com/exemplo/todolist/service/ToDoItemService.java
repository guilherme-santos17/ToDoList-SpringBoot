package com.exemplo.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.todolist.entity.ToDoItem;
import com.exemplo.todolist.repository.ToDoItemRepository;

@Service
public class ToDoItemService {
    
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    public List<ToDoItem> findAll(){
        return toDoItemRepository.findAll();
    }

    public Optional<ToDoItem> findById(Long id){
        return toDoItemRepository.findById(id);
    }

    public ToDoItem save(ToDoItem toDoItem){
        return toDoItemRepository.save(toDoItem);
    }

    public void deleteById(Long id){
        toDoItemRepository.deleteById(id);
    }
}
