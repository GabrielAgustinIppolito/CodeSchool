package org.generation.italy;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.abstractions.CourseRepository;
import org.generation.italy.codeSchool.model.data.exceptions.DataException;
import org.generation.italy.codeSchool.model.data.implementetions.InMemoryCourseRepository;

import java.util.Optional;

public class Main {
   public static void main(String[] args) throws DataException {
      CourseRepository c = new InMemoryCourseRepository();
      Optional<Course> x = c.findById(22); //non é ovvio possa arrivare un null se non specifico
      Course def = x.orElse(new Course());
      if (x.isPresent()){
         Course course = x.get();
         System.out.println(course.getTitle());
      }
   }
}