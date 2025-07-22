package com.vdart.vdartcourses.certificate;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.service.ResourceNotFoundException;




@RestController
@RequestMapping("/certificates")
public class CertificateTemplateController {

    @Autowired
    private CertificateTemplateService certificateTemplateService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public List<CertificateTemplate> getCertificateTemplate() {
        // Logic to retrieve the certificate template by courseId
        return certificateTemplateService.getAllCertificateTemplates();
    }
    @GetMapping("courseid/{courseId}")
    @PreAuthorize("isAuthenticated()")
    public CertificateTemplate getCertificateTemplateByCourse(@PathVariable ObjectId courseId) {
        return certificateTemplateService.getCertificateTemplateByCourseId(courseId)
        .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id: " + courseId));
    }
    // @GetMapping("/id/{id}")
    // public CertificateTemplate getCertificateTemplateById(@PathVariable ObjectId id) {
    //     return certificateTemplateService.getCertificateTemplateById(id)
    //     .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id: " + id));
    // }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public CertificateTemplate addCertificateTemplate(@RequestBody CertificateTemplate entity) {
        return certificateTemplateService.saveCertificateTemplate(entity);
    }
    
    @PutMapping("courseid/{id}/update")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public CertificateTemplate updateCertificateTemplate(@PathVariable ObjectId id, @RequestBody CertificateTemplate certificateTemplate) {
        
        CertificateTemplate certificateData = certificateTemplateService.getCertificateTemplateById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); 
        certificateData.setFields(certificateTemplate.getFields());
        certificateData.setCourseId(certificateTemplate.getCourseId());
        certificateData.setDomain(certificateTemplate.getDomain());
        certificateData.setTemplateUrl(certificateTemplate.getTemplateUrl());
        return certificateData;
    }

    @DeleteMapping("/courseid/{id}/delete")
    public ResponseEntity<String> deleteCertificate(@PathVariable ObjectId id){
        certificateTemplateService.deleteCertificateTemplateById(id);
        return ResponseEntity.ok("Deleted successfully");

}
}