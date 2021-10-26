/**
 * The course element to be added to a database. The CRN is the unique identifier
 *
 * @author Randall Kim
 */
public class CourseDBElement implements Comparable<CourseDBElement> {
    private String courseID;
    private int crn;
    private int credits;
    private String roomNumber;
    private String instructor;

    /**
     * Constructs a course element
     * @param courseID Name of the course
     * @param crn Unique identifier of the course
     * @param credits Number of credits
     * @param roomNumber Room number of the course
     * @param instructor Instructor of the course
     */
    public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructor){
        this.courseID = courseID;
        this.crn = crn;
        this.credits = credits;
        this.roomNumber = roomNumber;
        this.instructor = instructor;
    }

    /**
     * Constructs a course with default fields
     */
    public CourseDBElement(){

    }

    /**
     * Sets the CRN of the course
     * @param crn the unique identifier
     */
    public void setCRN(int crn){
        this.crn = crn;
    }

    /**
     * Gets the CRN of the course
     * @return the CRN of the course
     */
    public int getCRN(){
        return crn;
    }

    /**
     * Compares two different courses based on CRN
     * @param c course to compare to
     * @return -1, 0, or 1, if the course CRN is less than, equal to, or greater than the other course
     */
    @Override
    public int compareTo(CourseDBElement c) {
        if(this.crn < c.getCRN()){
            return -1;
        } else if(this.crn == c.getCRN()){
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Hash code function that uses the CRN
     * @return hash code of the course
     */
    @Override
    public int hashCode(){
        return crn;
    }

    /**
     * Overrides the toString method to display each field
     * @return
     */
    @Override
    public String toString(){
        return "Course:" + courseID + " CRN:" + crn + " Credits:" + credits +
                " Instructor:" + instructor + " Room:" + roomNumber;
    }
}
