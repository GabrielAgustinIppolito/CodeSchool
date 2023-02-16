package org.generation.italy.codeSchool.model;

import java.util.Objects;

public class Course {
   private long id;
   private String title;
   private String description;
   private String program;
   private double duration;

   public Course(long id, String title, String description, String program, double duration) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.program = program;
      this.duration = duration;
   }

   public Course() {}

   public long getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public String getDescription() {
      return description;
   }

   public String getProgram() {
      return program;
   }

   public double getDuration() {
      return duration;
   }

   public void setId(long id) {
      this.id = id;
   }

   @Override
   public String toString() { //metodo originario in object
      return "Course{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", program='" + program + '\'' +
            ", duration=" + duration +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true; // se punto allo stesso oggetto ritorno true
      if (o == null || getClass() != o.getClass()) return false;//se mi hai passato null ritorno false, idem se sono classi di oggetti diverse
      Course course = (Course) o; //siccome sto facendo this.getclass() all'interno di corso, significa che sto passando un corso. su Object (o) non potrei chiamare i metodi di corso, faccio un DownCast
      return getId() == course.getId() && Double.compare(course.getDuration(), getDuration()) == 0 && getTitle().equals(course.getTitle()) && Objects.equals(getDescription(), course.getDescription()) && Objects.equals(getProgram(), course.getProgram());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId()); //dato che non posso avere due corsi diversi con stesso id, modifica l'hashcode in base all'equals
   }
}
