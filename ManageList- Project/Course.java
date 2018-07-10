/**
 * Course class contains course information such as
 *   course id, session id, number of units and the title of the course
 *
 */
import java.util.StringTokenizer ;

public class Course
{
  private String   m_courseId ;
  private int      m_sessionId ;
  private int      m_numOfUnits ;
  private String   m_courseTitle ;
  
  /**
   * initialize the Course bject given the information about the course
   */
  public Course(String courseId, int sessionId, int numOfUnits, String courseTitle)
  {
     m_courseId = courseId ;
	 m_sessionId = sessionId ;
	 m_numOfUnits = numOfUnits ;
	 m_courseTitle = courseTitle;
  }

  /**
   *  return the current number of units
   */
  public int getNumberOfUnits()
  {
    return ( m_numOfUnits ) ;
  }

  /**
   *  return the unique session id
   */
  public int getSessionId()
  {
    return ( m_sessionId ) ;
  }

  /**
   *  represent the Course object as a string
   */
  public String toString()
  {
    return (m_courseId + "   " + m_sessionId + "   " + m_numOfUnits + "   " + m_courseTitle) ;
  }

    /**
   *   return an indication whether this course contains a given word
   */
  public boolean contains(String s)
  {
    boolean found = false ;

    StringTokenizer  tokens = new StringTokenizer( m_courseId + " " + m_courseTitle ) ;

    while ((tokens.hasMoreTokens() == true) && (found == false))
    {
      found = tokens.nextToken().equalsIgnoreCase(s) ;
    }
    return (found) ;
  }
}

