package com.vdart.vdartcourses.certificate;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CertificateTemplateRepo extends MongoRepository<CertificateTemplate, ObjectId> {
    
    Optional<CertificateTemplate> findByCourseId(ObjectId courseId);

}
