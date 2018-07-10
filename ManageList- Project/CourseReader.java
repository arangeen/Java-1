/**
 * CourseReader class reads in and process a course data file
 *
 * @version     1.0
 *
 */
import java.io.* ;
import java.util.* ;

public class CourseReader
{
  /**
   * allocate and populate course objects from the given data file
   */
  public static CourseCatalog read(String courseDataFileName) throws FileNotFoundException
  {
      File f = new File(courseDataFileName);
	  Scanner rdr = new Scanner(f) ;
      CourseCatalog catalog = new CourseCatalog() ;
	  // go through each input line, line by line for record by record
      while (rdr.hasNextLine())
	  {
		 String oneRecord = rdr.nextLine().trim();
		 if (!oneRecord.isEmpty())
		    catalog.add( getOneCourse(oneRecord) );
	  }
	  
	  return catalog ;
  }
  
  /**
   *  process the input data of one course
   */
  private static Course getOneCourse(String oneRecord)
  {
     Scanner rdr = new Scanner(oneRecord);
	 String courseId    = rdr.next() ;
	 int    sessionId   = rdr.nextInt() ;
	 int    numOfUnits  = rdr.nextInt() ;
	 String courseTitle = rdr.nextLine().trim() ;
     return new Course(courseId, sessionId, numOfUnits, courseTitle) ;
  }
}


