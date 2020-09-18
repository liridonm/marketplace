package com.patela.marketplace.model;

import com.patela.marketplace.model.annotation.AttachmentType;
import lombok.Getter;
import lombok.Setter;

import javax.jws.WebParam;
import javax.persistence.*;

@Entity
@Table(name = "attachment")
@Getter
@Setter
public class Attachment extends Model<Integer> {

    private String name;

    private String url;

    @Column(name = "table_id")
    private Integer tableId;

    private String model;

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    @Column(name = "mime_type")
    private String mimeType;

}
