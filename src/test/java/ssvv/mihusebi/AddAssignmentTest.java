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
}
