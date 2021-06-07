import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanelTable {
    static int NUM = 20;
    JLabel[] labels = new JLabel[NUM];
    JLabel[] labels_countr = new JLabel[NUM];



    public JPanel createCities(ArrayList<City> cities) {
        JLabel[] labels = new JLabel[cities.size()];
        JLabel[] labels_countr = new JLabel[cities.size()];
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.ParallelGroup parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(parallel));
        GroupLayout.SequentialGroup sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
        for (int i = 0; i < cities.size(); i++) {
            labels[i] = new JLabel(String.valueOf(i + 1)+". ", JLabel.RIGHT);
            labels[i].setFont(new Font("Serif", Font.HANGING_BASELINE, 22));
            labels_countr[i] = new JLabel(cities.get(i).getName());
            labels_countr[i].setFont(new Font("Serif", Font.ITALIC, 25));
            labels[i].setLabelFor(labels_countr[i]);
            parallel.addGroup(layout.createSequentialGroup().addComponent(labels[i])
                    .addComponent(labels_countr[i]));
            sequential.addGroup(layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labels[i]).addComponent(labels_countr[i]));
            layout.linkSize(SwingConstants.HORIZONTAL, labels[i], labels[0]);
        }

        return panel;
    }
    //NOT IN USE
    public JPanel createTrav(ArrayList<Traveller> travellers,JPanel panel) {
        JLabel[] labels = new JLabel[travellers.size()];
        JLabel[] labels_countr = new JLabel[travellers.size()];
        //JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.ParallelGroup parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(parallel));
        GroupLayout.SequentialGroup sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
        for (int i = 0; i < travellers.size(); i++) {
            labels[i] = new JLabel(String.valueOf(i + 1) + ". ", JLabel.RIGHT);
            labels[i].setFont(new Font("Serif", Font.HANGING_BASELINE, 22));
            labels_countr[i] = new JLabel(travellers.get(i).getName());
            labels_countr[i].setFont(new Font("Serif", Font.ITALIC, 25));
            labels[i].setLabelFor(labels_countr[i]);
            parallel.addGroup(layout.createSequentialGroup().addComponent(labels[i])
                    .addComponent(labels_countr[i]));
            sequential.addGroup(layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labels[i]).addComponent(labels_countr[i]));
            layout.linkSize(SwingConstants.HORIZONTAL, labels[i], labels[0]);
        }
        return panel;
    }
/*
    public static void main(String[] args) {

        ArrayList<Traveller> a=new ArrayList<>();
        int [] grade={2,3,4,5,2,4,2,4,2,4};
        a.add(new YoungTraveller("koa",17,grade));
        //JPanel p= new JPanel();

        //JPanel panel = new MyGui().createTrav(a,p);
       // JScrollPane jsp = new JScrollPane(panel) {
        //    @Override
         //   public Dimension getPreferredSize() {
         //       return new Dimension(320, 240);
          //  }
       // };
       //JOptionPane.showMessageDialog(null, jsp, "Data", JOptionPane.PLAIN_MESSAGE);





    }

 */
}