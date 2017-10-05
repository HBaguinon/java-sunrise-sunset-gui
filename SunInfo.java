/*******************************************************************************
* NAME: HAROLD DANE C. BAGUINON                                                *
* DATE: 09/24/2015                                                             *
* DATE DUE: 09/24/2015 06:00:00 PM                                             *
* COURSE: CSC521 010                                                           *
* PROFESSOR: DR. SPIEGEL                                                       *
* PROJECT: #1                                                                  *
* FILENAME: SunInfo.java                                                       *
* PURPOSE: This program is the first assignment.                               *
*          It will read a file and provide a GUI for the user to display       *
*          times for Sunrise and Sunset from the file.                         *
*          The program utilizes a special "Day" class file, and various        *
*          Java methods such as ActionListeners and exception handlers.        *
* JAVADOC LOCATION: http://acad.kutztown.edu/~hbagu753/project1/               *
*******************************************************************************/

import java.io.File;                // Java File
import java.io.FileNotFoundException; // Exception handling
import java.util.Scanner;       // To read the file
import java.util.Arrays;        // For printing arrays contents
import java.util.*;             // For Java utilities
import javax.swing.*;            // For Swing classes
import java.awt.*;               // For BorderLayout class and color class
import java.awt.event.*;         // For event listener interface

/** SunInfo is the class which contains the GUI for this project, as well as the
 *  file reader, the two-dimensional array object, and all of the major methods.
 *  The program is designed to read the contents of a text file which contain
 *  the sunset and sunrise times in Kutztown, Pennsylvania during 2008. The
 *  program displays two drop-down menus (combo boxes) which the user can choose
 *  the month and day of the times desired. Once the choices are maid, the Print
 *  Data button or menu item can be clicked to display the sunrise and sunset
 *  times, respectively. The exit button is used to close the program.
 * 
 *  @author Harold Baguinon
 *  @version 1.0 Build 1000 Sep 24, 2015.
 */
public class SunInfo extends JFrame {
    /** This section contains all of the info for the variables.*/
    /** Window width */
    private final int WINDOW_WIDTH = 700;
    /** Window height */
    private final int WINDOW_HEIGHT = 650;

    /** Center panel which contains the main section*/
    private JPanel CenterPanel;
    /** North panel which contains the title */
    private JPanel NorthPanel;    
    /** South panel, for the buttons */
    private JPanel SouthPanel; 
   
    /** Text for the title */
    private JLabel TitleLabel;         
    /** Text for "Month: " */
    private JLabel MonthLabel;         
    /** Text for "Day: " */
    private JLabel DayLabel;            
    /** Text for "Sunrise: " */
    private JLabel SunriseWordLabel;       
    /** Text for "Sunset: " */
    private JLabel SunsetWordLabel; 
    /** Text for sunrise time */
    private JLabel SunriseValueLabel;        
    /** Text for sunset time */
    private JLabel SunsetValueLabel;        

    /** Java button that is used to print the data */
    private JButton PrintData;       
    /** Button to exit the program */
    private JButton ExitButton;     
    
    /** ComboBox for choosing months */
    private JComboBox<String> MonthBox;
    /** ComboBox for choosing days */
    private JComboBox<String> DayBox;
    
    /** The menu bar */
    private JMenuBar MenuBar;    
    /** The File menu */
    private JMenu FileMenu; 
    /** The Print Data menu item */
    private JMenuItem PrintDataItem; 
    /** The Exit menu item */
    private JMenuItem ExitItem; 

    /** The array of strings for the list of months, used in the combobox. */
    private String[] monthList = new String[] {"January", "February", "March",
            "April", "May", "June", "July", "August", "September", "October",
            "November", "December"}; // For Month array
    
