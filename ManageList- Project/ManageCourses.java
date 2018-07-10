/**
 * ManageCourses reads in and perform one command at a time.
 *
 * The command can be
 *   - end, help, catalog, addcourse, search, units>= or save
 *
 * Note: see the help() method for more info.
 *
 * @version     1.0
 * @since       Spring 2016
 *
 */

import java.io.* ;
import java.util.* ;

class ManageCourses
{  
  /** keyboard reader          */
  private Scanner  m_rdr ;
  
  /** catalog of courses */
  private CourseCatalog     m_catalog ;

  /**
   * initialize the program
   */
  private ManageCourses(String courseDataFileName) throws FileNotFoundException
  {
      // create the reader from keyboard
      m_rdr = new Scanner(System.in) ;

      // get the list of courses from the data file
      m_catalog = CourseReader.read(courseDataFileName) ;
  }

  /**
   *  display a given message
   */
  private static void msg(String s)
  {
      System.out.println(s) ;
  }

  /**
   *   read in a string from the user
   */
  private String gets(String prompt) throws IOException 
  {
    System.out.print(prompt) ;
    System.out.flush() ;
    return ( m_rdr.nextLine() ) ;
  }

  /**
   * read in the course id
   */
  private String getCourseId() throws IOException
  {
    return ( gets("   Please enter the course id: ") );
  }

  /**
   * read in the session id
   */
  private int getSessionId() throws IOException
  {
    int sessionId = -1 ;
    do
    {
      String sessionIdStr = gets("   Please enter the course id: ") ;
      sessionId = Integer.parseInt(sessionIdStr) ;
	  if (sessionId < 0)
        System.out.println("   Invalid session number. Please try it again. ") ;
	  else if (m_catalog.search(sessionId) != null)
	  {
		  System.out.println(" Invalid session number. Session already exists. Please try it again. ");
		  sessionId = -1;
	  }
    } while (sessionId < 0) ;
    return ( sessionId ) ;
  }

  /**
   * read in the number of units
   */
  private int getNumOfUnits() throws IOException
  {
    int numOfUnits = -1 ;
    do
    {
      String numOfUnitsStr = gets("   Please enter the number of units: ") ;
      numOfUnits = Integer.parseInt(numOfUnitsStr) ;
	  if (numOfUnits < 0)
        System.out.println("   Invalid number of units. Please enter a non-negative value. ") ;
    } while (numOfUnits < 0) ;
    return ( numOfUnits ) ;
  }  

  /**
   * read in the course's title
   */
  private String getCourseTitle() throws IOException
  {
    return ( gets("   Please enter the title: ") );
  }
  
  /**
   *  parse the input for course info and add the Course object to the catalog
   */
  private void addCourse() throws IOException
  {    
    String courseId    = getCourseId() ;
    int    sessionId   = getSessionId() ;
	int    numOfUnits  = getNumOfUnits() ;
	String courseTitle = getCourseTitle() ;

    m_catalog.add( new Course(courseId, sessionId, numOfUnits, courseTitle) ) ;
    msg("   Add a course successfully. ") ;
  }


  /**
   *  search for a word and display all the matched objects in the list
   */
  private void search(String arg)
  {  
    Iterator<Course> courseList = m_catalog.search(arg) ;

    if (courseList.hasNext() == false)
       System.out.println("   No match is found. ") ;

    else
    {
       while (courseList.hasNext())
       {
          System.out.println( courseList.next() ) ;
       }
    }
  }

  /**
   *  print out the complete list
   */
  private void catalog()
  {  
    if (m_catalog.isEmpty())
       System.out.println("   The list is empty. ") ;

    else
       m_catalog.print() ;
  }

  /**
   *  print out the list of courses that is >= the given number of units
   */
  private void unitsGreaterEqual(int numOfUnits) throws IOException
  {  
    Iterator<Course> courseList = m_catalog.unitsGreaterEqual(numOfUnits) ;

    if (courseList.hasNext() == false)
       System.out.println("   No match is found. ") ;

    else
    {
       while (courseList.hasNext())
       {
          System.out.println( courseList.next() ) ;
       }
    }  
  }


