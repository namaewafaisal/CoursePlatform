package com.vdart.vdartcourses.certificate;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateTemplateService {

    @Autowired
    private CertificateTemplateRepo certificateTemplateRepo;

    // Method to save a certificate template
    public CertificateTemplate saveCertificateTemplate(CertificateTemplate certificateTemplate) {
        return certificateTemplateRepo.save(certificateTemplate);
    }
    // Method to get a certificate template by ID
    public Optional<CertificateTemplate> getCertificateTemplateById(ObjectId id) {
        return certificateTemplateRepo.findById(id);
    }
    // Method to delete a certificate template by ID
    public void deleteCertificateTemplateById(ObjectId id) {        
        certificateTemplateRepo.deleteById(id);
    }
    // Method to get all certificate templates
    public List<CertificateTemplate> getAllCertificateTemplates() {
        return certificateTemplateRepo.findAll();
    }       
    // Method to get a certificate template by course ID
    public Optional<CertificateTemplate> getCertificateTemplateByCourseId(ObjectId courseId) {
        return certificateTemplateRepo.findByCourseId(courseId);
    }   
}
