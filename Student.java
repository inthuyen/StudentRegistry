import java.util.ArrayList;

/**
 * class for Student objects.
 * Implements the Comparable interface.
 */
public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  private ArrayList<CreditCourse> courses;

    /**
     * Constructor method for class Student
     * @param name - student name
     * @param id - student ID
     */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses = new ArrayList<CreditCourse>();
  }

    /**
     * Returns the student's ID
     * @return - student ID
     */
  public String getId()
  {
	  return id;
  }

    /**
     * Returns the name of the student
     * @return - student name
     */
  public String getName()
  {
	  return name;
  }
  
  public boolean takenCourse(String courseCode)
  {
    for (int j = 0; j < courses.size(); j++)
    {
      if (courses.get(j).getCode().equalsIgnoreCase(courseCode))
        return true;
	}
    return false;
  }

    /**
     * Adds a credit course to list of course for this student
     * @param courseName - course name
     * @param courseCode - course code
     * @param descr - course description
     * @param format - course format
     * @param sem - semester the course is active
     * @param grade - grade achieved in course
     */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  CreditCourse cc = new CreditCourse(courseName,courseCode,descr,format,sem, grade);
	  cc.setActive();
	  courses.add(cc);
  }

// Gets the grade the student achieved in a course
  public double getGrade(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	  {
		if (courses.get(i).getCode().equals(courseCode))
		{
			return courses.get(i).grade; 
		}
	  }
	  return 0;
  }

  // sets the grade a student achieved
  public void setGrade(String courseCode, double grade)
  {
    for (int k = 0; k < courses.size(); k++)
    {
	   if (courses.get(k).getCode().equalsIgnoreCase(courseCode))
	   {
		  courses.get(k).grade = grade;
		  courses.get(k).setInactive();
	   }
    }
  }

  // prints the student's transcript
  public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++)
	  { 
		  if (!courses.get(i).active) 
			  System.out.println(courses.get(i).displayGrade());
	  }
  }


    /**
     * Prints all active courses this student is enrolled in
     */
  public void printActiveCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 } 
  }
  
  public void printCompletedCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (!courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 }
  }
  
  // Student has dropped course
  public void removeActiveCourse(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).getCode().equals(courseCode) && courses.get(i).active) 
		 {
            courses.remove(i);
            return;
		 }
	 }
  }
    /**
     * toString method to return student id and name
     * @return - student id and name
     */
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }

    /**
     * Sort student names alphabetically
     * @param other - other student object
     * @return - sorted name
     */
  public int compareTo(Student other)
  {
	  return this.name.compareTo(other.name);
  }

    /**
     * Checs if student names are equal *and* student ids are equal (of "this" student
     * and "other" student)
     * @param other - other student
     * @return - true or false
     */
  public boolean equals(Object other)
  {
	  Student s = (Student) other;
	  return this.name.equals(s.name) && this.id.equals(s.id);
  }
  
}