  /**
   *  save the current list of courses into a given data file
   */
  private void save(String fileName) throws IOException
  {    
    FileWriter  f   = new FileWriter( fileName ) ;
    PrintWriter wrt = new PrintWriter( f, true ) ;

    m_catalog.print(wrt) ;
    wrt.close() ;

    msg("   Saved successfully.") ;
  }


  /**
   *  process the given command appropriately
   */
  private void process(String cmd) throws IOException
  {    
    String          verb, arg ;
    StringTokenizer tokens = new StringTokenizer( cmd ) ; 
    int             count  = tokens.countTokens() ;

    if (count == 1)
    {
      verb = tokens.nextToken() ;
      if (verb.equalsIgnoreCase("help"))
        help() ;

      else if (verb.equalsIgnoreCase("catalog"))
        catalog() ;

      else if (verb.equalsIgnoreCase("addcourse"))
        addCourse() ;

      else
        error() ;

    }
    else if (count == 2)
    {
      verb = tokens.nextToken() ;
      arg  = tokens.nextToken() ;

      if (verb.equalsIgnoreCase("search"))
        search(arg) ;

     else if (verb.equalsIgnoreCase("units>="))
        unitsGreaterEqual(Integer.parseInt(arg)) ;
	
      else if (verb.equalsIgnoreCase("save"))
        save(arg) ;

      else
        error() ;
    }
    else
      error() ;
  }


  /**
   *  print out help message
   */
  private static void help()
  {
      PrintStream out = System.out ;

      out.println("   Available commands:            ") ;
      out.println("     - end                        ") ;
      out.println("            terminate the program ") ;
      out.println("     - help                       ") ;
      out.println("            show all available commands ") ;
      out.println("     - catalog                           ") ;
      out.println("            Show all available course in the catalog ") ;
      out.println("     - addcourse                                     ") ; 
      out.println("            Add a new course to the catalog (id, session number, units and title)    ") ;
      out.println("     - search word                                                  ") ;
      out.println("            Show all courses in the catalog that contains the given word in the id or the title ") ;
      out.println("     - units>=   number                                          ") ;
      out.println("            dShow all courses in the catalog that has the number of units equal or greater than the given number") ;
      out.println("     - save filename                                        ") ;
      out.println("            Save the current list to the given file           ") ;

  }

  /**
   *  print out an error message followed by help message.
   */
  private static void error()
  {
      System.out.println("   Invalid command.  ") ;
  }


  /**
   *  read in and process the given command until "end" is entered
   */
  public void run() throws IOException
  {
    System.out.println("Please enter a command.") ;
    System.out.println("Type in 'help' for more info and 'end' to exit the program. \n") ;

    String   cmd ;
    boolean  done = false ;

    do
    {
      // read one one command at a time (in one line) and process it
      cmd = m_rdr.nextLine() ;
      if (cmd != null)
      { 
        cmd  = cmd.trim() ;
        done = cmd.equalsIgnoreCase("end") ;
        if (!done)
          process( cmd ) ;
      }
    } while (!done) ;
  }

  
  /**
   *  print out introductory message
   */
  private static void intro()
  {
      System.out.println("\n     Welcome to the Course Catalog    \n") ;
  }

  /**
   *  print out exiting message
   */
  private static void bye()
  {
      System.out.println("\n    Thank you for using the program. Goodbye.    \n") ;
  }

  /**
   *  show syntax error
   */
  private static void syntaxError()
  {
      System.out.println("Invalid usage. Please give a data file name.") ;
      System.out.println("Syntax:   java  ManageCourses  catalog-data-file-name.") ;	  
  }


  /**
   * main program for executing various commands
   *
   * @param     args     array of command line arguments
   */
  public static void main(String[] args) throws IOException
  {
	  if (args.length != 1)
	     syntaxError() ;
	  else
	  {
         intro() ;
         ManageCourses pgm = new ManageCourses(args[0]) ;
         pgm.run() ;
         bye() ;
	  }
  }
}

