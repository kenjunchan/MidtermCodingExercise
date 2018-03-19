package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;

public class Staff_Test {
	private static ArrayList<Staff> StaffArray = new ArrayList();
	@BeforeClass
	public static void setup() throws PersonException {
		StaffArray.add(new Staff("John","Steve","Doe", new Date(1999,5,8), "403 Academy Street, Newark, DE, 19717", "(572)-829-9090" 
				,"jsDoe@udel.edu", "TH 5-7", 8, 75000.00, new Date(2012,3,18), eTitle.MR));
		StaffArray.add(new Staff("Jane","Maria","Doe", new Date(1975,7,2), "403 Academy Street, Newark, DE, 19717", "(572)-582-3784" 
				,"jmDoee@udel.edu", "T 5-7", 2, 80000.00, new Date(20010,6,23), eTitle.MS));
		StaffArray.add(new Staff("Jake","","Smith", new Date(1960,10,6), "403 Academy Street, Newark, DE, 19717", "(572)-203-2848" 
				,"jSmith@udel.edu", "TH 2-4", 5, 100000.00, new Date(1982,4,5), eTitle.MR));
		StaffArray.add(new Staff("Steve","Michael","Smith", new Date(1953,7,2), "403 Academy Street, Newark, DE, 19717", "(572)-851-3281" 
				,"smSmith@udel.edu", "F 5-7", 9, 95000.00, new Date(2012,3,18), eTitle.MR));
		StaffArray.add(new Staff("Patrick","","Donoghue", new Date(1998,1,12), "403 Academy Street, Newark, DE, 19717", "(533)-293-4833" 
				,"pDonoghue@udel.edu", "TH 5-7", 0, 57000.00, new Date(2000,5,22), eTitle.MR));
		
	}
	
	@Test
	public void test() {
		double totalSalary = 0.0;
		for(Staff staff: StaffArray)
		{
			totalSalary += staff.getSalary();
		}
		double salaryAverage = totalSalary / StaffArray.size();
		salaryAverage = (Math.round(salaryAverage * 100)) / 100;
		assertEquals(salaryAverage,81400.00,.01);
		
		boolean thrown1 = false;
		try {
			Staff wrongYear = new Staff("John","Steve","Doe", new Date(1901,3,18), "403 Academy Street, Newark, DE, 19717", "(572)-829-9090" 
					,"jsDoe@udel.edu", "TH 5-7", 8, 75000.00, new Date(2012,3,18), eTitle.MR);
		}
		catch(PersonException e)
		{
			thrown1 = true;
		}
		boolean thrown2 = false;
		try {
			Staff wrongPhone = new Staff("John","Steve","Doe", new Date(1999,5,8), "403 Academy Street, Newark, DE, 19717", "27-329-94090" 
					,"jsDoe@udel.edu", "TH 5-7", 8, 75000.00, new Date(2012,3,18), eTitle.MR);
		}
		catch(PersonException e)
		{
			thrown2 = true;
		}
		
		assertTrue(thrown1);
		assertTrue(thrown2);
	}	

}
