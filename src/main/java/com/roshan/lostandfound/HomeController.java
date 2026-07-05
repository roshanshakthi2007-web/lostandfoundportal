package com.roshan.lostandfound;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HomeController {

    @Autowired
    LostItemRepository repository;
    
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello"+name+"Welcome";
    }
    @PostMapping("/report")
    public String reportItem(@RequestBody LostItem item)
    {
        
        repository.save(item);

        return "Lost Item Successfully Reported";
    }
    @GetMapping("/items")
    public List<LostItem> getAllItems() {
    return repository.findAll();
    }
    @GetMapping("/item")
    public LostItem getItem(@RequestParam int id) {
    return repository.findById(id).orElse(null);
    }
    @PutMapping("/update")
public String updateLocation(
        @RequestParam int id,
        @RequestParam String location,
        @RequestParam String password) {

    if (!password.equals("a1b2")) {
        return "Wrong Password";
    }

    LostItem item = repository.findById(id).orElse(null);

    if (item == null) {
        return "Item not found";
    }

    item.location = location;

    repository.save(item);

    return "Location Updated Successfully";
}
    @DeleteMapping("/delete")
public String deleteItem(
        @RequestParam int id,
        @RequestParam String password) {

    if (!password.equals("a1b2")) {
        return "Wrong Password";
    }

    LostItem item = repository.findById(id).orElse(null);

    if (item == null) {
        return "Item not found";
    }

    repository.deleteById(id);

    return "Item Deleted Successfully";
}
}