package eci.ieti.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import eci.ieti.data.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    Page<Todo> findByResponsibleContaining(String responsible,Pageable pageable);

    
}