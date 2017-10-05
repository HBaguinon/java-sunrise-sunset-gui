/*******************************************************************************
* NAME: HAROLD DANE C. BAGUINON                                                *
* DATE: 09/24/2015                                                             *
* DATE DUE: 09/24/2015 06:00:00 PM                                             *
* COURSE: CSC521 010                                                           *
* PROFESSOR: DR. SPIEGEL                                                       *
* PROJECT: #1                                                                  *
* FILENAME: Day.java                                                           *
* PURPOSE: This program is part of the first assignment.                       *
*          It is the special "Day" class file, which is used to store the      *
*          sunrise and sunset values as strings. It also contains methods which*
*          are used to modify or return the sunrise or sunset values.          *
* JAVADOC LOCATION: http://acad.kutztown.edu/~hbagu753/project1/               *
*******************************************************************************/

/** Day is the class which contains the sunSet and sunRise data members, which are
 *  private strings. It also contains set and get methods to modify or return
 *  the sunRise or sunSet values.
 *
 *  @author Harold Baguinon
 *  @version 1.0 Build 1000 Sep 24, 2015.
 */
public class Day {
    /** The sunRise string data member */
    private String sunRise;
    /** The sunSet string data member */
    private String sunSet;

    /**
     *  This is the constructor. In it are the set and get methods which are
     *  used to modify or return the sunSet and sunRise values.
     *  @param sunRise      This is the sunRise string data member.
     *  @param sunSet       This is the sunSet string data member.
     */
    public Day(String sunRise, String sunSet) {
        this.sunRise = sunRise;
        this.sunSet = sunSet;
    }

        /**
         *  This is the getSunRise() method, which is used to access the
         *  sunRise data member.
         *  @return sunRise     The sunRise data member, a string.
         */
        public String getSunRise() { return sunRise; }
        
        /**
         *  This is the getSunSet() method, which is used to access the
         *  sunSet data member.
         *  @return sunSet     The sunSet data member, a string.
         */
        public String getSunSet() { return sunSet; }
        
        /**
         *  This is the setSunRise() method, which is used to modify the
         *  sunRise data member.
         *  @param sunRise     A String to replace the current sunRise string.
         */
        public void setSunRise(String sunRise) { this.sunRise = sunRise; }
        
        /**
         *  This is the setSunSet() method, which is used to modify the
         *  sunSet data member.
         *  @param sunSet     A String to replace the current sunSet string.
         */
        public void setSunSet(String sunSet) { this.sunSet = sunSet; }

    /**
     *  This is the toString() method. It is necessary in order to return and
     *  print out values stored in the object. Conveniently, the contents can
     *  be printed out in a special format, such as SunRise: and SunSet:, as
     *  shown.
     *  @return ("SunRise: " + this.getSunRise() + 
               ", SunSet: " + this.getSunSet())     The special format for printouts.
     */
    @Override
    public String toString() {
        return ("SunRise: " + this.getSunRise() + 
               ", SunSet: " + this.getSunSet());
    }
}
