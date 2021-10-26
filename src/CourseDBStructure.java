import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Data structure that stores courses
 *
 * @author Randall Kim
 */
public class CourseDBStructure implements CourseDBStructureInterface{
    public LinkedList<CourseDBElement>[] hashTable;
    int size;

    /**
     * Constructs a course database
     * @param size size of the hashTable that stores the courses
     */
    public CourseDBStructure(int size){
        hashTable = new LinkedList[size];
        this.size = size;
    }

    /**
     * Used for testing purposes only
     * Constructs a course database
     * @param testing
     * @param size
     */
    public CourseDBStructure(String testing, int size){
        this(size);
    }

    /**
     * Adds a course to the database
     * @param element the CDE to be added
     */
    @Override
    public void add(CourseDBElement element) {
        int hashCode = hashCode(element.getCRN());

        if(hashTable[hashCode] == null){
            LinkedList<CourseDBElement> list = new LinkedList<>();
            list.add(element);
            hashTable[hashCode] = list;
        } else {
            hashTable[hashCode].add(element);
        }
    }

    /**
     * Retrieves a course from the database with matching CRN
     * @param crn the CRN to check the database for
     * @return the course with matching CRN
     * @throws IOException if a course is not found in the database with the given CRN
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int hashCode = hashCode(crn);
        LinkedList<CourseDBElement> list = hashTable[hashCode];

        if(list != null){
            for(CourseDBElement c : list){
                if(c.getCRN() == crn){
                    return c;
                }
            }
        }

        throw new IOException("Course not found");
    }

    /**
     * Gets the size of the hashTable
     * @return size of the hashTable
     */
    @Override
    public int getTableSize() {
        return size;
    }

    /**
     * Computes the hash code given a CRN. The database uses bucket hashing.
     * @param crn Unique identifier of a course
     * @return hash code of a course with the given CRN
     */
    private int hashCode(int crn){
        return crn % size;
    }
}
