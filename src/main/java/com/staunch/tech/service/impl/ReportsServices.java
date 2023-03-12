package com.staunch.tech.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.BubbleChartDto;
import com.staunch.tech.dto.Reports3Dto;
import com.staunch.tech.dto.ReportsDto;
import com.staunch.tech.entity.Reports2D;
import com.staunch.tech.entity.Reports3D;
import com.staunch.tech.entity.Ticket;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.Reports2DRepository;
import com.staunch.tech.repository.Reports3DRepository;
import com.staunch.tech.repository.TicketRepository;
import com.staunch.tech.service.IReports;
import com.staunch.tech.utils.ConversionUtils;

@Service
public class ReportsServices implements IReports{


	@Autowired
	private Reports2DRepository reportsRepo;
	
	@Autowired
	private Reports3DRepository reports3Repo;
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public List<ReportsDto> calculateAmountSpent() {
		List<ReportsDto> reports = new ArrayList<ReportsDto>();
		var a = reportsRepo.findAllByType("colour");
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		int pos = 0;
		for(var i:labels)
		{
			long temp=0;
			List<String> listUuid = new ArrayList<String>();
			int aa = 0;
			for(var j:a)
			{
				if(i.contains(j.getLabels()))
				{
					temp=temp+j.getValues();
					listUuid.add(aa++,j.getTicket_id());
				} 
			}
			reports.add(pos++, new ReportsDto(i, temp, listUuid));
		}
		return reports;  
	}

	@Override
	public List<ReportsDto> calculatWeeklyReports() {
		
		List<ReportsDto> reports = new ArrayList<ReportsDto>();
		var a = reportsRepo.findAllByType("weekly");
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		int pos = 0;
		for(var i:labels)
		{
			long temp=0;
			List<String> listUuid = new ArrayList<String>();
			int aa = 0;
			for(var j:a)
			{
				if(i.contains(j.getLabels()))
				{
					temp=temp+j.getValues();
					listUuid.add(aa++,j.getTicket_id());
				} 
			}
			reports.add(pos++, new ReportsDto(i, temp, listUuid));
		}
		return reports;  

	}
	
	@Override
	public List<Reports3Dto> calculateRadarReport() {
		var a = reports3Repo.findAllByType("radar");		
		List<Reports3Dto> reports = new ArrayList<Reports3Dto>();
		int pos = 0;
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		List<String> label= new ArrayList<String>();
		label.addAll(Arrays.asList("A safety hazard", "Minor Repair", "Major Repair", "Replacement Required"));
		for(var i:labels)
		{
			List<String> listUuid = new ArrayList<String>();
			List<String> month = new ArrayList<String>();
			List<Long> value = new ArrayList<Long>();
			int aa = 0;
			int bb=0;
			for(var j:label)
			{
				long temp=0;
				for(var k:a)
				{
					if(i.contains(k.getLabels()) && j.contains(k.getLabel()))
					{
						temp=temp+k.getValues();
						listUuid.add(aa++,k.getTicket_id());
					}
				}
				month.add(bb,j);
				value.add(bb++,temp);
			}
			reports.add(pos++,new Reports3Dto(i, month, value, listUuid));
		}
		return reports;
	}  
	
	public List<BubbleChartDto> calculateBubbleReport() {
		var a = reports3Repo.findAllByType("bubble");		
		List<BubbleChartDto> reports = new ArrayList<BubbleChartDto>();
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		List<String> label= new ArrayList<String>();
		label.addAll(Arrays.asList("A safety hazard", "Minor Repair", "Major Repair", "Replacement Required"));
		for(var i:label)
		{
			List<Long> type = new ArrayList<Long>();
			List<Long> value = new ArrayList<Long>();
			List<Long> no = new ArrayList<Long>();
			String o = new String();
			int bb=0;
			for(var j:labels)
			{
				long temp=0;
				long n = 0;
				for(var k:a)
				{
					if(i.equals(k.getLabel()) && j.equals(k.getLabels()))
					{
						temp=temp+k.getValues();
						n+=1;
						o =k.getLabel();
					}
				
				}
				type.add(bb,Long.parseLong(j));
				value.add(bb,temp);
				no.add(bb++,n);
			}
			if(i.equals(o)) {
				reports.add(new BubbleChartDto(o, type, value, no));}
		}
		return reports;
	}

	@Override
	public String generate2D(List<Reports2D> reports2d) {
		for(var i:reports2d) {
			reportsRepo.save(i);
		}
		return "Success";
	}

	@Override
	public String generate3D(List<Reports3D> reports3d) {
		for(var i:reports3d) {
			reports3Repo.save(i);
		}
		return "Success";
	}
}

