import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Manager that an application uses to interact with the course database
 *
 * @author Randall Kim
 */
public class CourseDBManager implements CourseDBManagerInterface{
    private CourseDBStructure courses = new CourseDBStructure(100);

    /**
     * Adds a course to the database
     * @param id name of the course
     * @param crn unique identifier of the course
     * @param credits number of credits
     * @param roomNum room number of the course
     * @param instructor instructor of the course
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement newCourse = new CourseDBElement(id, crn, credits, roomNum, instructor);
        courses.add(newCourse);
    }

    /**
     * Retrieves a course from the database with the CRN
     * @param crn identifier used to get a course
     * @return the course in the database with matching CRN
     */
    @Override
    public CourseDBElement get(int crn) {
        try {
            CourseDBElement course = courses.get(crn);
            return course;
        } catch (IOException e){
            return null;
        }
    }

    /**
     * Reads from a file and adds the courses to the database
     * @param input file to be read
     * @throws FileNotFoundException if file is not found
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);

        while(scanner.hasNext()){
            String courseString = scanner.nextLine();
            Scanner stringScanner = new Scanner(courseString);
            stringScanner.useDelimiter(" ");

            String id = stringScanner.next();
            int crn = stringScanner.nextInt();
            int credits = stringScanner.nextInt();
            String roomNum = stringScanner.next();
            String instructor = stringScanner.nextLine().trim();

            add(id, crn, credits, roomNum, instructor);
        }
    }

    /**
     * Retrieves all courses from the database and returns them into an ArrayList sorted by CRN
     * @return ArrayList of all courses
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<CourseDBElement> allCourses = new ArrayList<>();

        for(LinkedList<CourseDBElement> list : courses.hashTable){
            if(list != null){
                allCourses.addAll(list);
            }
        }

        Collections.sort(allCourses);

        ArrayList<String> courseStrings = new ArrayList<>();

        for(CourseDBElement course : allCourses){
            courseStrings.add("\n" + course.toString());
        }

        return courseStrings;
    }
}
