package com.vdart.vdartcourses.certificate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Document(collection = "certificate_templates")
public class CertificateTemplate {

    @Id
    private ObjectId id;
    
    private ObjectId courseId; 
    private String templateUrl; // URL to the certificate template
    private CertificateFields fields; // Fields to be filled in the certificate
    private String domain; // Domain for which the certificate is applicable

    public String getId() {
        return id != null ? id.toHexString() : null;
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor

class CertificateFields {

    private FieldPosition name;    // e.g. print name at (x: 200, y: 450)
    private FieldPosition date;
    private FieldPosition userId;

    
    
}
@Data
@NoArgsConstructor
@AllArgsConstructor

class FieldPosition {
    private int x;
    private int y;
    private int fontSize;
}

