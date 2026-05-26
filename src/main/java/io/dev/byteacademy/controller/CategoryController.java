package io.dev.byteacademy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dev.byteacademy.model.Category;
import io.dev.byteacademy.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias dos cursos")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    
    @PostMapping("/bulk")
    @ApiResponse(responseCode = "201", description = "Lista de Category salva com sucesso")
    @Operation(summary = "Salva categorias em lote", description = "Recebe uma lista de categorias e ignora automaticamente os nomes duplicados.")
    public ResponseEntity<?> saveAll(@RequestBody List<Category> categories) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.saveAll(categories));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        if(!categoryService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
