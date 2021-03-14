// Inthuyen Naguleswaran
// 500978904
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

// Class to run the simulator
public class StudentRegistrySimulator
{
  public static void main(String[] args) // main method
  {
  	// Catch exceptions when reading the file with student names and ids
	  Registry registry;
	  try
	  {
		  registry = new Registry();
	  }
	  catch (FileNotFoundException exception) //catch block for FileNotFoundException
	  {
		  System.out.println("File not found.");
		  return;
	  }
	  catch (IOException exception)
	  {
		  System.out.println("Bad File Format."); //catch block for IOException
		  return;
	  }
	  // creating Scheduler object
	  Scheduler scheduler = new Scheduler(registry.getCourses());

	  // Get input from console
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equals("Q") || command.equals("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG")) // register a student
		  {
			  String name = null;
			  String id   = null;
			  if (commandLine.hasNext())
			  {
				 name = commandLine.next();
				 // check for all alphabetical
				 String lcase = name.toLowerCase();
				 if (!isStringOnlyAlphabet(lcase))
				 {
				   System.out.println("Invalid Characters in Name " + name);
				   continue;
				 }
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
				 // check for all numeric
				 if (!isNumeric(id))
				 {
				   System.out.println("Invalid Characters in ID " + id);
				   continue;
				 }
				 if (!registry.addNewStudent(name,id))
					 System.out.println("Student " + name + " already registered");
			  }
			 
		  }
		  else if (command.equalsIgnoreCase("DEL")) // delete a student
		  {
			  if (commandLine.hasNext())
			  {
				 String id = commandLine.next();
				 // check for all numeric
				 
				 if (!isNumeric(id))
				   System.out.println("Invalid Characters in student id " + id);
				 registry.removeStudent(id);
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC")) // print active courses
		  {
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL")) // print class list
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printClassList(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR")) // prints the students' grade in a course
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printGrades(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("ADDC")) // add a student to a course
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.addCourse(id, courseCode);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC")) // drop a student from a course
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.dropCourse(id, courseCode);
			  }
			  
		  }
		  
		  else if (command.equalsIgnoreCase("PSC")) // prints a student's courses
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentCourses(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("PST")) // prints the student's transcript
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentTranscript(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG")) // sets the final grade in a course for a student
		  {
			  String courseCode = null;
			  String id         = null;
			  String grade      = null;
			  double numGrade = 0;
			  
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				  grade = commandLine.next();
				  if (!isNumeric(grade)) continue;
				  numGrade = Double.parseDouble(grade);
				  registry.setFinalGrade(courseCode, id, numGrade);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("SCN")) // sorts the course by name
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.sortCourseByName(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("SCI")) // sorts the course by id
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.sortCourseById(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase(("SCH"))) // create a schedule for a course
		  {
			  String courseCode = null;
			  String day = null;
			  int start = 0;
			  int duration = 0;

			  if (commandLine.hasNext())
			  {
				  courseCode = commandLine.next();
				  ActiveCourse ac = registry.findCourse(courseCode);
				  if (ac == null)
				  {
				  	throw new UnknownCourse(courseCode); // exception for an unknown course
				  }
			  }
			  if (commandLine.hasNext())
			  {
				  day = commandLine.next();
				  if (!(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") ||
						  day.equalsIgnoreCase("Wed") || day.equalsIgnoreCase("Thur") ||
						  day.equalsIgnoreCase("Fri")))
				  {
				  	throw new InvalidDay(); // exception for an invalid day
				  }
			  }
			  if (commandLine.hasNextInt())
			  {
				  start = commandLine.nextInt();
			  }
			  if (commandLine.hasNextInt())
			  {
				  duration = commandLine.nextInt();
				  if (!(duration == 1 || duration == 2 || duration == 3))
				  {
				  throw new InvalidDuration(); // exception for an invalid duration
				  }
			  }
			  if ((start < 800) || ((duration * 100) + start) > 1700)
			  {
			  	throw new InvalidTime(); // exception for an invalid time
			  }
			  for (ActiveCourse ac : scheduler.schedule.values())
			  {
			  	if (ac.getLectureDay().equalsIgnoreCase(day))
				{
					int acEndTime = ac.getLectureStart() + (ac.getLectureDuration() * 100);
					int EndTime = start + (duration * 100);
					if ((start == ac.getLectureStart()) || (ac.getLectureStart() < start && start < acEndTime) ||
						(start < ac.getLectureStart() && EndTime > ac.getLectureStart()))
					{
						throw new LectureTimeCollision(); // exception for overlapping lectures
					}
				}
			  }
			  scheduler.setDayAndTime(courseCode, day, start, duration);
		  }
		  else if (command.equalsIgnoreCase(("CSCH"))) // clears the schedule
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				  courseCode = commandLine.next();
				  scheduler.clearSchedule(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase(("PSCH"))) // prints the schedule
		  {
			  scheduler.printSchedule();
		  }
		  System.out.print("\n>");
	  }
  }
  
  private static boolean isStringOnlyAlphabet(String str)  // checks to see if input only contains letters
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); 
  } 
  
  public static boolean isNumeric(String str) // checks if input only contains digits
  {
      for (char c : str.toCharArray())
      {
          if (!Character.isDigit(c)) return false;
      }
      return true;
  }
}

class UnknownCourse extends RuntimeException // class for Unknown Course exception
{
	public UnknownCourse()
	{}
	public UnknownCourse(String message)
	{
		super("Unknown course " + message);
	}
}

class InvalidDay extends RuntimeException // class for Invalid Day exception
{
	public InvalidDay()
	{
		super("Invalid Lecture Day");
	}
}

class InvalidTime extends RuntimeException // class for Invalid Time exception
{
	public InvalidTime()
	{
		super("Invalid Lecture Start Time");
	}
}

class InvalidDuration extends RuntimeException // class for Invalid Duration exception
{
	public InvalidDuration()
	{
		super("Invalid Lecture Duration");
	}
}

class LectureTimeCollision extends RuntimeException // class for overlapping lectures exception
{
	public LectureTimeCollision()
	{
		super("Invalid Lecture Time");
	}
}