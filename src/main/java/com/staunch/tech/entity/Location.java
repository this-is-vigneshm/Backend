   package com.staunch.tech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_location")
public class Location {
    @Id
    @Column(name = "FACILITY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facilityId;
    @Column(name = "FACILITY_CODE", unique = true)
    @NotBlank(message = "Facility Code cannot be null")
    private String facilityCode;
    @Column(name = "FACILITY_NAME")
    @NotBlank(message = "Facility Name cannot be null")
    private String facilityName;
    @Column(name = "FACILITY_TYPE")
    private String facilityType;
    @Column(name = "FACILITY_SOURCE")
    private String facilitySource;
    @Column(name = "IS_CROSS_CODER_FLAG")
    private boolean isCrossCodeFlag;
    @Column(name = "INACTIVE_DATE")
    private String inactiveDate;
    @Column(name = "FAC_LOCATION_CODE")
    private String facLocationCode;

    
    @Column(name = "CREATED_TIME")
    private long createdTime;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATED_TIME")
    private long lastUpdatedTime;
    @Column(name = "LAST_UPDATED_BY")
    private  String lastUpdatedBy;
    
    
    @Transient
    private boolean expand;
    @Transient
    @NotNull(message = "User Id is Null")
    private int userId;
}
