package ssvv.mihusebi;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigBangTest {
    private Service service;
    private StudentXMLRepository studentRepository;
    private TemaXMLRepository temaRepository;
    private NotaXMLRepository notaRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentXMLRepository(new StudentValidator(), "studentRepo.xml");
        temaRepository = new TemaXMLRepository(new TemaValidator(), "temaRepo.xml");
        notaRepository = new NotaXMLRepository(new NotaValidator(), "notaRepo.xml");
        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @Test
    void test_addStudent(){
        int operationResult = service.saveStudent("1","nume", 936);
        assertEquals(0, operationResult);
    }

    @Test
    void test_addAssignment(){
        int operationResult = service.saveAssignment("1","desc",7,6);
        assertEquals(0, operationResult);
    }

    @Test
    void test_addNota(){
        int operationResult = service.saveNota("1","1",6,7,"Buna, buna copii!");
        assertEquals(0, operationResult);
    }

    @Test
    void test_addStudent_addAssignment_addNota() {
        int operationResult1 = service.saveStudent("1","nume", 936);
        int operationResult2 = service.saveAssignment("1","desc",7,6);
        int operationResult3 = service.saveNota("1","1",6,7,"Buna, buna copii!");

        assertEquals(0, operationResult1);
        assertEquals(0, operationResult2);
        assertEquals(0, operationResult3);
    }


}
