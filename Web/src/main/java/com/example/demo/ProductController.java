package com.example.demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    List<ProductTypes> products = new ArrayList<>();

    public ProductController() {
        List<Product> food = new ArrayList<>();
        food.add(new Product("Chicken", 5.2));
        food.add(new Product("Soup", 2.5));

        List<Product> tools = new ArrayList<>();
        tools.add(new Product("Tape", 1.2));
        tools.add(new Product("Rope", 7.9));
        tools.add(new Product("Drill", 29.99));

        products.add(new ProductTypes("Food", food));
        products.add(new ProductTypes("Tools", tools));

    }

    @GetMapping("hello")
    String hello00() {
        return "Hello world!";
    }

    @GetMapping("hello/param")
    String hello01(@RequestParam String who) {
        return String.format("Hello %s!", who);
    }

    @GetMapping("hello/param/{who}")
    String hello02(@PathVariable String who) {
        return String.format("Hello %s!", who);
    }

    @GetMapping({"items", "items/"})
    List<ProductTypes> getAll() {
        return products;
    }

    @GetMapping({"items/{index}", "items/{index}/"})
    ProductTypes getType(@PathVariable int index) {
        if(index < 0 || index >= products.size()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return products.get(index);
    }

    @GetMapping({"items/{index01}/{index02}", "items/{index01}/{index02}/"})
    Product getItem(@PathVariable int index01, @PathVariable int index02) {
        if(index01 < 0 || index01 >= products.size()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if(index02 < 0 || index02 >= products.get(index01).list().size()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return products.get(index01).list().get(index02);
    }

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletResponse response) {
        int status = response.getStatus();
        HttpStatus httpStatus = HttpStatus.valueOf(status);
        if (httpStatus == HttpStatus.NOT_FOUND)
            return new ResponseEntity<>("Error 404 - not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(String.format("Error %d", status), httpStatus);
    }

    @PutMapping({"items/add/{index}", "items/add/{index}/"})
    public void addProduct(@PathVariable int index, @RequestBody Product product) {
        products.get(index).list().add(product);
    }

    @DeleteMapping({"items/delete/{index01}/{index02}", "items/delete/{index01}/{index02}/"})
    public void deleteProduct(@PathVariable int index01, @PathVariable int index02) {
        products.get(index01).list().remove(index02);
    }

}
