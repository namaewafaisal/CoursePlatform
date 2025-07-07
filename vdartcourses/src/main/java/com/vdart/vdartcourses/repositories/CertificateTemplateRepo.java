package com.vdart.vdartcourses.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vdart.vdartcourses.collections.CertificateTemplate;

public interface CertificateTemplateRepo extends MongoRepository<CertificateTemplate, ObjectId> {
    
    Optional<CertificateTemplate> findByCourseId(ObjectId courseId);

}
