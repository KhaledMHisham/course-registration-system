package com.example.demo.service;

import java.util.Collection;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface GenericService<T> {
  T findById(UUID id);

  T save(T entity);

  void delete(UUID id);

  Collection<T> findAll();

  Page<T> findAll(int page, int size);
}
