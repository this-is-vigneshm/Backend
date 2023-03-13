package com.staunch.tech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.LookUpsDto;
import com.staunch.tech.entity.LookUps;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.LookUpsRepository;
import com.staunch.tech.service.ILookUpsService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;

@Service
public class LookUpsService implements ILookUpsService{

	@Autowired
	private ValidationUtils validations;
	
	@Autowired
	private LookUpsRepository loopUpsRepo;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public LookUps registerLookUps(LookUpsDto lookups) {
		try {
			validations.validate(lookups);
			var userOpt = employeeRepository.findById(lookups.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			return loopUpsRepo.save(ConversionUtils.convertDtoToNewEntity(lookups, userOpt.get().getName()));
			}
		catch(DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());}
			
	}

	@Override
	public List<LookUps> viewLookUps() {
		var lookUpsList = loopUpsRepo.findAll();
		if(lookUpsList.isEmpty())
		{
			throw new AssetManagementException("LookUps Empty");
		}
		return loopUpsRepo.findAll();
	}

	@Override
	public LookUps editLookUps(int id, LookUpsDto lookUps) {
		try {
			validations.validate(lookUps);
			var lookUpsOpt = loopUpsRepo.findById(id);
			if(lookUpsOpt.isEmpty()) {
				throw new AssetManagementException("LookUps Id is Invalid!");
			}
			var userOpt = employeeRepository.findById(lookUps.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			return loopUpsRepo.save(ConversionUtils.convertDtoToUpdateEntity(lookUpsOpt.get(),lookUps, userOpt.get().getName()));
			}
		catch(DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());}
	}

	@Override
	public String deleteLookUps(int id) {
		var lookUpsOpt = loopUpsRepo.findById(id);
		if(lookUpsOpt.isEmpty()) {
			throw new AssetManagementException("LookUps Id is Invalid!");
		}
		loopUpsRepo.deleteById(id);
		return "Deleted Successfully";
	}

}
