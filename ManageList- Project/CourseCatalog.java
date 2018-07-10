/**
 * CourseCatalog class manages a list of Course objects
 *
 * @version     1.0
 *
 */

import java.util.* ;
import java.io.* ;

public class CourseCatalog
{
  /** list of courses */
  private Vector<Course>     m_courses ;

  /**
   * allocate and initialize the list
   */
  public CourseCatalog()
  {
    m_courses = new Vector<Course>() ;
  }

  /**
   * the string representation of the list of all items
   */
  public String toString()
  {
    int count = m_courses.size() ;

    StringBuffer buff = new StringBuffer() ;

    for(int i=0; i < count; i++)
    {
      buff.append( m_courses.elementAt(i).toString() ) ;
      buff.append( "\n" ) ;
    }

    return ( buff.toString() ) ;
  }


  /**
   *  default printing out the list
   */
  public void print()
  {
    print( new PrintWriter(System.out, true) ) ;
  }


  /**
   * print out the list of courses to a given destination
   */
  public void print(PrintWriter wrt)
  {
    wrt.println( this.toString() ) ;
  }


  /**
   * return an indicator whether the list is empty or not
   */
  public boolean isEmpty()
  {
    return (m_courses.size() == 0) ;
  }


  /**
   * add a given Course object to the list
   */
  public boolean add(Course course)
  {
    boolean isSuccessful = false ;

    if (course != null)
    {
      m_courses.addElement(course) ; 
      isSuccessful = true ;
    }
    return ( isSuccessful ) ;
  }
  
  /**
   *   return all the courses in the list that contains the given String s
   */
  public Iterator<Course> search(String s)
  { 
    int    count    = m_courses.size() ;
    Vector<Course> foundCourseList = new Vector<Course>() ;
    Course course   = null ;

    // traverse each object in the list and create a list of matched objects
    for(int i=0; i < count; i++)
    {
      course = m_courses.elementAt(i) ;
      if (course.contains(s))
          foundCourseList.add(course) ;
    }
    return ( foundCourseList.iterator() ) ;
  }  

  /**
   *   return the courses with the given session id if found and null if not found
   */
  public Course search(int sessionId)
  { 
    int    count    = m_courses.size() ;
    Course foundCourse   = null ;

    // traverse each object in the list and create a list of matched objects
    for(int i=0; (i < count) && (foundCourse == null); i++)
    {
      Course course = m_courses.elementAt(i) ;
      if (course.getSessionId() == sessionId)
          foundCourse = course ;
    }
    return ( foundCourse ) ;
  }  
  
  /**
   *   return all the courses in the list that has the number of units greater than or equal to the given target
   */
  public Iterator<Course> unitsGreaterEqual(int numOfUnits)
  { 
    int    count    = m_courses.size() ;
    Vector<Course> foundCourseList = new Vector<Course>() ;
    Course course   = null ;

    // traverse each object in the list and create a list of matched objects
    for(int i=0; i < count; i++)
    {
      course = m_courses.elementAt(i) ;
      if (course.getNumberOfUnits() >= numOfUnits)
          foundCourseList.add(course) ;
    }
    return ( foundCourseList.iterator() ) ;
  }  
}