    /** The array of strings for the list of 31 dates, used in the combobox. */
    private String[] dayList31 = new String[] {"1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31"}; // For 31 day months
    /** The array of strings for the list of 30 dates, used in the combobox. */
    private String[] dayList30 = new String[] {"1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30"}; // For 30 day months
    /** The array of strings for the list of 29 dates, used in the combobox. */
    private String[] dayList29 = new String[] {"1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
                    // For February
    
    /** This is the special "Day" object, where the time data is stored */
    private static Day[][] day = new Day[32][13];  
    /** Day counter, used in array */
    public int d = 1; 
    /** Month counter, used in array */
    public int m = 1; 
    /** Used for error checking, comparing months' days */
    private int monthDays; 
    /** A boolean variable used only for error checking */
    private boolean DayError;  
    
    /** Used for military time conversion */
    private String SunRiseTime;
    /** Used for military time conversion */
    private String SunSetTime; 
    

    /**
     *  This is the constructor. In it are various methods which are used to 
     *  construct the GUI, such as arranging panels, setting labels to panels,
     *  choosing a layout, building the menu bar, buttons, comboboxes, etc. 
     */
    public SunInfo()
    {
        // Title.
        setTitle("Sunrise and Sunset");
            
        // Close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        // Add BorderLayout manager to content pane.
        setLayout(new BorderLayout());
      
        // Build menu bar
        BuildMenuBar();
      
        // BUILD PANELS
        BuildCenterPanel();
        BuildNorthPanel();
        BuildSouthPanel();
      
        // Add panels
        add(CenterPanel, BorderLayout.CENTER);
        add(NorthPanel, BorderLayout.NORTH);
        add(SouthPanel, BorderLayout.SOUTH);
      
        // Set size of the window.
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        
        // Display the window, but do not pack.
        // pack();               // DON'T PACK!
        setVisible(true);
    } 
    
    /**
     * This method builds the menu bar, which contains the File Menu.
     */
    private void BuildMenuBar()
     {
        // Create the menu bar.
        MenuBar = new JMenuBar();
      
        // Create File and Game menus.
        BuildFileMenu();
      
        // Add File and Game menus to the Menu Bar.
        MenuBar.add(FileMenu);
      
        // Set the Menu Bar.
        setJMenuBar(MenuBar);
     }
   
   
    /**
     *  This method builds the File Menu, which contains Print Data and Exit.
     */
    private void BuildFileMenu()
    {
        // Create the Print Data menu item.
        PrintDataItem = new JMenuItem("Print Data");
        PrintDataItem.setMnemonic(KeyEvent.VK_P);
        PrintDataItem.addActionListener(new PrintDataButtonListener());
      
        // Create the Exit menu item.
        ExitItem = new JMenuItem("Exit");
        ExitItem.setMnemonic(KeyEvent.VK_X);
        ExitItem.addActionListener(new ExitButtonListener());
      
        // Create JMenu object for the File menu.
        FileMenu = new JMenu("File");
        FileMenu.setMnemonic(KeyEvent.VK_F);
      
        // Add the menu items to the File menu.
        FileMenu.add(PrintDataItem);
        FileMenu.add(ExitItem);
    }
   
   
    /**
     *  This method builds the north panel, which contains the title.
     */
    public void BuildNorthPanel()
    {
        // Build title panel. Make it LIGHT_GRAY.
        NorthPanel = new JPanel();
        NorthPanel.setBackground(Color.LIGHT_GRAY);
  
        // Display the title.
        TitleLabel = new JLabel("Sunrise and Sunset", SwingConstants.CENTER);
        TitleLabel.setFont(new Font("Serif", Font.BOLD, 64));
        
        // Add the label to the panel.
        NorthPanel.add(TitleLabel);
    }
     
     
    /**
     * This is the method that builds the central panel, which contains a 
     * grid layout of the labels and combo boxes.
     */
    public void BuildCenterPanel()
    {
        // Build Card panel. Make it LIGHT_GRAY.
        CenterPanel = new JPanel();
        //CenterPanel.setBackground(Color.LIGHT_GRAY);
        
        // Set layout and border for the panel.
        CenterPanel.setLayout(new GridLayout(4, 2));
        CenterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
  
        // Month: label
        MonthLabel = new JLabel("Month: ", SwingConstants.CENTER);
        MonthLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        MonthLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(MonthLabel);
        
        // Month ComboBox
        MonthBox = new JComboBox<>(monthList);
        MonthBox.setFont(new Font("SansSerif", Font.BOLD, 36));
        MonthBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(MonthBox);
        MonthBox.addActionListener(new MonthBoxActionListener());
        
        // Day: label
        DayLabel = new JLabel("Day: ", SwingConstants.CENTER);
        DayLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        DayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(DayLabel);
      
        // Day ComboBox
        DayBox = new JComboBox<>(dayList31);
        DayBox.setFont(new Font("SansSerif", Font.BOLD, 36));
        DayBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(DayBox);
        DayBox.addActionListener(new DayBoxActionListener());
        
        // Sunrise: label
        SunriseWordLabel = new JLabel("Sunrise: ", SwingConstants.CENTER);
        SunriseWordLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        SunriseWordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(SunriseWordLabel);
        
        // Sunrise time label
        SunriseValueLabel = new JLabel("", SwingConstants.CENTER);
        SunriseValueLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        SunriseValueLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(SunriseValueLabel);
        
        // Sunset: label
        SunsetWordLabel = new JLabel("Sunset: ", SwingConstants.CENTER);
        SunsetWordLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        SunsetWordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(SunsetWordLabel);
        
        // Sunset time label
        SunsetValueLabel = new JLabel("", SwingConstants.CENTER);
        SunsetValueLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        SunsetValueLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CenterPanel.add(SunsetValueLabel);
    }
   
    /**
     *   This is the method that builds the south panel, which contain the buttons.
     */
    public void BuildSouthPanel()
    {
        // Build title panel. Make it LIGHT_GRAY.
        SouthPanel = new JPanel();
        SouthPanel.setBackground(Color.LIGHT_GRAY);
      
        // Set layout for the panel.
        SouthPanel.setLayout(new GridLayout(1, 2));
      
        // Create buttons and add mnemonics and tool tips.
        PrintData = new JButton("Print Data");
        PrintData.setMnemonic(KeyEvent.VK_P);
        PrintData.setToolTipText("Click here to print data.");
        ExitButton = new JButton("Exit");
        ExitButton.setMnemonic(KeyEvent.VK_X);
        ExitButton.setToolTipText("Click here to exit the game.");
      
        // Add an action listener to the buttons.
        PrintData.addActionListener(new PrintDataButtonListener());
        ExitButton.addActionListener(new ExitButtonListener());
      
        // Add buttons to panel.
        SouthPanel.add(PrintData);
        SouthPanel.add(ExitButton);
    }

    /** ReadSunInfo() is the method that reads a file's contents into an array
     *  object which is later accessed. The data is read by first skipping the 
     *  header lines, and then reading each row, skipping the first token and
     *  putting each subsequent pair into a Day object, where the Day object
     *  contains a SunRise time and a SunSet time, both strings.
     *
     *  @throws FileNotFoundException   Necessary if the file does not exist.
     */
    public static void ReadSunInfo() throws FileNotFoundException {

        // Creates File instance to reference text file in Java
        File inputFile = new File("Kutztown.txt");
     
        // Creates Scanner instnace to read File in Java
        Scanner scnr = new Scanner(inputFile);
     
        // Reads each line of file using Scanner class
        for (int i=1; i<10; i++) {
            scnr.nextLine();
        }
        
        for (int d=1; d<32; d++) {
            scnr.next();
            for (int m=1; m<13; m++) {
                if ((d>29&&m==2) || (d>30&&m==4) || (d>30&&m==6) ||
                    (d>30&&m==9) || (d>30&&m==11)) {
                    day[d][m] = new Day(null, null);
                }
                else { 
                    day[d][m] = new Day(scnr.next(), scnr.next());
                } 
            }
        }
        // System.out.println(Arrays.deepToString(day)); // For testing
    }

    
    /**
     *   The MonthBoxActionListener is an action listener class for the Month
     *   Box. It implements Java's ActionListener.
     */
    private class MonthBoxActionListener implements ActionListener
    {
        /**
         *  The actionPerformed method executes when the user selects a month.
         *  @param event  The event object.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            // Set the selectedMonth    
            String selectedMonth = (String) MonthBox.getSelectedItem();
            // Assign m a value depending on the month selected
            if (selectedMonth.equals("January")) {
                m = 1;
            } else if (selectedMonth.equals("February")) {
                m = 2;
            } else if (selectedMonth.equals("March")) {
                m = 3;
            } else if (selectedMonth.equals("April")) {
                m = 4;
            } else if (selectedMonth.equals("May")) {
                m = 5;
            } else if (selectedMonth.equals("June")) {
                m = 6;
            } else if (selectedMonth.equals("July")) {
                m = 7;
            } else if (selectedMonth.equals("August")) {
                m = 8;
            } else if (selectedMonth.equals("September")) {
                m = 9;
            } else if (selectedMonth.equals("October")) {
                m = 10;
            } else if (selectedMonth.equals("November")) {
                m = 11;
            } else if (selectedMonth.equals("December")) {
                m = 12;
            }
            // choose the number of days depending on the month
            if (selectedMonth.equals("April") ||
                selectedMonth.equals("June") ||
                selectedMonth.equals("September") ||
                selectedMonth.equals("November")) {
                monthDays = 30;
            }
            else if (selectedMonth.equals("February")) {
                monthDays = 29;
            }
            else {
                monthDays = 31;
            }
            // if the days don't match, show the error
            if (monthDays == 30 && d == 31 ) {
                JOptionPane.showMessageDialog(null, "Error! That month doesn't have that many days!");
                DayBox.setSelectedIndex(0);
                d = 1;
                DayError = false;
            }
            else if (monthDays == 29 && (d == 31 || d == 30)) {
                JOptionPane.showMessageDialog(null, "Error! That month doesn't have that many days!");
                DayBox.setSelectedIndex(0);
                d = 1;
                DayError = false;
            }
            else
                DayError = false;
        }
    }
    
    /**
     *   The DayBoxActionListener is an action listener class for the Day
     *   Box. It implements Java's ActionListener.
     */
    private class DayBoxActionListener implements ActionListener
    {
        /**
         *  The actionPerformed method executes when the user selects a day.
         *  @param event    The event object.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
                
            String selectedDay = (String) DayBox.getSelectedItem();
            // if the days don't match, show the error
            if (monthDays == 30 && selectedDay.equals("31")) {
                JOptionPane.showMessageDialog(null, "Error! That month doesn't have that many days!");
                DayBox.setSelectedIndex(0);
                d = 1;
                DayError = false;
            }
            else if (monthDays == 29 && (selectedDay.equals("31") || selectedDay.equals("30"))) {
                JOptionPane.showMessageDialog(null, "Error! That month doesn't have that many days!");
                DayBox.setSelectedIndex(0);
                d = 1;
                DayError = false;
            }
            else
                d = Integer.parseInt(selectedDay);
                DayError = false;
        }
    }
    
    
    /**
     *  The PrintDataButtonListener is an action listener class for the Print
     *  Data button. It implements Java's ActionListener. It also calls the 
     *  MilitaryTime() method, which is described below. It implements Java's
     *  ActionListener.
     */
    private class PrintDataButtonListener implements ActionListener
    {
        /**
         *  The actionPerformed method executes when the user clicks on the
         *  Print Data button.
         *  @param action   The event object.
         */
        public void actionPerformed(ActionEvent action) 
        {
            MilitaryTime();
            // Show an error if there is still an error. Otherwise, display.
            if (DayError == true) {
                JOptionPane.showMessageDialog(null, "Please choose another day!");
                SunriseValueLabel.setText("");
                SunsetValueLabel.setText("");
            }
            else {
                SunriseValueLabel.setText(SunRiseTime);
                SunsetValueLabel.setText(SunSetTime);
            }
            //SunriseValueLabel.setText(String.valueOf(day[d][m].getSunRise()));
            //SunsetValueLabel.setText(String.valueOf(day[d][m].getSunSet()));
            // Print to screen.
            System.out.println(day[d][m].getSunRise());
            System.out.println(day[d][m].getSunSet());
      }
    }
   
    /** 
     *  MilitaryTime() is the method that converts the data string, which looks 
     *  like a four-digit integer, into standard time. This is done using
     *  substrings and conversion between Integer and String values. A colon is
     *  added to separate the hours and minutes, and AM or PM is added.
     */
    public void MilitaryTime() {
        SunRiseTime = String.valueOf((Integer.parseInt(String.valueOf(day[d][m].getSunRise()).substring(0,2)))) + ":" + 
            String.valueOf(day[d][m].getSunRise()).substring(2,4) + " AM";
        SunSetTime = String.valueOf((Integer.parseInt(String.valueOf(day[d][m].getSunSet()).substring(0,2)) - 12)) + ":" + 
            String.valueOf(day[d][m].getSunSet()).substring(2,4) + " PM";
    }
    
   /**
    *  The ExitButtonListener is an action listener class for Exit button.
    *  It implements Java's ActionListener.
    */
    private class ExitButtonListener implements ActionListener
    {
        /**
         *  The actionPerformed method executes when the user clicks on the
         *  Exit button.
         *  @param action   The event object.
         */
        public void actionPerformed(ActionEvent action)
        {
            // Close the program.
            System.exit(0);
        }
    }
   
    /**
     *  The main() method for this class simply runs the ReadSunInfo() method,
     *  which reads a file into an array as described above. it also instantiates
     *  the SunInfo class.
     *  
     *  @throws FileNotFoundException   Necessary if the file does not exist.
     */
    public static void main(String args[]) throws FileNotFoundException {
        ReadSunInfo();
        
        new SunInfo();
    }
}
