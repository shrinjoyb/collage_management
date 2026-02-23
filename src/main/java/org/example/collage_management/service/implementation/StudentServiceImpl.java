package org.example.collage_management.service.implementation;

import org.example.collage_management.entity.enums.StudentStatus;
import org.springframework.transaction.annotation.Transactional;
import org.example.collage_management.dto.request.StudentRequestDto;
import org.example.collage_management.dto.response.StudentResponseDto;
import org.example.collage_management.entity.Department;
import org.example.collage_management.entity.Student;
import org.example.collage_management.mapper.StudentMapper;
import org.example.collage_management.repository.DepartmentRepository;
import org.example.collage_management.repository.StudentRepository;
import org.example.collage_management.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              DepartmentRepository departmentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.studentMapper = studentMapper;
    }
  //create single student
    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto dto) {

        Student student = studentMapper.toEntity(dto);

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        student.setDepartment(department);

        Student saved = studentRepository.save(student);
        student.setStatus(StudentStatus.ACTIVE);

        return studentMapper.toResponseDto(saved);
    }
 // create multiple student
    @Override
    @Transactional
    public List<StudentResponseDto> createMultipleStudents(List<StudentRequestDto> dtos) {

        List<Student> students = dtos.stream().map(dto -> {

            Student student = studentMapper.toEntity(dto);

            Department department = departmentRepository
                    .findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            student.setDepartment(department);
            student.setStatus(StudentStatus.ACTIVE);

            return student;

        }).collect(Collectors.toList());

        List<Student> savedStudents = studentRepository.saveAll(students);

        return savedStudents.stream()
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
 // update
    @Override
    @Transactional
    public StudentResponseDto update(Long id, StudentRequestDto dto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id)
                );

        studentMapper.updateStudentFromDto(dto, existingStudent);

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existingStudent.setDepartment(department);

        Student saved = studentRepository.save(existingStudent);

        return studentMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto findById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id)
                );

        return studentMapper.toResponseDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> findAll() {

        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {
            throw new RuntimeException("No students found");
        }

        return students.stream()
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }

        studentRepository.deleteById(id);
    }
}
