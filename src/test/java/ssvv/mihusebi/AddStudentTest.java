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
    void test_student_name_valid(){
        String validName = "Sebi";
        int result = service.saveStudent("1", validName, 936);

        assertEquals(0, result);
    }

    @Test
    void test_student_name_null(){
        String nullName = null;
        int result = service.saveStudent("1", nullName, 936);

        assertEquals(1, result);
    }

    @Test
    void test_student_id_null(){
        String nullId = null;
        int result = service.saveStudent(nullId, "nume", 936);

        assertEquals(1, result);
    }

    @Test
    void test_student_id_empty(){
        String emptyId = "";
        int result = service.saveStudent(emptyId, "nume", 936);

        assertEquals(1, result);
    }

    @Test
    void test_student_id_valid(){
        String validId = "1";
        int result = service.saveStudent(validId, "nume", 936);

        assertEquals(0, result);
    }

    @Test
    void test_student_grupa_lessThan110_return1(){
        int tooLessGrupa = 109;
        int result = service.saveStudent("1", "nume", tooLessGrupa);

        assertEquals(1, result);
    }

    @Test
    void test_student_grupa_equal110_return1(){
        int validGrupa = 110;
        int result = service.saveStudent("1", "nume", validGrupa);

        assertEquals(1, result);
    }

    @Test
    void test_student_grupa_greaterThan110_return0(){
        int greaterThan110 = 111;
        int result = service.saveStudent("1", "nume", greaterThan110);

        assertEquals(0, result);
    }

    @Test
    void test_student_grupa_lessThan938_return0() {
        int validGrupa = 937;
        int result = service.saveStudent("1", "nume", validGrupa);

        assertEquals(0, result);
    }

    @Test
    void test_student_grupa_equal938_return0() {
        int validGrupa = 938;
        int result = service.saveStudent("1", "nume", validGrupa);

        assertEquals(0, result);
    }

    @Test
    void test_student_grupa_greaterThan938_return1() {
        int validGrupa = 939;
        int result = service.saveStudent("1", "nume", validGrupa);

        assertEquals(1, result);
    }
}
