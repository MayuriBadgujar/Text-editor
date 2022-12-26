
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener{
    //creating the frame
    JFrame f;
    //creating the text area
    JTextArea t;
    editor(){
        //initialising the frame title
        f=new JFrame( "notepad");

        //setting the overall theme of the application
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }
        //initialising(create) the text area
        t=new JTextArea();

        //create the menubar
        JMenuBar menu=new JMenuBar();

        // initialising file menu

        JMenu file=new JMenu(  "File");

        //create menuitems for file menu
        JMenuItem m1=new JMenuItem("New");
        JMenuItem m2=new JMenuItem("Open");
        JMenuItem m3=new JMenuItem("Save");
        JMenuItem m4=new JMenuItem("Print");

        //adding actionlistner
        m1.addActionListener(  this);
        m2.addActionListener(  this);
        m3.addActionListener(  this);
        m4.addActionListener(  this);

        //adding the menuitems to the menu
        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);

        JMenu edit=new JMenu( "Edit");
        JMenuItem m5=new JMenuItem("Cut");
        JMenuItem m6=new JMenuItem("Copy");
        JMenuItem m7=new JMenuItem("Paste");


        //adding actionlistner to the menuitems
        m5.addActionListener(  this);
        m6.addActionListener(  this);
        m7.addActionListener( this);

         //adding  menuitems to edit menu
        edit.add(m5);
        edit.add(m6);
        edit.add(m7);

        //create close button
        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);

        //adding menu to the menubar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //adding menubar and textarea to the frame
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize( 500, 500);
        //showing the output
        f.show();

    }

    //funtion for adding the funtionality to each of the menuitem
    public void actionPerformed(ActionEvent e){
        //extracting the button pressed
        String s=e.getActionCommand();
        if(s.equals("New")){
        t.setText("");
        }
        else if(s.equals("Open")){
            //initialising thje button pressed
        JFileChooser j=new JFileChooser("d:");

           //invoking the opendialogbox with an integer
        int r=j.showOpenDialog(null);
             //user click on ok or not
        if(r==JFileChooser.APPROVE_OPTION) {

            //set the label to the path of the selected ile location
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            try {

                //stirng to copy the data from the chosen file
                String s1 = "", s2 = "";

                //fr varaible contains raw data (file)
                //store the whole file in fr
                FileReader fr = new FileReader(fi);

                //char by char store in br vari
                //buffer the first character inside s2
                BufferedReader br = new BufferedReader(fr);
                //adding all the lines to s2 one by one
                s2 = br.readLine();
                while ((s1 = br.readLine()) != null) {
                    s2 = s2 + "\n" + s1;
                }
                //setting the text area to s2
                t.setText(s2);
            } catch (Exception et) {
                JOptionPane.showMessageDialog(f, et.getMessage());
            }
        }
        else JOptionPane.showMessageDialog(f,"operation cancelled");

        }
        else if(s.equals("Save")){
        JFileChooser j=new JFileChooser("Dekstop:");
        //where to save our file
        int r=j.showSaveDialog(null);
        if(r==JFileChooser.APPROVE_OPTION){
           File fi=new File(j.getSelectedFile().getAbsolutePath());
           try{
               FileWriter wr=new FileWriter(fi);
               BufferedWriter bw=new BufferedWriter(wr);

               bw.write(t.getText());
               //after writing is finished flush the stream
               //clear the writer older part

               bw.flush();
               bw.close();
           }
           catch(Exception et){
               JOptionPane.showMessageDialog(f,et.getMessage());

           }
        }
        else
            JOptionPane.showMessageDialog(f,"operation cancelled");

        }
        else if(s.equals("Print")){
            //if t.print line show error
            //then click on bubble then use this try catch
          try{
              t.print();
          }
          catch(Exception et){
              JOptionPane.showMessageDialog(f,et.getMessage());
          }
        }
        else if(s.equals("Cut")){
         t.cut();
        }
        else if(s.equals("Copy")){
         t.copy();
        }
        else if(s.equals("Paste")){
        t.paste();
        }
        else if(s.equals("Close")){
        f.setVisible(false);
        }

    }
    public static void main(String args[]) {
        editor e = new editor();
    }
    }
