import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
public class PasswordGeneratorGUI{

    public static void main(String[] args)
    {
        //Backend Settings
        PasswordGeneratorSettings settings = new PasswordGeneratorSettings();
        //Frame
        JFrame frame = new JFrame("Password Generator");
        createWindow(frame, settings);

    }

    static void createWindow(JFrame frame, PasswordGeneratorSettings settings)
    {
        //Main stuff
        JPanel panel = new JPanel();
        frame.getContentPane();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 450));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //Password Line
        JTextPane generatedPass = new JTextPane();
        generatedPass.setBounds(30,370,540, 20);
        generatedPass.setVisible(false);
        panel.add(generatedPass);
        
        //Maximum Character Length
        SpinnerModel model = new SpinnerNumberModel(10, 1, 30, 1);
        JSpinner charLength = new JSpinner(model);
        Dimension charLenSize = charLength.getPreferredSize();
        charLength.setBounds(210, 70,charLenSize.width, charLenSize.height);
        panel.add(charLength);
        //Label
        JLabel charLenLabel = new JLabel("Maximum Character Length");
        Dimension charLenLabSize = charLenLabel.getPreferredSize();
        charLenLabel.setBounds(30, 75, charLenLabSize.width, charLenLabSize.height);
        panel.add(charLenLabel);

        //Allows Capitals Checkbox + Functionality
        JCheckBox allowCapital = new JCheckBox("Allows Capitals");
        Dimension allowCapSize = allowCapital.getPreferredSize();
        allowCapital.setBounds(30, 110, allowCapSize.width, allowCapSize.height);
        panel.add(allowCapital);

        allowCapital.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                settings.changeCaps(allowCapital.isSelected());
            }
        });

        //Allows Numbers Checkbox + Functionality
        JCheckBox allowsNumber = new JCheckBox("Allows Numbers");
        Dimension allowNumSize = allowsNumber.getPreferredSize();
        allowsNumber.setBounds(30, 145, allowNumSize.width, allowNumSize.height);
        panel.add(allowsNumber);

        allowsNumber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                settings.changeNums(allowsNumber.isSelected());
            }
        });


        //Allows Special Characters Checkbox + Functionality
        JCheckBox allowsSpecialChars = new JCheckBox("Allows Special Characters");
        Dimension allowSpecSize = allowsSpecialChars.getPreferredSize();
        allowsSpecialChars.setBounds(30, 180, allowSpecSize.width, allowSpecSize.height);
        panel.add(allowsSpecialChars);

        allowsSpecialChars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                settings.changeSpecialChars(allowsSpecialChars.isSelected());
            }
        });




        //Create a generate button
        JButton generateButton = new JButton("Generate");
        Dimension genSize = generateButton.getPreferredSize();
        generateButton.setBounds(255, 300, genSize.width, genSize.height);
        panel.add(generateButton);
        //Generates Password
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                //Add Calculation Method
                generatedPass.setText("Copy Generated Password: " + generatePassword((int)charLength.getValue(), 
                settings.getCaps(), settings.getNums(), settings.getSpecialChars()));
                generatedPass.setVisible(true);              
            }
        });

        

         

        //Final Window setup
        panel.setLayout(null);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
    }

    //Password Generation Function
    static String generatePassword(int maxChar, boolean allowsCaps, boolean allowsNums, boolean allowsSpecialChars)
    {
        //1 = lower, 2 = upper
        String retString = "";
        for(int i = 0; i < maxChar; i++)
        {
            //Capitals
            int upOrLow = 0;
            //Number
            int makeNum = (int)((Math.random() * 9) + 1); //1 in 10
            //Symbol
            int makeSymbol = (int)((Math.random() * 9) + 1); //1 in 10
            if(allowsCaps)
            {
                upOrLow = (Math.random() <= 0.5) ? 1 : 2;
            }
            else if(!allowsCaps)
            {
                upOrLow = 1;
            }
            //Default
            int randLet = 97;
            if(makeNum > 1)
            {
                if(upOrLow == 1)
                {
                    randLet = (int)(Math.random()*25 + 97);
                }
                if(upOrLow == 2)
                {
                    randLet = (int)(Math.random()*25 + 65);
                }
                if(allowsSpecialChars && makeSymbol == 1)
                {
                    int sect = (int)(Math.random()*4 + 1);
                    if(sect == 1)
                    {
                        randLet = (int)(Math.random()*14 + 33);
                    }
                    else if(sect == 2)
                    {
                        randLet = (int)(Math.random()*6 + 58);
                    }
                    else if(sect == 3)
                    {
                        randLet = (int)(Math.random()*5 + 91);
                    }
                    else if(sect == 4)
                    {
                        randLet = (int)(Math.random()*3 + 121);
                    }

                }
            }
            else if(makeNum == 1 && allowsNums)
            {
                randLet = (int)(Math.random()*9 + 48);
            }
            
            retString += (char)(randLet);
        }
        return retString;
    }

    
}
