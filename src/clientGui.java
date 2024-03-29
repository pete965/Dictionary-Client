import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;

/**
 * Title      : JungleParty.java
 * Description: This class add the new function based on task1.
 * @author      Boyang Sun
 * @version     1.0
 */
public class clientGui implements ActionListener{
    static String getinput;
    static int howmany;
    JFrame myFrame=new JFrame();
    
    String Desip="";
    int Desport=0;

    JTextField query=new JTextField(10);
    JTextField addKey=new JTextField(10);
    JTextField addValue=new JTextField(10);
    JTextField remove=new JTextField(10);
    JLabel msg1=new JLabel("Which word do you want to search?");
    JLabel msg2=new JLabel("Type in the word and its meaning which you want to add to the dictionary.");
    JLabel msg3=new JLabel("Which word do you want to remove from the dictionary?");
    JButton bt1=new JButton("Query");
    JButton bt2=new JButton("Add");
    JButton bt3=new JButton("Remove");
    JTextArea queryOut = new JTextArea();
    JTextArea addOut = new JTextArea();
    JTextArea removeOut = new JTextArea();
    /**
     This is the main method.
     @param args  No implements will be used.
     */
    public static void main(String[] args){
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        clientGui party=new clientGui();
        party.go(ip,port);
    }

    /**
     This is go mathod.It aims to construct the JAVA GUI.And realize the function of the program.
     */
    public void go(String ip,int port){
        Desip = ip;
        Desport = port;
        myFrame.setSize(1000,700);
        myFrame.setVisible(true);
        myFrame.setTitle("Best Dictionary You Have Ever Met");
        myFrame.setLocation(100,100);

        JPanel pan=new JPanel();
        JPanel pan1=new JPanel();
        JPanel pan2=new JPanel();
        JPanel pan3=new JPanel();
        JPanel pan4=new JPanel();
        JPanel pan5=new JPanel();
        JPanel pan6=new JPanel();
        pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
        pan1.add(msg1);
        pan1.add(query);
        pan1.add(bt1);
        pan2.add(msg2);
        pan2.add(addKey);
        pan2.add(addValue);
        pan2.add(bt2);
        pan3.add(msg3);
        pan3.add(remove);
        pan3.add(bt3);
        pan4.add(queryOut);
        pan5.add(addOut);
        pan6.add(removeOut);
        pan.add(pan1);
        pan.add(pan4);
        pan.add(pan2);
        pan.add(pan5);
        pan.add(pan3);
        pan.add(pan6);
        myFrame.add(pan);
        pan1.setLayout(new FlowLayout());
        pan2.setLayout(new FlowLayout());
        pan3.setLayout(new FlowLayout());
        pan4.setLayout(new FlowLayout());
        pan5.setLayout(new FlowLayout());
        pan6.setLayout(new FlowLayout());
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String output = "No output";
        if(e.getSource()==bt1){
            DictionaryClient dictionaryClient = new DictionaryClient();
            output = dictionaryClient.sendRequest("query",query.getText(),Desip,Desport);
            queryOut.setText(output);
            System.out.println(output);
        }else if(e.getSource()==bt2){
            if(addValue.getText().length()<=0){
                addOut.setText("You must enter a meanning for the word!");
            }else{
                DictionaryClient dictionaryClient = new DictionaryClient();
                output = dictionaryClient.sendRequest("add",addKey.getText()+" "+addValue.getText(),Desip,Desport);
                addOut.setText(output);
            }
        }else{
            DictionaryClient dictionaryClient = new DictionaryClient();
            output = dictionaryClient.sendRequest("remove",remove.getText(),Desip,Desport);
            removeOut.setText(output);
        }
    }
}