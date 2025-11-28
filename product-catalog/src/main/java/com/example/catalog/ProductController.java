package com.example.catalog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Map<Integer, Product> db = new HashMap<>();

    @GetMapping
    public List<Product> getAll() { return new ArrayList<>(db.values()); }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product p = db.get(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product p) {
        if (db.containsKey(p.getId())) return ResponseEntity.status(409).build();
        db.put(p.getId(), p);
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product p) {
        if (!db.containsKey(id)) return ResponseEntity.notFound().build();
        db.put(id, p);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!db.containsKey(id)) return ResponseEntity.notFound().build();
        db.remove(id);
        return ResponseEntity.ok().build();
    }
}
