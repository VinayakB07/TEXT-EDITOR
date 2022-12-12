import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;

public class TextEditor implements ActionListener {

    //Frame for Text Editor
    JFrame frame;

    //Icon for the frame
    ImageIcon icon;

    //MenuBar for the frame
    JMenuBar menuBar;

    //Menu for the menuBar
    JMenu file,edit,darkMode;

    //Menu items for the menu
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JMenuItem enable,disable;

    //Area for editing text
    JTextArea textArea;

    //Constructor
    TextEditor(){

       //Initializing frame
       frame=new JFrame();

       //Initializing TextArea
       textArea=new JTextArea();

       //Initializing MenuBar
       menuBar=new JMenuBar();

       //Initializing Menu
       file=new JMenu("File");
       edit=new JMenu("Edit");
       darkMode=new JMenu("Dark Mode");

       //Initializing menu items
       newFile=new JMenuItem("New");
       openFile=new JMenuItem("Open");
       saveFile=new JMenuItem("Save");

       //Adding ActionListener to menu items
       newFile.addActionListener(this);
       openFile.addActionListener(this);
       saveFile.addActionListener(this);

       //Adding menu items to file menu
       file.add(newFile);
       file.add(openFile);
       file.add(saveFile);

       //Initializing menu items
       cut=new JMenuItem("Cut");
       copy=new JMenuItem("Copy");
       paste=new JMenuItem("Paste");
       selectAll=new JMenuItem("Select All");
       close=new JMenuItem("Close");

       //Adding ActionListener to menu items
       cut.addActionListener(this);
       copy.addActionListener(this);
       paste.addActionListener(this);
       selectAll.addActionListener(this);
       close.addActionListener(this);

       //Adding menu items to the edit menu
       edit.add(cut);
       edit.add(copy);
       edit.add(paste);
       edit.add(selectAll);
       edit.add(close);

       //Initializing menu items
       enable=new JMenuItem("Enable");
       disable=new JMenuItem("Disable");

       //Adding ActionListener to menu items
       enable.addActionListener(this);
       disable.addActionListener(this);

       //Adding menu items to the dark mode menu
       darkMode.add(enable);
       darkMode.add(disable);

       //Adding menu to the menu bar
       menuBar.add(file);
       menuBar.add(edit);
       menuBar.add(darkMode);

       //Adding menu bar to the frame
       frame.setJMenuBar(menuBar);

       //Setting color of menu bar
       menuBar.setBackground(Color.LIGHT_GRAY);

       //Adding text are to the frame
       frame.add(textArea);

       icon=new ImageIcon("icon.png");

       //Setting icon
       frame.setIconImage(icon.getImage());

       //Setting title of the frame
       frame.setTitle("Text Editor");

       //Setting size and location of frame
       frame.setBounds(100,100,800,500);

       //Making frame visible to user
       frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent){

        //when the source is cut
        if(actionEvent.getSource()==cut){
            //performs cut
            textArea.cut();
        }

        //when the source is copy
        if(actionEvent.getSource()==copy){
            //performs copy
            textArea.copy();
        }

        //when the source is paste
        if(actionEvent.getSource()==paste){
            //performs paste
            textArea.paste();
        }

        //when the source is selectAll
        if(actionEvent.getSource()==selectAll){
            //performs select all
            textArea.selectAll();
        }

        //when the source is close
        if(actionEvent.getSource()==close){
            //performs close
            System.exit(0);
        }

        //when the source is enabled
        if(actionEvent.getSource()==enable){
            //changing the text and background color
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.white);
        }

        //when the source is disabled
        if(actionEvent.getSource()==disable){
            //changing the text and background color
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
        }

        //when the source is newFile
        if(actionEvent.getSource()==newFile){
            //creates new textEditor
            TextEditor newTextEditor=new TextEditor();
        }

        //when the source is openFile
        if(actionEvent.getSource()==openFile){

            //Initializing fileChooser and setting the location of file we want
            JFileChooser fileChooser=new JFileChooser("C:");

            //Initializing chooseOption for choosing the file
            int chooseOption=fileChooser.showOpenDialog(null);

            //if we select a file
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //get selected file
                File file=fileChooser.getSelectedFile();
                //get selected file path
                String filePath=file.getPath();
                try{
                    //creating buffer reader
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    //creating string to store content from file
                    String intermediate ="",output="";
                    while((intermediate = bufferedReader.readLine())!=null){
                        output += intermediate+"\n";
                    }
                    //set output to textArea
                    textArea.setText(output);
                }catch (Exception exception){
                    //showing the error if error occurs
                    exception.printStackTrace();
                }
            }
        }

        //when the source is saveFile
        if(actionEvent.getSource()==saveFile){

            //Initializing fileChooser and setting the location were we want to save the file
            JFileChooser fileChooser=new JFileChooser("C:");

            //get choose option from fileChooser
            int chooseOption=fileChooser.showSaveDialog(null);

            //if choose option is approved
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //get selected file
                File file=new File(fileChooser.getSelectedFile().getAbsoluteFile()+"txt");
                try{
                    //creating buffer writer
                    BufferedWriter outFile=new BufferedWriter(new FileWriter(file));
                    //writing content of textArea to the outfile
                    textArea.write(outFile);
                    outFile.close();
                }catch(Exception exception){
                    //showing the error if error occurs
                    exception.printStackTrace();
                }
            }
        }
    }
public static void main(String[] args){
        //creating textEditor in main class
        TextEditor textEditor=new TextEditor();
}
}
