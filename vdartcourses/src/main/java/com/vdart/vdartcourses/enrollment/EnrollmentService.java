package com.vdart.vdartcourses.enrollment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.service.CurrentUserService;


@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private CurrentUserService currentUserService;

    public List<Enrollment> getUserCourses() {
        String username = currentUserService.getUsername();
        List<Enrollment> userEnrollments = enrollmentRepo.findByUsername(username);

        return userEnrollments;
    }

    public Enrollment enrollUserInCourse(Enrollment enrollment) {
        String username = currentUserService.getUsername();
        enrollment.setUsername(username);
        return enrollmentRepo.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }
}

