package org.example.collage_management.service.implementation;

import org.example.collage_management.dto.request.CourseRequestDto;
import org.example.collage_management.dto.response.CourseResponseDto;
import org.example.collage_management.entity.Department;
import org.example.collage_management.entity.Teacher;
import org.example.collage_management.mapper.CourseMapper;
import org.example.collage_management.mapper.StudentMapper;
import org.example.collage_management.repository.DepartmentRepository;
import org.example.collage_management.repository.TeacherRepository;
import org.springframework.transaction.annotation.Transactional;
import org.example.collage_management.entity.Course;
import org.example.collage_management.exception.ResourceNotFoundException;
import org.example.collage_management.repository.CourseRepository;
import org.example.collage_management.service.CourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courserepository;
    private final  DepartmentRepository departmentrepository;
    private final  TeacherRepository teacherrepository;
    private final CourseMapper courseMapper;


    public CourseServiceImpl(CourseRepository courseRepository
            , DepartmentRepository departmentrepository
            , TeacherRepository teacherrepository
            , CourseMapper coursemapper)

    {
        this.courserepository = courseRepository;
        this.departmentrepository = departmentrepository;
        this.teacherrepository = teacherrepository;
        this.courseMapper = coursemapper;
    }

    //create

    @Override
    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto dto) {

        // DTO → Entity (simple fields)
        Course course = courseMapper.toEntity(dto);

        // Fetch Department
        Department department = departmentrepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + dto.getDepartmentId()
                        ));

        course.setDepartment(department);

        // Fetch Teacher
        Teacher teacher = teacherrepository
                .findById(dto.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found with id: " + dto.getTeacherId()
                        ));

        course.setTeacher(teacher);

        // Save to DB
        Course savedCourse = courserepository.save(course);

        // Return Response DTO
        return courseMapper.toResponseDto(savedCourse);
    }


    //Bulk create
    @Override
    @Transactional
    public List<CourseResponseDto> createMultipleCourses(List<CourseRequestDto> dtos) {

        List<Course> courses = dtos.stream().map(dto -> {

            Course course = courseMapper.toEntity(dto);

            // Fetch Department
            Department department = departmentrepository
                    .findById(dto.getDepartmentId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Department not found with id: " + dto.getDepartmentId()
                            ));

            course.setDepartment(department);

            // Fetch Teacher
            Teacher teacher = teacherrepository
                    .findById(dto.getTeacherId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Teacher not found with id: " + dto.getTeacherId()
                            ));

            course.setTeacher(teacher);

            return course;

        }).toList();

        // Save all
        List<Course> savedCourses = courserepository.saveAll(courses);

        return courseMapper.toResponseDto(savedCourses);
    }

    //get course by id

    @Override
    @Transactional
    public  CourseResponseDto getCourseByID(Long id){

        Course course = courserepository.findById(id).
                orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id: " + id));

        return courseMapper.toResponseDto(course);
    }

    //get all courses
    @Override
    @Transactional()
     public   List<CourseResponseDto> getAllCourses(){

        List<Course> course = courserepository.findAll();

        return courseMapper.toResponseDto(course);

    }
    @Override
    @Transactional
    public CourseResponseDto updateCourse(CourseRequestDto dto, Long id) {

        Course existingCourse = courserepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id
                        ));

        // update simple fields
        courseMapper.updateCourseFromDto(dto, existingCourse);

        // update department
        Department department = departmentrepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + dto.getDepartmentId()
                        ));

        existingCourse.setDepartment(department);

        // update teacher
        Teacher teacher = teacherrepository
                .findById(dto.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found with id: " + dto.getTeacherId()
                        ));

        existingCourse.setTeacher(teacher);

        return courseMapper.toResponseDto(existingCourse);
    }



    //delete course
    @Override
    @Transactional
    public void deleteCourse(Long id) {

        Course existingCourse = courserepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id " + id));

        courserepository.delete(existingCourse);
    }




}
