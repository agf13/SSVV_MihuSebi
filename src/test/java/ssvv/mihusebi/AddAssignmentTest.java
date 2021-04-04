package ssvv.mihusebi;

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

public class AddAssignmentTest {
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
    void test_assignment_id_valid()
    {
        String id = "1";
        int result = service.saveAssignment(id, "desc", 7, 6);
        assertEquals(0, result);
    }

    @Test
    void test_assignment_id_null()
    {
        String id = null;
        int result = service.saveAssignment(id, "desc", 7, 6);
        assertEquals(1, result);
    }

    @Test
    void test_assignment_id_empty(){
        String id = "";
        int result= service.saveAssignment(id, "desc", 7, 6);
        assertEquals(result, 1);
    }

    @Test
    void test_assignment_descriere_valid()
    {
        String desc = "1";
        int result = service.saveAssignment("id", desc, 7, 6);
        assertEquals(0, result);
    }

    @Test
    void test_assignment_descriere_null()
    {
        String desc = null;
        int result = service.saveAssignment("id", desc, 7, 6);
        assertEquals(1, result);
    }

    @Test
    void test_assignment_descriere_empty(){
        String desc = "";
        int result= service.saveAssignment("id", desc, 7, 6);
        assertEquals(result, 1);
    }


    @Test
    void test_assignment_deadline_lessThan1(){
        int deadline = 0;
        int result= service.saveAssignment("id", "desc", deadline, 6);
        assertEquals(result, 1);
    }

    @Test
    void test_assignment_deadline_greaterThan14(){
        int deadline = 15;
        int result= service.saveAssignment("id", "desc", deadline, 6);
        assertEquals(result, 1);
    }

    @Test
    void test_assignment_deadline_lessThanStartLine(){
        int deadline = 2;
        int startline = 3;
        int result= service.saveAssignment("id", "desc", deadline, startline);
        assertEquals(result, 1);
    }

    @Test
    void test_assignment_deadline_greaterThanStartLine(){
        int deadline = 3;
        int startline = 2;
        int result= service.saveAssignment("id", "desc", deadline, startline);
        assertEquals(result, 1);
    }

    @Test
    void test_assignment_startline_lessThan1(){
        int startline = 0;
        int result= service.saveAssignment("id", "desc", 10, startline);
        assertEquals(result, 1);
    }

    @Test
    void test_assignment_startline_greaterThan14(){
        int startline = 15;
        int result= service.saveAssignment("id", "desc", 15, startline);
        assertEquals(result, 1);
    }

}
