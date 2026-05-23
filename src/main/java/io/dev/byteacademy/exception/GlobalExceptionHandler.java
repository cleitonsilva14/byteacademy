package io.dev.byteacademy.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException exception, WebRequest webRequest){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Course not found!");
        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException exception, WebRequest webRequest){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Category not found!");
        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);

    }

    /*@ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException exception, WebRequest webRequest) {
        
        // 1. Define uma mensagem genérica padrão caso o mapeamento falhe
        String mensagemExplicativa = "Já existe um registro com os dados informados.";
        String textoOriginal = exception.getMessage();

        // 2. Regex para capturar o índice/campo e o valor entre aspas que falhou no MongoDB
        // Captura padrões como: index: name dup key: { name: "SQL" }
        Pattern pattern = Pattern.compile("index:\\s+(\\w+)\\s+dup key:\\s+\\{\\s+\\1:\\s+\\\"([^\\\"]+)\\\"\\}");
        Matcher matcher = pattern.matcher(textoOriginal);

        if (matcher.find()) {
            String campo = matcher.group(1); // Ex: "name" ou "title"
            String valor = matcher.group(2); // Ex: "SQL"
            
            // Traduz o nome do campo para o usuário, se quiser
            if ("name".equals(campo)) campo = "nome";
            if ("title".equals(campo)) campo = "título";

            mensagemExplicativa = String.format("Não foi possível concluir a operação: O %s '%s' já está cadastrado.", campo, valor);
        }

        // 3. Monta o ProblemDetail padronizado (RFC 7807)
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, mensagemExplicativa);
        
        // Altera o título para algo legível em vez do nome da classe Java
        problemDetail.setTitle("Conflito de Dados"); 
        
        // Opcional: Adiciona propriedades customizadas extras ao JSON se achar útil
        problemDetail.setProperty("erro", "Chave Duplicada");

        return new ResponseEntity<>(problemDetail, HttpStatus.CONFLICT);
    } */
    
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException exception, WebRequest webRequest){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
        problemDetail.setTitle("org.springframework.dao.DuplicateKeyException");
        return new ResponseEntity<>(problemDetail, HttpStatus.CONFLICT);

    }


}
