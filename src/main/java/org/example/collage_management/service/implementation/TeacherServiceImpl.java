package org.example.collage_management.service.implementation;

import jakarta.transaction.Transactional;
import org.example.collage_management.dto.request.TeacherRequestDto;
import org.example.collage_management.dto.response.TeacherResponseDto;
import org.example.collage_management.entity.Department;
import org.example.collage_management.entity.Student;
import org.example.collage_management.entity.Teacher;
import org.example.collage_management.entity.enums.StudentStatus;
import org.example.collage_management.entity.enums.TeacherStatus;
import org.example.collage_management.mapper.TeacherMapper;
import org.example.collage_management.repository.DepartmentRepository;
import org.example.collage_management.repository.TeacherRepository;
import org.example.collage_management.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final DepartmentRepository departmentRepository;


    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper, DepartmentRepository departmentRepository)
    {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.departmentRepository = departmentRepository;
    }
    //get by id
    @Override
    public TeacherResponseDto findTeacherById(Long id)
    {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found with id: " + id));

        return teacherMapper.toResponseDto(teacher);
    }
   // get all
    @Override
    public List<TeacherResponseDto> findAllTeachers(){

        List<Teacher> Teacher= teacherRepository.findAll();

        return teacherMapper.toResponseDtoList(Teacher);
    }

    // create single teacher

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto dto){

        Teacher teacher = teacherMapper.toEntity(dto);

        // Fetch departments
        List<Department> departments =
                departmentRepository.findAllById(dto.getDepartmentIds());

        if (departments.size() != dto.getDepartmentIds().size()) {
            throw new RuntimeException("Some departments not found");
        }

        teacher.setDepartments(departments);

        // Default status
        teacher.setStatus(TeacherStatus.ACTIVE);

        Teacher saved = teacherRepository.save(teacher);

        return teacherMapper.toResponseDto(saved);
    }



    //Bulk Create
    @Override
    public List<TeacherResponseDto> createMultipleTeachers(List<TeacherRequestDto> dtos)

    {
        List<Teacher> teachers = dtos.stream().map(dto -> {

            Teacher teacher = teacherMapper.toEntity(dto);

            List<Department> departments =
                    departmentRepository.findAllById(dto.getDepartmentIds());

            if (departments.isEmpty()) {
                throw new RuntimeException("Departments not found");
            }

            if (departments.size() != dto.getDepartmentIds().size()) {
                throw new RuntimeException("Some departments not found");
            }

            teacher.setDepartments(departments);
            teacher.setStatus(TeacherStatus.ACTIVE);

            return teacher;

        }).collect(Collectors.toList());

        List<Teacher> savedTeachers = teacherRepository.saveAll(teachers);

        return savedTeachers.stream()
                .map(teacherMapper::toResponseDto)
                .collect(Collectors.toList());

    }
    //update
    @Override
    public TeacherResponseDto updateTeacher( TeacherRequestDto dto, Long id) {

        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found with id: " + id));

        // simple field update via MapStruct
        teacherMapper.updateTeacherFromDto(dto, existing);

        // ManyToMany handle manually
        List<Department> departments =
                departmentRepository.findAllById(dto.getDepartmentIds());

        if (departments.size() != dto.getDepartmentIds().size()) {
            throw new RuntimeException("Some departments not found");
        }

        existing.setDepartments(departments);

        Teacher updated = teacherRepository.save(existing);

        return teacherMapper.toResponseDto(updated);
    }
  // delete
    @Override
    public void deleteTeacher(Long id) {

        if (teacherRepository.findById(id).isPresent()) {

            teacherRepository.deleteById(id);
        }
        else  {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }





}
