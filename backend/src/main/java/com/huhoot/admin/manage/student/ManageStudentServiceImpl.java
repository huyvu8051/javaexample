package com.huhoot.admin.manage.student;

import com.huhoot.converter.ListConverter;
import com.huhoot.dto.StudentResponse;
import com.huhoot.exception.UsernameExistedException;
import com.huhoot.dto.ChallengeResponse;
import com.huhoot.mapper.StudentMapper;
import com.huhoot.model.Student;
import com.huhoot.repository.StudentInChallengeRepository;
import com.huhoot.repository.StudentRepository;
import com.huhoot.vue.vdatatable.paging.PageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class ManageStudentServiceImpl implements ManageStudentService {
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final Validator validator;

    private final ListConverter listConverter;
    private final StudentMapper studentMapper;


    private final StudentInChallengeRepository sicRepo;

    @Override
    public PageResponse<StudentResponse> findAllStudentAccount(Pageable pageable) {

        Page<StudentResponse> all = studentRepository.findAllStudent(pageable);

        return listConverter.toPageResponse(all);

    }


    private void addOneStudent(StudentAddRequest addRequest) throws UsernameExistedException {
        Set<ConstraintViolation<StudentAddRequest>> violations = validator.validate(addRequest);

        if (violations.size() > 0) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<StudentAddRequest> violation : violations) {
                sb.append(violation.getPropertyPath());
                sb.append(" :");
                sb.append(violation.getMessage());
                sb.append(" | ");
            }

            throw new ValidationException(sb.toString());
        }


        Optional<Student> duplicate = studentRepository.findOneByUsername(addRequest.getUsername());

        if (duplicate.isPresent()) {
            throw new UsernameExistedException("Username existed!");
        }

        String hashedPassword = passwordEncoder.encode("password");
        Student student = new Student(addRequest.getUsername(), addRequest.getFullName(), hashedPassword);
        studentRepository.save(student);
    }

    @Override
    public List<StudentAddErrorResponse> addManyStudentAccount(List<StudentAddRequest> request) {
        List<StudentAddErrorResponse> errors = new ArrayList<>();

        for (StudentAddRequest studentAddRequest : request) {
            try {
                this.addOneStudent(studentAddRequest);
            } catch (Exception e) {
                StudentAddErrorResponse errResponse = new StudentAddErrorResponse(studentAddRequest);

                errResponse.setErrorMessage(e.getMessage());
                errors.add(errResponse);
                log.warn(e.getMessage());
            }
        }

        return errors;

    }


    @Override
    public void updateStudentAccount(StudentUpdateRequest request) throws UsernameExistedException {

        Optional<Student> duplicate = studentRepository.findOneByUsername(request.getUsername());

        if (duplicate.isPresent() && !duplicate.get().getUsername().equals(request.getUsername())) {
            throw new UsernameExistedException("Username existed!");
        }

        Optional<Student> optional = studentRepository.findOneById(request.getId());

        Student entity = optional.orElseThrow(() -> new NullPointerException("Student not found"));

        studentMapper.update(request, entity);

        studentRepository.save(entity);
    }

    @Override
    public StudentResponse findOneByUsername(String username) {
        return studentRepository.findOneStudentResponseByUsername(username);
    }

    @Override
    public PageResponse<ChallengeResponse> findAllChallengeParticipateIn(String username, Pageable pageable1) {
        Page<ChallengeResponse> page = sicRepo.findAllChallengeResByStudent(username, pageable1);

        return listConverter.toPageResponse(page);


    }


}
