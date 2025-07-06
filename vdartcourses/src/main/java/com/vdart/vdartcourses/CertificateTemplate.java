package com.vdart.vdartcourses;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "certificate_templates")
public class CertificateTemplate {

    @Id
    private ObjectId id;
    
    private ObjectId courseId; 
    public ObjectId getId() {
        return id;
    }
    public CertificateTemplate() {
    }
    public CertificateTemplate(ObjectId id, ObjectId courseId, String templateUrl, CertificateFields fields, String domain) {
        this.id = id;
        this.courseId = courseId;
        this.templateUrl = templateUrl;
        this.fields = fields;
        this.domain = domain;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public ObjectId getCourseId() {
        return courseId;
    }
    public void setCourseId(ObjectId courseId) {
        this.courseId = courseId;
    }
    public String getTemplateUrl() {
        return templateUrl;
    }
    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }
    public CertificateFields getFields() {
        return fields;
    }
    public void setFields(CertificateFields fields) {
        this.fields = fields;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    private String templateUrl; // URL to the certificate template
    private CertificateFields fields; // Fields to be filled in the certificate
    private String domain; // Domain for which the certificate is applicable


}

class CertificateFields {

    private FieldPosition name;    // e.g. print name at (x: 200, y: 450)
    private FieldPosition date;
    private FieldPosition userId;


    // Constructors, Getters, Setters

    public FieldPosition getName() {
        return name;
    }
    public FieldPosition getDate() {
        return date;
    }
    public void setDate(FieldPosition date) {
        this.date = date;
    }
    public CertificateFields() {
    }
    public CertificateFields(FieldPosition name, FieldPosition date, FieldPosition userId) {
        this.name = name;
        this.date = date;
        this.userId = userId;
    }
    public FieldPosition getUserId() {
        return userId;
    }
    public void setUserId(FieldPosition userId) {
        this.userId = userId;
    }
    public void setName(FieldPosition name) {
        this.name = name;
    }
    
}

class FieldPosition {
    private int x;
    private int y;
    private int fontSize;
    public int getX() {
        return x;
    }
    public FieldPosition(int x, int y, int fontSize) {
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }
    public FieldPosition() {
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    // Constructors, Getters, Setters
}

