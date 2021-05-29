import exceptions.AgeException;
import exceptions.WikipediaNoArcticleException;
import weather.Sys;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;

public class GUI extends JPanel implements ActionListener {
    public GUI() {
        super(new GridLayout(1, 2));

        JTabbedPane tabbedPane = new JTabbedPane();

        panel1 = makeTextPanel("Enter the details of traveller");
        tabbedPane.addTab("Recommend City", panel1);
        panel1.setPreferredSize(new Dimension(700, 700));
        //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        panel2 = makeTextPanel("List of clients");

        tabbedPane.addTab("See all clients", panel2);
        //tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        panel3 = makeTextPanel("Giveaway a ticket");
        tabbedPane.addTab("Give free ticket", panel3);
        panel3.setPreferredSize(new Dimension(690, 770));

        //tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        //The following line enables to use scrolling tabs.
        //tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        detailsOfPanel1(panel1);
        detailsOfPane2(panel2);
        detailsOfPanel3(panel3);
        panel1.setBackground(Color.lightGray);
        tabbedPane.setBackground(Color.pink);
        panel2.setBackground(Color.gray);
        panel3.setBackground(Color.darkGray);


    }
    JTextField text, text2, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t12, t11, t13, t14, t15, t16, t17, t19, t20;
    JLabel l1,  l3, l8, l9;
    JPanel p1, p3, p4, p7, p8;
    JComponent panel1;
    static JComponent panel2;
    JComponent panel3;
    static MyTable testtable;
    JButton b, b1, b3, b5, b6, b7;
    JTextArea j1, j2, j3, j4;
    JPanel p2 = new JPanel();
    GroupLayout x=new GroupLayout(p2);
    JLabel l5 = new JLabel("                      ");
    JLabel l2 = new JLabel(" ");
    JLabel l6 = new JLabel(" ");
    JLabel l7=new JLabel("Enter a number(2-5):");
    JLabel l4 = new JLabel(" ");
    JTextField t18 = new JTextField();
    JButton b2= new JButton("See 2-5 recommended cities");
    JButton b4= new JButton("Commit");
    int index=0;
    ArrayList<Traveller> travs=new ArrayList<>();
    //static ArrayList<Traveller> new_travellers=new ArrayList<>();
    static ArrayList<City> new_cities=new ArrayList<>();
    public static ArrayList<City> getNew_cities() {
        return new_cities;
    }
    JPanel p5 = new JPanel();

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Welcome to e-vacation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem1 = new JMenuItem(" Save");
        JMenuItem menuItem3 = new JMenuItem(" Exit");
        menu.add(menuItem1);
        menu.add(menuItem3);
        frame.setJMenuBar(menuBar);

