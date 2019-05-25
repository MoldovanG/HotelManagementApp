import employees.Camerista;
import employees.Employee;
import employees.Manager;
import employees.Paznic;
import employees.Receptioner;
import hotel.Hotel;
import services.HotelServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SwingGUI extends JPanel {
    Hotel hotelInstance = null;

    public SwingGUI(Hotel hotel) {
        super(new GridLayout(1, 1));

        hotelInstance = hotel;
        JTabbedPane tabbedPane = new JTabbedPane();

        //ADD PANEL 1 to the tabbed pane
        //PANEL 1 responsible for adding a new employee
        JComponent panel1 = makeEmployeePanel();
        tabbedPane.addTab("Add Employee", null, panel1,
                "Add employee");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        //ADD PANEL 2 to the tabbed pane
        //Panel 2 responsible for executing diferent services
        JComponent panel2 = makeStatisticsPanel();
        tabbedPane.addTab("Hotel Statistics", null, panel2,
                "Execute Services");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    //Create the design to print statitiscs about the hotel
    protected JComponent makeStatisticsPanel() {
        JPanel panel = new JPanel(false);
        JLabel outputLabel = new JLabel();
        JScrollPane scroller = new JScrollPane(outputLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(300,250));

        JButton listSalaries = new JButton("List Salaries and total Cost per Month");
        listSalaries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSalariesAndCostOnClick(outputLabel);
            }
        });

        JButton monthWithMostRoomsBooked = new JButton("Month with most rooms booked");
        monthWithMostRoomsBooked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profitableMonthOnClick(outputLabel);
            }
        });

        JButton revenueForToday = new JButton("Revenue for Today");
        revenueForToday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revenueForToday(outputLabel);
            }
        });

        JButton profitForToday = new JButton("Profit for Today");
        profitForToday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profitForToday(outputLabel);
            }
        });

        //outputLabel.setPreferredSize(new Dimension(200,200));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        //First Row, First Column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =0;
        gc.gridy =0;
        panel.add(listSalaries,gc);
        //First Row, Second Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =1;
        gc.gridy =0;
        panel.add(monthWithMostRoomsBooked,gc);
        //Second Row, Second Column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =0;
        gc.gridy =1;
        panel.add(revenueForToday,gc);

        //Second Row,Second Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =1;
        gc.gridy =1;
        panel.add(profitForToday,gc);

        //Last Row,First Column
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.weightx =1;
        gc.weighty =50;
        gc.gridx =0;
        gc.gridy =2;
        panel.add(scroller,gc);
        return panel;
    }

    //Create the design to add a new Employee
    protected JComponent makeEmployeePanel() {
        JPanel panel = new JPanel(false);

        JTextField employeeName = new JTextField() ;
        employeeName.setPreferredSize(new Dimension(100,20));

        JLabel textFieldHint = new JLabel("Employee Name : ");
        JLabel typeFieldHint = new JLabel("Employee Type : ");

        String[] myComboTags = {"camerista","manager","paznic","receptioner"};
        JComboBox<String> employeeType = new JComboBox<String>(myComboTags);

        JButton employeeButton = new JButton("Add Employee");
        //Add on click action listener to the button
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeOnClick(employeeName,employeeType); // get the info from the text input field and from the combo and instantiate and add a new employee
            }
        });

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //First Row ,First Column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =0;
        gc.gridy =0;
        panel.add(textFieldHint,gc);

        //First Row, Second Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =1;
        gc.gridy =0;
        panel.add(employeeName,gc);

        //First Row ,First Column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =0;
        gc.gridy =1;
        panel.add(typeFieldHint,gc);

        //Second Row, Second Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx =0.5;
        gc.weighty =0.5;
        gc.gridx =1;
        gc.gridy =1;
        panel.add(employeeType,gc);

        //Last Row,First Column
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.weightx =0.5;
        gc.weighty =50;
        gc.gridx =0;
        gc.gridy =2;
        panel.add(employeeButton,gc);

        return panel;

    }

    private void addEmployeeOnClick(JTextField name, JComboBox<String> type){
        String employeeName = name.getText();
        String employeeType = (String) type.getSelectedItem();
        Employee aux = null;
        switch (employeeType){
            case "camerista":
                aux = new Camerista(employeeName);
                break;
            case "manager":
                aux = new Manager(employeeName);
                break;
            case "paznic":
                aux = new Paznic(employeeName);
            case "receptioner":
                aux = new Receptioner(employeeName);
        }
        HotelServices.hireEmployee(aux,hotelInstance);
    }
    private void listSalariesAndCostOnClick (JLabel outputLabel){
       ArrayList<String> result = (ArrayList<String>) HotelServices.getSalariesAndTotalCostPerMonth(hotelInstance);
       StringBuilder mBuilder = new StringBuilder();
       mBuilder.append("<html>");
       for (String s : result)
           mBuilder.append(s +"<br>");
       mBuilder.append("</html>");
       outputLabel.setText(mBuilder.toString());
    }
    private void profitableMonthOnClick (JLabel outputLabel){
        String month = HotelServices.getMonthWithMostRoomsBooked(hotelInstance);
        outputLabel.setText(month);
    }
    private void revenueForToday (JLabel outputLabel){
        Calendar c= Calendar.getInstance();
        Date today = c.getTime() ;
        int revenue =  HotelServices.getRevenueForToday(today,hotelInstance);
        outputLabel.setText("Hotel revenue for today : "+ revenue);
    }
    private void profitForToday(JLabel outputLabel){
        Date today = Calendar.getInstance().getTime();
        int profit = HotelServices.getProfitForToday(today,hotelInstance);
        outputLabel.setText("Hotel profit for today : "+profit);
    }
    public static void createAndShowGUI(Hotel hotel) {
        //Create and set up the window.
        JFrame frame = new JFrame("HotelManagementApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600,600));
        //Add content to the window.
        frame.add(new SwingGUI(hotel), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
