package com.staunch.tech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_locations")
public class Locations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int id;
	
	@Column(name = "facility_code")
	private String facCode;
	
	@Column(name = "location_name")
	private String name;
	
	@Column(name = "location_description")
	private String description;
	
    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;
    
    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;
    
    @Column(name = "ADDRESS_LINE3")
    private String addressLine3;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "STATE")
    private String state;
    
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    
    @Column(name = "COUNTRY")
    private String country;
	
    @Column(name ="created_by")
    private String createdBy;
    
    @Column(name ="created_time")
    private long createdTime;
    
    @Column(name ="updated_by")
    private String updatedBy;
    
    @Column(name ="updated_time")
    private long updatedTime;
    
    @Transient
    private boolean expand;
}
