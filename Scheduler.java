// Inthuyen Naguleswaran
// 500978904
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

// class that shows the schedule for courses
public class Scheduler 
{

	TreeMap<String,ActiveCourse> schedule;
	String[][] table =
			{
					{" ", "Mon", "Tue", "Wed", "Thur", "Fri"},
					{"0800", " ", " ", " ", " ", " "},
					{"0900", " ", " ", " ", " ", " "},
					{"1000", " ", " ", " ", " ", " "},
					{"1100", " ", " ", " ", " ", " "},
					{"1200", " ", " ", " ", " ", " "},
					{"1300", " ", " ", " ", " ", " "},
					{"1400", " ", " ", " ", " ", " "},
					{"1500", " ", " ", " ", " ", " "},
					{"1600", " ", " ", " ", " ", " "},
					{"1700", " ", " ", " ", " ", " "}
			};

	// constructor method
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	}

	// sets the day and time of a course
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		ActiveCourse ac = findCourse(courseCode);
		ac.setLectureDay(day);
		ac.setLectureStart(startTime);
		ac.setLectureDuration(duration);
	}

	// clears the schedule
	public void clearSchedule(String courseCode)
	{
		ActiveCourse ac = findCourse(courseCode);
		if (ac != null)
		{
			ac.setLectureDay("");
			ac.setLectureStart(0);
			ac.setLectureDuration(0);

			for (int j = 1; j < table[0].length; j ++)
			{
				for (int i = 1; i < table.length; i ++)
				{
					if (courseCode.equalsIgnoreCase(table[i][j]))
					{
						table[i][j] = " ";
					}
				}
			}
		}
	}

	// prints the schedule
	public void printSchedule()
	{
		// Update Schedule
		for (ActiveCourse ac : schedule.values())
		{
			String courseCode = ac.getCode();
			String day = ac.getLectureDay();
			String time = (String) Integer.toString(ac.getLectureStart());
			int duration = ac.getLectureDuration();
			int col = 0;
			int row = 0;

			for (int j = 0; j < table[0].length; j++)
			{
				if (day.equalsIgnoreCase(table[0][j]))
				{
					col = j;
				}
			}
			for (int i = 1; i < table.length; i++)
			{
				if (i == 1 && time.equalsIgnoreCase("800"))
				{
					row = 1;
				}
				else if (i == 2 && time.equalsIgnoreCase("900"))
				{
					row = 2;
				}
				else if (time.equalsIgnoreCase(table[i][0]))
				{
					row = i;
				}
			}
			for (int k = 0; k < duration; k++)
			{
				table[row + k][col] = courseCode;
			}
		}

		// Print Schedule
		for (int i = 0; i < table.length; i++)
		{
			for (int j = 0; j < table[0].length; j++)
			{
				System.out.printf("%-10s", table[i][j]);
			}
			System.out.println("\n");
		}
	}

	// finds a certain ActiveCourse
	public ActiveCourse findCourse(String code)
	{
		for (String key : schedule.keySet())
		{
			if (key.equalsIgnoreCase(code))
				return schedule.get(key);
		}
		return null;
	}

	// returns the schedule treemap
	public TreeMap<String,ActiveCourse> getSchedule()
	{
		return schedule;
	}
	
}

