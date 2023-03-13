package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.LookUpsDto;
import com.staunch.tech.entity.LookUps;

public interface ILookUpsService {

	LookUps registerLookUps(LookUpsDto lookups);
	
	List<LookUps> viewLookUps();
	
	LookUps editLookUps(int id, LookUpsDto lookUps);
	
	String deleteLookUps(int id);
	
}