        //Add content to the window.
        frame.add(new GUI(), BorderLayout.CENTER);
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Traveller.write_travellers_in_file();
                for(int i=0;i<getNew_cities().size();i++){
                    try {
                        City.put_new_city_in_map(GUI.getNew_cities().get(i).getName(),GUI.getNew_cities().get(i).getCountry_abbrev());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Traveller.initialize_travellers_from_file();
                panel2.remove(testtable);
                MyTable updatedtable=new MyTable();
                panel2.add(updatedtable,BorderLayout.CENTER);
                JOptionPane.showMessageDialog(null, "Data saved and updated!");

            }

        });
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    protected JComponent makeTextPanel(String text) {
        JComponent panel = new JPanel(false);
        //JLabel filler_empty = new JLabel(" ");
        //.setHorizontalAlignment(JLabel.LEFT);
        //filler_empty.setVerticalAlignment(JLabel.TOP);
/*
        JLabel filler = new JLabel(text);
        filler.setFont(new Font("Serif", Font.ITALIC, 24));
        filler.setHorizontalAlignment(JLabel.CENTER);
        filler.setVerticalAlignment(JLabel.TOP);
        panel.setLayout(new BorderLayout());
        //panel.add(filler_empty);
        panel.add(filler);
 */
        TitledBorder txt = new TitledBorder(text);
        txt.setTitleFont(new Font("Serif", Font.ROMAN_BASELINE, 35));
        txt.setTitleColor(Color.DARK_GRAY);
        if (text == "Giveaway a ticket") {
            txt.setTitleColor(Color.LIGHT_GRAY);
        }
        txt.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(txt);
        return panel;
    }

    protected JComponent detailsOfPanel1(JComponent panel) {
        p1 = new JPanel();
        JLabel l;
        p1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(1, 1, 1, 1);
        c.anchor = GridBagConstraints.EAST;
        p1.add(l = new JLabel("Name:", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l1 = new JLabel("Age:", SwingConstants.RIGHT), c);
        l1.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Native City:", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Native country abbreviation(e.g.,'gr'):", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for SEA:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for MUSEUM:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for UNIVERSITY:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for STADIUM:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for CHURCH:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for CAFE:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for PARK:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for ART:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for FASHION:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Grade for BRIDGE:(0-10)", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("Cities she/he wants to visit:", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(l = new JLabel("For each city above put in order the country abbrev:", SwingConstants.RIGHT), c);
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        p1.add(b = new JButton("Clear"), c);
        b.setPreferredSize(new Dimension(90, 30));
        b.addActionListener(this);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.CENTER;
        p1.add(t1 = new JTextField(15), c);
        t1.setFont(new Font("Serif", Font.PLAIN, 26));


        c.gridx = 1;
        c.gridy = GridBagConstraints.RELATIVE;
        p1.add(t2 = new JTextField(15), c);
        t2.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t3 = new JTextField(15), c);
        t3.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t4 = new JTextField(5), c);
        t4.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t5 = new JTextField(15), c);
        t5.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t6 = new JTextField(15), c);
        t6.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t7 = new JTextField(15), c);
        t7.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t8 = new JTextField(15), c);
        t8.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t9 = new JTextField(15), c);
        t9.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t10 = new JTextField(15), c);
        t10.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t11 = new JTextField(15), c);
        t11.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t12 = new JTextField(15), c);
        t12.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t13 = new JTextField(15), c);
        t13.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t14 = new JTextField(15), c);
        t14.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t15 = new JTextField(15), c);
        t15.setFont(new Font("Serif", Font.PLAIN, 24));
        p1.add(t16 = new JTextField(15), c);
        t16.setFont(new Font("Serif", Font.PLAIN, 24));

        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        p1.add(b1 = new JButton("Recommend"), c);
        b1.addActionListener(this);
        b1.setPreferredSize(new Dimension(155, 30));
        panel.add(p1);


        //p2 = new JPanel();

        //x=new GroupLayout(p2);
        p2.setPreferredSize(new Dimension(600, 650));
        p2.setVisible(false);
/*
        l5 = new JLabel("                      ");
        l2 = new JLabel(" ");
        l6 = new JLabel(" ");
        l7=new JLabel("Enter a number(2-5):");
        l4 = new JLabel(" ");
        t18 = new JTextField(5);

 */
        t18.setFont(new Font("Serif", Font.PLAIN, 25));
        l5.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        l2.setFont(new Font("Serif", Font.HANGING_BASELINE, 210));
        l6.setFont(new Font("Serif", Font.HANGING_BASELINE, 25));
        l4.setFont(new Font("Serif", Font.HANGING_BASELINE, 25));
        l7.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));

       // b2= new JButton("See 2-5 recommended cities");
        b2.addActionListener(this);
        b2.setPreferredSize(new Dimension(100, 25));
        //b4= new JButton("Commit");
        b4.addActionListener(this);
        b4.setPreferredSize(new Dimension(100, 25));

        x.setAutoCreateGaps(true);
        x.setAutoCreateContainerGaps(true);
        x.setHorizontalGroup(x.createSequentialGroup()

                .addComponent(l5)
/*
                .addGroup(x.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addComponent(l5)
                        .addComponent(l7)
                )



 */
                        .addGroup(x.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(l2)
                        .addComponent(l4)
                        .addComponent(b2)
                        .addComponent(l6)
                        //.addComponent(t18)
                        )
                //.addComponent(b4)


        );
        x.linkSize(SwingConstants.VERTICAL, b2);

        x.setVerticalGroup(x.createSequentialGroup()
                .addGroup(x.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l5)
                        .addComponent(l2)
                )
                .addComponent(l4)
                .addComponent(b2)
                .addComponent(l6)
