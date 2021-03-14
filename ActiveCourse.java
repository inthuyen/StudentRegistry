// Inthuyen Naguleswaran
// 500978904
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
   public  ArrayList<Student> students; // map id to name
   private String             semester;
   private int				  lectureStart;
   private int 				  lectureDuration;
   private String			  lectureDay;

   // Constructor method for ActiveCourse class
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   lectureStart = 0;
	   lectureDay = "";
	   lectureDuration = 0;
   }

   // Returns the semester the course is active in
   public String getSemester()
   {
	   return semester;
   }

   // Prints the class list for this course
   public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }

   // Prints the grades students achieved in this course
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }

   // Gets the details of the course
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }

   // Gets the course decription
   public String getCourseDescription()
   {
	   return getDescr();
   }

   // Gets the grade of a student
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }

   // Checks to see if a student is enrolled
   public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }

   // Removes a student from the course
   public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }

   // Sorts the students by name
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }


   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }

   // Sorts the students by ID
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
   }

   // Sets the lecture Start Time
   public void setLectureStart(int lectureStart)
   {
   	this.lectureStart = lectureStart;
   }

   // Sets the lecture duration
	public void setLectureDuration(int lectureDuration)
	{
		this.lectureDuration = lectureDuration;
	}

	// sets the lecture day
	public void setLectureDay(String lectureDay)
	{
		this.lectureDay = lectureDay;
	}

	// gets the lecture start time
	public int getLectureStart()
	{
		return this.lectureStart;
	}

	// gets the lecture duration
	public int getLectureDuration()
	{
		return this.lectureDuration;
	}

	// gets the lecture day
	public String getLectureDay()
	{
		return this.lectureDay;
	}
}
