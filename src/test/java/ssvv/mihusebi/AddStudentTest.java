package ssvv.mihusebi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddStudentTest {
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
    void test_student_name_empty(){
        String emptyName = "";
        int result = service.saveStudent("1", emptyName, 936);

        assertEquals(1, result);
    }

    @Test
    void test_student_name_notEmpty(){
        String validName = "Sebi";
        int result = service.saveStudent("1", validName, 936);

        assertEquals(0, result);
    }
}
