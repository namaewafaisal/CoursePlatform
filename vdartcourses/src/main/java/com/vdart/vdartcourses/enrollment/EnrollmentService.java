package com.vdart.vdartcourses.enrollment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.course.Course;
import com.vdart.vdartcourses.course.CourseService;
import com.vdart.vdartcourses.service.CurrentUserService;
import com.vdart.vdartcourses.service.ResourceNotFoundException;


@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private CourseService courseService;

    public List<Course> getUserCourses() {
        String username = currentUserService.getUsername();
        List<Enrollment> userEnrollments = enrollmentRepo.findByUsername(username);
        List<Course> userCourses = userEnrollments.stream()
            .map(enroll -> courseService.getCourseById(new ObjectId(enroll.getCourseId())).orElseThrow(() -> new ResourceNotFoundException("Course not found")))
            .collect(Collectors.toList());

        return userCourses;
    }

    public Enrollment enrollUserInCourse(Enrollment enrollment) {
        String username = currentUserService.getUsername();
        enrollment.setUsername(username);
        return enrollmentRepo.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    public void deleteEnrollment(ObjectId id) {
        enrollmentRepo.deleteById(id);
    }

    public Enrollment updateEnrollment(ObjectId id, Enrollment enrollment) {
        String username = currentUserService.getUsername();
        Enrollment enrollment2 = enrollmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        if(!(enrollment2.getUsername().equals(username))) {
            throw new ResourceNotFoundException("You are not authorized to update this enrollment.");       
        }
        if(enrollment.getCourseId() != null) {
            enrollment2.setCourseId(new ObjectId(enrollment.getCourseId()));
        }
        if(enrollment.getUsername()!= null) {
            enrollment2.setUsername(enrollment.getUsername());
        }
        if(enrollment.getEnrollmentDate()!= null) {
            enrollment2.setEnrollmentDate(enrollment.getEnrollmentDate());
        }
        if(enrollment.getProgress()!= null) {
            enrollment2.setProgress(enrollment.getProgress());
        }
        return enrollmentRepo.save(enrollment2);
    }

    public void deleteAll() {
        enrollmentRepo.deleteAll();
    }
    public Optional<Enrollment> searchForEnrollment(Enrollment enrollment) {
        String username = currentUserService.getUsername();
        String courseId = enrollment.getCourseId();
        return enrollmentRepo.findByUsernameAndCourseId(username, new ObjectId(courseId));
    }
}