/*
                .addGroup(x.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l7)
                        .addComponent(t18)
                        .addComponent(b4)
                )



 */

        );
        p2.setLayout(x);
        panel.add(p2);

        return panel;

    }
    protected JComponent detailsOfPane2(JComponent panel) {
        //panel2.setLayout(new GridLayout(1,1));
        testtable=new MyTable();
        panel.add(testtable,BorderLayout.CENTER);
        return panel;
    }
    /*
    protected JComponent detailsOfPanel2(JComponent panel) {
        ArrayList<Traveller> unique_trav=Traveller.no_duplicate_arraylist_travellers();
        JLabel[] labels = new JLabel[unique_trav.size()];
        JLabel[] age = new JLabel[unique_trav.size()];
        JLabel[] visit = new JLabel[unique_trav.size()];
        JLabel[] grade0 = new JLabel[unique_trav.size()];
        JLabel[] grade1 = new JLabel[unique_trav.size()];
        JLabel[] grade2 = new JLabel[unique_trav.size()];
        JLabel[] grade3 = new JLabel[unique_trav.size()];
        JLabel[] grade4 = new JLabel[unique_trav.size()];
        JLabel[] grade5 = new JLabel[unique_trav.size()];
        JLabel[] grade6 = new JLabel[unique_trav.size()];
        JLabel[] grade7 = new JLabel[unique_trav.size()];
        JLabel[] grade8 = new JLabel[unique_trav.size()];
        JLabel[] grade9 = new JLabel[unique_trav.size()];
        JLabel[] labels_countr = new JLabel[unique_trav.size()];
       // JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(parallel)

        );
        GroupLayout.SequentialGroup sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
        for (int i = 0; i < unique_trav.size(); i++) {
            labels[i] = new JLabel(String.valueOf(i + 1) + ". ", JLabel.RIGHT);
            age[i] = new JLabel(String.valueOf(unique_trav.get(i).getAge()));

            labels[i].setFont(new Font("Serif", Font.HANGING_BASELINE, 22));
            labels_countr[i] = new JLabel(unique_trav.get(i).getName());
            labels_countr[i].setFont(new Font("Serif", Font.ITALIC, 25));
            age[i].setFont(new Font("Serif", Font.HANGING_BASELINE, 25));
            //[i].setLabelFor(labels_countr[i]);
            parallel.addGroup(layout.createSequentialGroup()
                    .addComponent(labels[i])
                    .addComponent(labels_countr[i])
                    .addComponent(age[i])

            );

            sequential.addGroup(layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labels[i])
                    .addComponent(labels_countr[i])
                    .addComponent(age[i])

            );

            layout.linkSize(SwingConstants.HORIZONTAL, labels[i], labels[0]);
        }




        return panel;
    }

     */
    protected JComponent detailsOfPanel3(JComponent panel) {
        JLabel labelcity = new JLabel("Enter City name: ");
        JLabel labelabb = new JLabel("Enter City abbrev(e.g,'gr'): ");
        text = new JTextField(20);
        text2 = new JTextField(20);
        text.setFont(new Font("SansSerif", Font.HANGING_BASELINE + Font.PLAIN, 20));
        text2.setFont(new Font("SansSerif", Font.HANGING_BASELINE + Font.PLAIN, 20));
        JButton winner = new JButton("Find winner");
        winner.addActionListener(this);

        // create a new panel with GridBagLayout manager
        p3 = new JPanel(new GridBagLayout());
        p3.setPreferredSize(new Dimension(750,300));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        p3.add(labelcity, constraints);

        constraints.gridx = 1;
        p3.add(text, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        p3.add(labelabb, constraints);

        constraints.gridx = 1;
        p3.add(text2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        p3.add(winner, constraints);

        // set border for the panel
        p3.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Select city"));

        // add the panel to tapped pane
        panel.add(p3);
        p4 = new JPanel();
        l3 = new JLabel("", JLabel.CENTER);
        JLabel l = new JLabel("         ");
        //JLabel l1 = new JLabel("k");
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 40));
        l3.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        l8 = new JLabel("", JLabel.CENTER);
        l8.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        GroupLayout xa=new GroupLayout(p4);
        xa.setAutoCreateGaps(true);
        xa.setAutoCreateContainerGaps(true);
        xa.setHorizontalGroup(xa.createSequentialGroup()
                .addComponent(l)
                .addGroup(xa.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(l3)
                        .addComponent(l8)
                ));
        xa.setVerticalGroup(xa.createSequentialGroup()
                .addGroup(xa.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(l)
                        .addComponent(l3)
                )

                        .addComponent(l8)



        );
        p4.setLayout(xa);
        p4.setPreferredSize(new Dimension(750, 140));
        p4.setVisible(false);
        panel3.add(p4);
        /*
        JLabel l;
        JTextField t;
        panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 30, 200));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(l=new JLabel("Enter a city name:", JLabel.CENTER));
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        panel.add(t=new JTextField(10));
        //t.setHorizontalAlignment();
        t.setFont(new Font("Serif",Font.PLAIN, 16));
        panel.add(l=new JLabel("Enter a country abbrev for this city(e.g,"+"it):", JLabel.CENTER));
        l.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        panel.add(t=new JTextField(10));
        t.setFont(new Font("Serif",Font.PLAIN, 16));
        JButton btn=new JButton("Submit");
        panel.add(new JButton("Submit"),SwingConstants.RIGHT);
        btn.setPreferredSize(new Dimension(80, 10));

         */
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (command.equals("Find winner")) {
            String city = text.getText();
            String country = text2.getText();
            City c = new City(city, country);
            try {
                City.put_new_city_in_map(city,country);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Traveller the_winner = c.free_ticket(Traveller.getTravellers());
            int age = the_winner.getAge();
            String name = the_winner.getName();
            double sim = the_winner.calculate_similarity(c);
            String result = "The winner with max similarity for city:" + city + "_" + country + " is:";
            String result1 = name + " with age " + age + " and sim:" + sim;
           l3.setText(result);
           l8.setText(result1);
            p4.setVisible(true);
        }
        if (command.equals("Clear")) {
            JTextField temp;
            for (Component c : p1.getComponents()) {
                if (c.getClass().toString().contains("javax.swing.JTextField")) {
                    temp = (JTextField) c;
                    temp.setText("");

                }
            }

        }
        if (command.equals("Recommend")) {
            String name = t1.getText();
            String age_string = t2.getText();
            String n_city = t3.getText();
            String n_country = t4.getText();
            String g1 = t5.getText();
            String g2 = t6.getText();
            String g3 = t7.getText();
            String g4 = t8.getText();
            String g5 = t9.getText();
            String g6 = t10.getText();
            String g7 = t11.getText();
            String g8 = t12.getText();
            String g9 = t13.getText();
            String g10 = t14.getText();
            String cities = t15.getText();
            String countries = t16.getText();
            int ints[] = new int[10];
            String strings[] = {g1, g2, g3, g4, g5, g6, g7, g8, g9, g10};
            for (int i = 0; i < 10; i++) {
                ints[i] = Integer.parseInt(strings[i]);
            }
            int age = Integer.parseInt(age_string);

            if (age < 16 || age > 115) {
                try {
                    throw new AgeException();
                } catch (AgeException ageException) {
                    t2.setText("");
                    l1.setForeground(Color.RED);
                }
            } else {
                l1.setForeground(Color.black);
                age_string = t2.getText();
                age = Integer.parseInt(age_string);

                Traveller trav = make_obj_Traveller(age, name, ints);
                Traveller.getTravellers().add(trav);

                for (int i = 0; i < Traveller.getTravellers().size(); i++) {
                    System.out.println(Traveller.getTravellers().get(i).getName());
                }

                try {
                    trav.setLocal_geodesic(n_city, n_country);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                ArrayList<String> mycities = tokenizer(cities);
                ArrayList<String> abbrev = tokenizer(countries);
                for (int i = 0; i < mycities.size(); i++) {
                      City acity=new City(mycities.get(i),abbrev.get(i));
                      new_cities.add(acity);
                }
                for (int i = 0; i < new_cities.size(); i++) {
                    System.out.println(new_cities.get(i).getName());
                }
                trav.setVisit();
                travs.add(index,trav);
                index++;
                p2.setVisible(true);
                String recom_city = "Recommended city:" + trav.getVisit();
                l4.setText(recom_city);
                //x1.setText();

            }
        }

        if (command.equals("See 2-5 recommended cities")) {
            x.setAutoCreateGaps(true);
            x.setAutoCreateContainerGaps(true);
            x.setHorizontalGroup(x.createSequentialGroup()

                    .addGroup(x.createParallelGroup(GroupLayout.Alignment.LEADING)

                            .addComponent(l5)
                            .addComponent(l7)
                    )
                    .addGroup(x.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(l2)
                            .addComponent(l4)
                            .addComponent(b2)
                            .addComponent(l6)
                            .addComponent(t18)

                    )
                    .addComponent(b4)


            );
            x.linkSize(SwingConstants.VERTICAL, b2,b4);

            x.setVerticalGroup(x.createSequentialGroup()
                    .addGroup(x.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(l5)
                            .addComponent(l2)
                    )
                    .addComponent(l4)
                    .addComponent(b2)
                    .addComponent(l6)
                    .addGroup(x.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(l7)
                            .addComponent(t18)
                            .addComponent(b4)
                    )

            );
            p2.setLayout(x);




        }
        if (command.equals("Commit")) {
            String number=t18.getText();
            int num = Integer.parseInt(number);

            if (num < 2 || num > 5) {

                t18.setText("");
                l7.setForeground(Color.RED);

            } else {
                l7.setForeground(Color.black);
                number = t18.getText();
                num = Integer.parseInt(number);}

            int var=index-1;
            Collection<City> city_values=City.getAll_cities().values();
            ArrayList<City> lotcities = new ArrayList<>(city_values);
            for(int i=0;i<lotcities.size();i++){
                lotcities.get(i).setSimilarity(travs.get(var));
            }
            ArrayList<City> my_cities=travs.get(var).compare_cities(num);

            for(int i=0;i<my_cities.size();i++){
                System.out.println(my_cities.get(i).getName());
            }

            p5=new PanelTable().createCities(my_cities);
            JScrollPane jsp = new JScrollPane(p5) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(320, 240);
                }
            };
            JOptionPane.showMessageDialog(null, jsp, "Data", JOptionPane.PLAIN_MESSAGE);
            //panel.add(p5);
            //p5.setVisible(false);
            p2.setVisible(false);
            t18.setText("");
        }



    }
    public Traveller make_obj_Traveller(int ageoftraveller,String name,int[] grades)  {

        if(ageoftraveller>=16 && ageoftraveller<=25){
            YoungTraveller young = new YoungTraveller(name, ageoftraveller, grades);
            return young;
        }
        if(ageoftraveller>25 && ageoftraveller<=60){
            Traveller middle=new MiddleTraveller(name, ageoftraveller, grades);
            return middle;
        }
        if(ageoftraveller>60 && ageoftraveller<=115){
            Traveller elder=new ElderTraveller(name, ageoftraveller, grades);
            return elder;
        }
        return null;
    }

    private ArrayList<String> tokenizer(String x) {
        ArrayList<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(x, ", ");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }

        return tokens;
        /*
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
        }

         */

    }

    public static void main(String[] args) throws IOException, NullPointerException, InterruptedException, SQLException {
        Traveller.initialize_travellers_from_file();
        DataBase.makeJDBCConnection();
        DataBase.ReadData();
        createAndShowGUI();

    }

}



