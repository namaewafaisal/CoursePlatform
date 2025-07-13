package com.vdart.vdartcourses.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.ResourceNotFoundException;
import com.vdart.vdartcourses.user.User;
import com.vdart.vdartcourses.user.UserRepo;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Enrollment> getUserCourses(ObjectId userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Enrollment> userEnrollments = enrollmentRepo.findByUserId(userId);

        return userEnrollments;
    }

    public void enrollUserInCourse(ObjectId userId, ObjectId courseId, Enrollment enrollment) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        enrollment.setUserId(userId);
        enrollment.setCourseId(courseId);
        enrollmentRepo.save(enrollment);
    }
}
