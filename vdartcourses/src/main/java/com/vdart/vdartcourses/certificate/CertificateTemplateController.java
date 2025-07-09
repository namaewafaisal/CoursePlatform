package com.vdart.vdartcourses.certificate;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.ResourceNotFoundException;



@RestController
@RequestMapping("/api/certificate-templates")
public class CertificateTemplateController {

    @Autowired
    private CertificateTemplateService certificateTemplateService;

    @GetMapping
    public List<CertificateTemplate> getCertificateTemplate() {
        // Logic to retrieve the certificate template by courseId
        return certificateTemplateService.getAllCertificateTemplates();
    }
    @GetMapping("/{courseId}")
    public CertificateTemplate getCertificateTemplateByCourse(@PathVariable ObjectId courseId) {
        return certificateTemplateService.getCertificateTemplateByCourseId(courseId)
        .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id: " + courseId));
    }
    @GetMapping("/id/{id}")
    public CertificateTemplate getCertificateTemplateById(@PathVariable ObjectId id) {
        return certificateTemplateService.getCertificateTemplateById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id: " + id));
    }

    // @PostMapping("/post")
    // public CertificateTemplate postMethodName(@RequestBody String entity) {
    //     //TODO: process POST request

    //     return certificateTemplateService.saveCertificateTemplate(entity);
    // }
    
}
