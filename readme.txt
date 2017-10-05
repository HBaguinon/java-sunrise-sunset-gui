/*******************************************************************************
* NAME: HAROLD DANE C. BAGUINON                                                *
* DATE: 09/24/2015                                                             *
* DATE DUE: 09/24/2015 06:00:00 PM                                             *
* COURSE: CSC521 010                                                           *
* PROFESSOR: DR. SPIEGEL                                                       *
* PROJECT: #1                                                                  *
* FILENAME: readme.txt                                                         *
* PURPOSE: This program is the first assignment.                               *
*          It will read a file and provide a GUI for the user to display       *
*          times for Sunrise and Sunset from the file.                         *
*          The program utilizes a special "Day" class file, and various        *
*          Java methods such as ActionListeners and exception handlers.        *
* JAVADOC LOCATION: http://acad.kutztown.edu/~hbagu753/project1/               *
*******************************************************************************/

Design Decisions

Day.java
    I wanted to create an object that contained two parts, the sunset time and 
    the sunrise time. It should be a simple class with set and get functions. 
    Because the object would be stored in an array, with the ability to print
    the contents of it easily, a special method was also created to output the
    file with labels "SunRise:" and "SunSet:" when printed to the command line
    interface. That method wasn't needed for the GUI portion, however.
    
SunInfo.java
    Originally, I had wanted to separate the GUI portion with the file-reading
    portion of the program. But it created some difficulties: the array was
    only being loaded in its own object, but not from the main class (and not
    accessible in the main class). I managed to get past that issue by combining
    the GUI building methods in the same class as the file reading method. 
    Basically, the function to read the file "Kutztown.txt" is called, and an
    Array object is created and filled. Then the constructor is called to 
    build the GUI, which is designed to have access to the array that contains
    the data from the read file.
    
GUI
    I decided to create the GUI with a simple Border Layout. This allowed me to
    create a Title panel at the top and a Button panel at the bottom. The
    center panel was laid out on a grid with labels and comboboxes. I also
    decided to include a File menu, because it looked nice. The buttons and
    menu items also have keyboard shortcut functionality. The comboboxes have
    action listeners attached, such that the program checks for months having
    too many days. For example, an error is displayed when the user attemps to
    choose February and 31 days. Originally, I wanted the Day combobox to change
    depending on which month is selected, but I didn't want to have to rewrite
    all the code I had written in order to accomodate the design change. I 
    opted for the error instead. I should point out that when the error is
    displayed, the days are automatically set to day 1, so that the user doesn't
    attempt to display the sunset and sunrise time for a nonexistent date.
    
Print
    When the user decides on a month and day, the user but click the Print Data 
    button or menu item in order to display the sunrise and sunset times via 
    changing the text in the two labels. Originally I had 7:00 AM and PM as 
    default, but because January 1 was also defaulted, I didn't want the user 
    to think that January 1 and those values as its sunrise and sunset times. 
    Thus, I decided to make the labels default to blank. When the Print Data 
    button is pressed, the data within the Day class is accessed, but it must 
    be broken up (via substring), a colon must be inserted to separate the hours
    and minutes, an AM or PM added, and it must be all concatenated together.
    For the afternoon/evening hours, not only is "PM" added, but the hour 
    number must be parsed into an integer and subtracted by 12 in order to 
    convert it from military time. The morning hours were also parsed into an
    integer and reconverted into a string as well; not to subtract 12, but to
    remove the "0" in the beginning of it.
    