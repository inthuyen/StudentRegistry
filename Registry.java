import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Registry
{
   private TreeMap<String, Student> students = new TreeMap<String, Student>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<String, ActiveCourse>();

   
   public Registry() throws IOException {
	   // Add some students
		readStudents();

	   ArrayList<Student> list = new ArrayList<Student>();
	   // CPS209
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   courses.put("CPS209", new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

	   // CPS511
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   courses.put("CPS511", new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));

	   // CPS643
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   courses.put("CPS643", new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

	   // CPS706
	   courseName = "Computer Networks";
	   courseCode = "CPS706";
	   descr = "Learn about Computer Networking";
	   format = "3Lec 1Lab";
	   courses.put("CPS706", new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

	   // CPS616
	   courseName = "Algorithms";
	   courseCode = "CPS616";
	   descr = "Learn about Algorithms";
	   format = "3Lec 1Lab";
	   courses.put("CPS616", new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
   }

   // Reads student names and ids from a file
   public void readStudents() throws FileNotFoundException, IOException
   {
	   String name = "";
	   String id = "";

	   File studentFile = new File("students.txt");
	   Scanner input = new Scanner(studentFile);
	   while (input.hasNextLine())
	   {
		   if (input.hasNext())
		   {
		   		name = input.next();
		   }
		   else
		   {
		   	throw new IOException("Bad File Format");
		   }
		   
		   if (input.hasNext())
			{
				id = input.next();
			}
			else
		   {
		   	throw new IOException("Bad File Format");
		   }
		   
		   students.put(id, new Student(name, id));
	   }
   }

   // adds a new student to the registry
   public boolean addNewStudent(String name, String id)
   {
	   if (findStudent(id) != null) return false;
	   	   
	   students.put(id, new Student(name, id));
	   return true;
   }

   // removes a student from the registry
   public boolean removeStudent(String studentId)
   {
   	 for (String key : students.keySet())
	 {
	 	if (key.equals(studentId))
		{
			students.remove(key);
			return true;
		}
	 }
	 return false;
   }

   // prints all students
   public void printAllStudents()
   {
		for (String key : students.keySet())
		{
		   System.out.println("ID: " + key + " Name: " + students.get(key).getName());
		}
   }

   // finds a student in the registry
   public Student findStudent(String id)
   {
     for (String key : students.keySet())
	 {
	   Student s = students.get(key);
	   if (s.getId().equals(id))
		   return s;
     }
     return null;
   }

   // finds an ActiveCourse
   public ActiveCourse findCourse(String code)
   {
     for (String key : courses.keySet())
	 {
	   if (key.equalsIgnoreCase(code))
	     return courses.get(key);
	 }
     return null;
   }

   // adds a student to a course
   public void addCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   if (s.takenCourse(courseCode)) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   if (ac.enrolled(studentId)) return;
			   
	   ac.students.add(s);
	   s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
   }
   
  // drops a student from a course
   public void dropCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.remove(studentId);
	   s.removeActiveCourse(courseCode);
   }

   // prints all active courses
   public void printActiveCourses()
   {
      for (String key : courses.keySet())
	     System.out.println(courses.get(key).getDescription());
   }

   // prints the class list of a course
   public void printClassList(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printClassList();
   }

   // sorts the courses by name
   public void sortCourseByName(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortByName();
   }

   // sorts the courses by id
   public void sortCourseById(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortById();	   
   }

   // prints the grades students have achieved in a course
   public void printGrades(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
   }

   // prints the courses a student has taken
   public void printStudentCourses(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
   }

   // prints a student's transcript
   public void printStudentTranscript(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printTranscript();
   }

   // sets the final grade of a student
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade);
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   ac.remove(studentId);
   }

   // returns the courses treemap
   public TreeMap<String, ActiveCourse> getCourses()
   {
   	return courses;
   }
}
