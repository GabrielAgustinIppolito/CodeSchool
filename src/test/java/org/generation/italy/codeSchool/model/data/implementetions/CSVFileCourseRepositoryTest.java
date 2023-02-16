package org.generation.italy.codeSchool.model.data.implementetions;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.exceptions.DataException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*; //è UN IMPORT STATICO

class CSVFileCourseRepositoryTest {

   private static final long ID = 1;
   private static final String TITLE = "TITLE";
   private static final String DESCRIPTION = "DESCRIPTION";
   private static final String PROGRAM = "PROGRAM";
   private static final double DURATION = 2.5;
   private static final String CSV_LINE = String.format(Locale.US,"%d,%s,%s,%s,%.2f",ID,TITLE,DESCRIPTION,PROGRAM,DURATION);
   private static final String CSV_LINE2 = String.format(Locale.US,"%d,%s,%s,%s,%.2f",ID+1,TITLE + "test",DESCRIPTION + "test",PROGRAM +"test",DURATION+1);

   private static final String FILE_NAME = "TESTA_DATA.csv";

   @org.junit.jupiter.api.BeforeEach
   void setUp() {
      System.out.println("Setup");
   }

   @org.junit.jupiter.api.AfterEach
   void tearDown() {
      try {
         new FileOutputStream(FILE_NAME).close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      System.out.println("tearDown");
   }

   @org.junit.jupiter.api.Test
   void findById_finds_course_when_present() {
      Course c1 = new Course(ID, TITLE, DESCRIPTION, PROGRAM, DURATION);
      CSVFileCourseRepository repo = new CSVFileCourseRepository(FILE_NAME);
      try(PrintWriter pW = new PrintWriter(new FileOutputStream(FILE_NAME))){
         pW.println(CSV_LINE2);
         pW.println(CSV_LINE); //conserva in un buffer, preparadnosi a scrivere su file
         pW.flush(); //Stampa su file
         Optional <Course> x = repo.findById(ID);
         //Assert
         assertTrue(x.isPresent());
         Course c2 = x.get();
         assertEquals(c1,c2); //con il nostro equals modificato
      }catch (DataException e){
         fail("Errore nella ricerrca by ud sul file di testo" + e.getMessage());
      }catch (IOException e){
         fail("Errore nella preparazioen del test sulla riceraca by id sul file di testo " + e.getMessage());
      }
      System.out.println("findByid");
      
   }

   @org.junit.jupiter.api.Test
   void create() {
      //ARRANGE
      Course c = new Course(ID, TITLE, DESCRIPTION, PROGRAM, DURATION);
      CSVFileCourseRepository repo = new CSVFileCourseRepository(FILE_NAME);
      //ACT
      try{
         List<String> linesBefore = Files.readAllLines(Paths.get(FILE_NAME));
         repo.create(c);
         List<String> linesAfter  =Files.readAllLines(Paths.get(FILE_NAME));
         //ASSERT
         assertEquals(linesBefore.size()+1, linesAfter.size()); //controllo si sìa aggiunta una linea
         String csvLne = linesAfter.get(linesAfter.size()-1);
         String[] tokens = csvLne.split(",");
         assertEquals(ID,Long.parseLong(tokens[0]));
         assertEquals(DURATION,Double.parseDouble(tokens[tokens.length-1]));
      }catch(DataException e){
         fail("Fallito il create  su CSV" + e.getMessage());
      } catch (IOException e){
         fail("Fallita la verifica del create su file CSV" + e.getMessage());
      }



      System.out.println("create");
   }

   @org.junit.jupiter.api.Test
   void courseToCSV() {
      //ARRANGE
      Course c = new Course(ID, TITLE, DESCRIPTION, PROGRAM, DURATION);
      CSVFileCourseRepository repo = new CSVFileCourseRepository();
      //ACT
      String csvLine = repo.courseToCSV(c);
      //ASSERT
      assertEquals(CSV_LINE,csvLine);
      System.out.println("courseToCsv");
   }
}