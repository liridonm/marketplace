package com.patela.marketplace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Model<T> {

    /**
     * Model id as primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    /**
     * Deleted field.
     */
    private boolean deleted = false;

//    /**
//     * Model create date.
//     */
//    @JsonIgnore
//    @Column(name="create_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    /**
//     * Model update date.
//     */
//    @JsonIgnore
//    @Column(name="write_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updatedDate;
//
//    /**
//     * Model create user Id.
//     */
//    @JsonIgnore
//    @Column(name="create_uid", nullable = false, updatable = false)
//    private Integer createUserId;
//
//    /**
//     * Model create user Id.
//     */
//    @JsonIgnore
//    @Column(name="update_uid", nullable = false, updatable = false)
//    private Integer updateUserId;

}
