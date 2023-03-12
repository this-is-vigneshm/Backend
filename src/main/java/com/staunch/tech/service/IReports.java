package com.staunch.tech.service;

import com.staunch.tech.dto.BubbleChartDto;
import com.staunch.tech.dto.Reports3Dto;
import com.staunch.tech.dto.ReportsDto;
import com.staunch.tech.entity.Reports2D;
import com.staunch.tech.entity.Reports3D;

import java.util.List;


public interface IReports {

	
	List<ReportsDto> calculateAmountSpent();
	
	List<ReportsDto> calculatWeeklyReports();

	List<Reports3Dto> calculateRadarReport();
	
	List<BubbleChartDto> calculateBubbleReport();

	String generate2D(List<Reports2D> reports2d);
	
	String generate3D(List<Reports3D> reports3d);
}
