package com.patela.marketplace.domain.entities;
;
import com.patela.marketplace.domain.enums.AttachmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class Attachment extends BaseEntity<Integer> {

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
