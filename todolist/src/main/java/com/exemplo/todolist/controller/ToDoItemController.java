package com.exemplo.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.todolist.entity.ToDoItem;
import com.exemplo.todolist.service.ToDoItemService;

@RestController
@RequestMapping("/api/todos")
public class ToDoItemController {

    @Autowired
    private ToDoItemService toDoItemService;

    @PostMapping
    public ToDoItem createItem(@RequestBody ToDoItem toDoItem){
        return toDoItemService.save(toDoItem);
    }

    @GetMapping
    public List<ToDoItem> findAllToDoItems(){
        return toDoItemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getToDoItemById(@PathVariable Long Id){
        Optional<ToDoItem> toDoItem = toDoItemService.findById(Id);
        return toDoItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable Long id, @RequestBody ToDoItem toDoItemDetails){
        Optional <ToDoItem> toDoItem = toDoItemService.findById(id);
        if(toDoItem.isPresent()){
            ToDoItem item = toDoItem.get();
            item.setTitle(toDoItemDetails.getTitle());
            item.setDescription(toDoItemDetails.getDescription());
            item.setCompleted(toDoItemDetails.isCompleted());
            ToDoItem updatedToDoItem = toDoItemService.save(item);
            return ResponseEntity.ok(updatedToDoItem);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id){
        toDoItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
