package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {

	private static ArrayList<Course> courseArray = new ArrayList<Course>();
	private static ArrayList<Semester> semesterArray = new ArrayList<Semester>();
	private static ArrayList<Section> sectionArray = new ArrayList<Section>();
	private static ArrayList<Student> studentArray = new ArrayList<Student>();
	private static ArrayList<Enrollment> enrollmentArray = new ArrayList<Enrollment>();
	
	@BeforeClass
	public static void setup() throws PersonException {
		final UUID CISC181_UUID = UUID.randomUUID();
		final UUID PHYS207_UUID = UUID.randomUUID();
		final UUID CHEM103_UUID = UUID.randomUUID();
		
		courseArray.add(new Course(CISC181_UUID, "CISC181", 3, eMajor.COMPSI));
		courseArray.add(new Course(PHYS207_UUID, "PHYS207", 4, eMajor.PHYSICS));
		courseArray.add(new Course(CHEM103_UUID, "CHEM103", 4, eMajor.CHEM));
		
		final UUID FALL_UUID = UUID.randomUUID();
		final UUID SPRING_UUID = UUID.randomUUID();
		
		semesterArray.add(new Semester(FALL_UUID, new Date(2017, 8, 29), new Date(2017, 12, 16))); // Fall Semester
		semesterArray.add(new Semester(SPRING_UUID, new Date(2018, 2, 5), new Date(2018, 5, 24))); // Spring Semester
		
		int roomID = 1;
		for(Semester Semester: semesterArray)
		{
			for(Course Course: courseArray)
			{
				sectionArray.add(new Section(Course.getCourseID(),Semester.getSemesterID(),UUID.randomUUID(),roomID));
				roomID += 2;
			}
		}
		
		studentArray.add(new Student("Ken", "Jun", "Chan", new Date(1999, 5, 8), eMajor.COMPSI, "123 Merryville Lane", "(728)-232-5281", "kjchan@udel.edu"));
		studentArray.add(new Student("Jane", "Madison", "James", new Date(1980, 4, 21), eMajor.BUSINESS, "123 Merryville Lane", "(452)-294-1939", "jmJames@udel.edu"));
		studentArray.add(new Student("Patrick", "Andrew", "Donoghue", new Date(1998, 12, 7), eMajor.CHEM, "123 Merryville Lane", "(321)-397-1381", "paDonoghue@udel.edu"));
		studentArray.add(new Student("Sabrina", "Christoper", "Smith", new Date(1972, 7, 2), eMajor.COMPSI, "123 Merryville Lane", "(328)-532-5232", "scSmith@udel.edu"));
		studentArray.add(new Student("Ben", "", "Atlas", new Date(1995, 11, 25), eMajor.NURSING, "123 Merryville Lane", "(987)-737-1847", "bAtlas@udel.edu"));
		studentArray.add(new Student("Dariusz", "", "Panek", new Date(1997, 9, 23), eMajor.COMPSI, "123 Merryville Lane", "(726)-591-5395", "dpanek@udel.edu"));
		studentArray.add(new Student("Sam", "Chris", "Smith", new Date(1996, 1, 10), eMajor.BUSINESS, "123 Merryville Lane", "(398)-342-5353", "sSmith@udel.edu"));
		studentArray.add(new Student("Nicholas", "", "Ho", new Date(1999, 2, 5), eMajor.COMPSI, "123 Merryville Lane", "(825)-239-5223", "nHo@udel.edu"));
		studentArray.add(new Student("Will", "Michael", "James", new Date(2000, 1, 16), eMajor.PHYSICS, "123 Merryville Lane", "(173)-942-2341", "wmJames@udel.edu"));
		studentArray.add(new Student("Ryan", "", "White", new Date(1994, 2, 23), eMajor.COMPSI, "123 Merryville Lane", "(392)-634-2831", "rWhite@udel.edu"));
		
		for(Student s : studentArray)
		{
			for(Section se : sectionArray)
			{
				enrollmentArray.add(new Enrollment(s.getStudentID(), se.getSectionID()));
			}
		}
		for(Enrollment e : enrollmentArray)
		{
			e.SetGrade(90.0);
		}
	}
	

	@Test
	public void test() {
		double creditTotal, totalGrade;
		for(Student s: studentArray)
		{
			creditTotal = 0.0;
			totalGrade = 0.0;
			for(Enrollment e: enrollmentArray)
			{
				if(s.getStudentID().equals(e.getStudentID()))
				{
					for(Section se : sectionArray)
					{
						if(se.getSectionID().equals(e.getSectionID()))
						{
							for(Course c : courseArray)
							{
								if(c.getCourseID().equals(se.getCourseID()))
								{
									totalGrade += e.getGrade() * c.getGradePoints();
									creditTotal += c.getGradePoints();
								}
							}
						}
					}
				}
			}
			assertEquals(totalGrade/creditTotal,90.0,.01);
		}
		double enrolledCourses;
		for(Section se : sectionArray)
		{
			enrolledCourses = 0.0;
			totalGrade = 0.0;
			for(Enrollment e: enrollmentArray)
			{
				if(se.getSectionID().equals(e.getSectionID()))
				{
					enrolledCourses += 1;
					totalGrade += e.getGrade();
				}
			}
			assertEquals((totalGrade / enrolledCourses), 90.0, .01);
		}
		
	}
}