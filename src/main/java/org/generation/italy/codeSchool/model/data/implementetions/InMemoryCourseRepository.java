package org.generation.italy.codeSchool.model.data.implementetions;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.abstractions.CourseRepository;
import org.generation.italy.codeSchool.model.data.exceptions.EntitiNotFoundException;

import java.util.*;

public class InMemoryCourseRepository implements CourseRepository {
   private static Map<Long, Course> dataSource = new HashMap<>(); //le generics lavorano solo con oggetti (Long wraper di long)
   private static long nextId;
   @Override
   public Optional<Course> findById(long id) {
      Course x = dataSource.get(id);
      if(x != null){
         return Optional.of(x);
      }
      return Optional.empty();
   }

   @Override
   public List<Course> findByTitleContains(String part) {
      Collection<Course> cs = dataSource.values();
      List<Course> result = new ArrayList<>();
      for(Course c : cs){
         if (c.getTitle().contains(part)){
            result.add(c);
         }
      }
      return result;
   }

   @Override
   public Course create(Course course) {
      nextId++;
      dataSource.put(nextId, course);
      course.setId(nextId);
      return course;
   }

   @Override
   public void update(Course course) throws EntitiNotFoundException {
      if(dataSource.containsKey(course.getId())){
         dataSource.put(course.getId(), course);
      } else{
//         EntitiNotFoundException e = new EntitiNotFoundException("Non esiste un corso con id" + course.getId());
//         throw e;
         throw new EntitiNotFoundException("Non esiste un corso con id" + course.getId());
      }
   }

   @Override
   public void deleteById(long id) throws EntitiNotFoundException {
//      Course old = dataSource.remove(id);
//      if (old == null){
//         throw new EntitiNotFoundException("Non esiste un corso con id" + id);
//      }
      if(dataSource.remove(id) == null){
         throw new EntitiNotFoundException("Non esiste un corso con id" +id);
      }
   }
}
