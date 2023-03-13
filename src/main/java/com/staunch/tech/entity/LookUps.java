package com.staunch.tech.entity;

import java.util.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_lookups")
public class LookUps {
	
	@Id
	@Column(name = "lookups_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "lookups_names")
	private String name;
	
	@ElementCollection
    @CollectionTable(name="T_VALUE")
    @MapKeyColumn(name="VALUE_NAMES")
    @Column(name="VALUE")
    private Map<String, String> values = new HashMap<>();
	
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
