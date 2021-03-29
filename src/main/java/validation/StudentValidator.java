package validation;
import domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().equals("")) { // name in S \ {null, ""}, where S is the set of all the possible strings
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().equals("")) { // name in S \ {null, ""}, where S is the set of all the possible strings
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() <= 110 || student.getGrupa() > 938) { // gr in [111, 938]
            throw new ValidationException("Grupa invalida! \n");
        }
    }
}

