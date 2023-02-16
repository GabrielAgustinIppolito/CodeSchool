package org.generation.italy.codeSchool.model.data.abstractions;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.exceptions.DataException;
import org.generation.italy.codeSchool.model.data.exceptions.EntitiNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
   Optional<Course> findById(long id) throws DataException;
   List<Course> findByTitleContains(String part); //possiamo usare una qualsiesi implementazione di lista
   Course create(Course course) throws DataException;
   void update(Course course) throws EntitiNotFoundException;
   void deleteById(long id) throws EntitiNotFoundException;
}
