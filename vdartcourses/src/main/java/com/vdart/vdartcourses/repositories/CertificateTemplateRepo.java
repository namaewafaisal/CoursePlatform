package com.vdart.vdartcourses.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vdart.vdartcourses.collections.CertificateTemplate;

public interface CertificateTemplateRepo extends MongoRepository<CertificateTemplate, ObjectId> {
    
    

}
