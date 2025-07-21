package com.vdart.vdartcourses.enrollment;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.user.UserService;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private UserService userService;

    public List<Enrollment> getUserCourses(String username) {

        List<Enrollment> userEnrollments = enrollmentRepo.findByUsername(username);

        return userEnrollments;
    }

    public void enrollUserInCourse(Enrollment enrollment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String userId = userService.findIdByUsername(username);
        enrollment.setUserId(new ObjectId(userId));
        enrollmentRepo.save(enrollment);
    }
}
