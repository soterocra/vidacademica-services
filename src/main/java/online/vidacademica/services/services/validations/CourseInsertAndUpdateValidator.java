package online.vidacademica.services.services.validations;


import online.vidacademica.services.dto.CourseDTO;
import online.vidacademica.services.repositories.SubjectRepository;
import online.vidacademica.services.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class CourseInsertAndUpdateValidator implements ConstraintValidator<CourseInsertAndUpdateValid, CourseDTO> {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void initialize(CourseInsertAndUpdateValid ann) {
    }

    @Override
    public boolean isValid(CourseDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}