
package com.staunch.tech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_assets")
public class Asset {
    @Id
    @Column(name ="asset_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="name")
    private String name;
    @Column(name ="code")
    private String code;
    @Column(name = "serialNo")
    private int serialNo;
    @Column(name ="description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "facility_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "area_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Area area;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "room_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;
    
    @Column(name = "category")
    private String category;
    @Column(name = "department")
    private String department;
    @Column(name = "sub_asset")
    private String subAsset;
    
    @Column(name = "asset_system")
    private String  system;
    
    @Column(name = "supplier")
    private String supplier;
    
    @Column(name = "Status")
    private String status; 
    
    @Column(name = "priority")
    private String priority;
    
    @Column(name = "Make")
    private String make;    
    
    @Column(name = "model")
    private String model;
    
    
   
    @Column(name ="price")
    private float price;

    
    
    
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