///*
// * Sruthi Coimbatore Viswanathan
// * Advanced UI Programming
// * U-PSUD M2 HCI 2015
// * References: Java2s.com/Tutorial, Stack Overflow
//*/
package photoviewer;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFileChooser;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;



public class photoViewer  extends JFrame
{    
    public static JLabel status = new JLabel("Welcome to PhotoViewer.");


    public photoViewer()
    {
        //super();
        //this.setUndecorated(true);
        //this.setOpacity(0.80f);
        // Center it on the screen
       // this.setLocationRelativeTo(null);
               
    }
    
    private static JMenuBar createMenuBar()
    {
      JMenuBar menuBar = new JMenuBar(); 
    
      JMenu fileMenu = new JMenu("File"); 
      JMenu viewMenu = new JMenu("View"); 
      menuBar.add(fileMenu); 
      menuBar.add(viewMenu);

     JMenuItem newMenuItem = new JMenuItem("Import"); 
     newMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                final JFileChooser f = new JFileChooser();
                int file = f.showOpenDialog(null);
            }
        });
     JMenuItem deleteMenuItem = new JMenuItem("Delete"); 
     JMenuItem exitMenuItem = new JMenuItem("Quit");
     exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        fileMenu.add(newMenuItem); 
        fileMenu.add(deleteMenuItem); 
        fileMenu.addSeparator(); 
        fileMenu.add(exitMenuItem);
        
        ButtonGroup viewGroup = new ButtonGroup();
        JRadioButtonMenuItem viewRadio = new JRadioButtonMenuItem("Photo viewer");
        viewRadio.setSelected(true);
        viewGroup.add(viewRadio);
        viewMenu.add(viewRadio);
        viewRadio.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {status.setText("Photo viewer view");}
        });
        JRadioButtonMenuItem browseRadio = new JRadioButtonMenuItem("Browser");
        viewGroup.add(browseRadio);
        viewMenu.add(browseRadio);
        browseRadio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event)
            {status.setText("Browser view");}
        });
        JRadioButtonMenuItem spiltRadio = new JRadioButtonMenuItem("Split");
        viewGroup.add(spiltRadio);
        viewMenu.add(spiltRadio);
        spiltRadio.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {status.setText("Split view");}
        });
        return menuBar;
    };
    
    private static JPanel createPanel()
    {
        JPanel mainPanel = new JPanel(new BorderLayout()); 
      
      JPanel bPanel = new JPanel(new BorderLayout()); 
      
      mainPanel.add(bPanel,BorderLayout.SOUTH);

      
      
      
      bPanel.add(status,BorderLayout.CENTER);
      
       //Button closeButton = new JButton("Close");
      //bPanel.add(closeButton,BorderLayout.SOUTH);
      //closeButton.addActionListener(e -> System.exit(0)); 
      
         
      
      
        JPanel pPanel = new JPanel(); 
        mainPanel.add(pPanel,BorderLayout.NORTH);
        
        
        
        JToggleButton home = new JToggleButton("Home");
        JToggleButton vacation = new JToggleButton("Vacation");
        JToggleButton official = new JToggleButton("Official");
        JToggleButton recent = new JToggleButton("Recent");

        ChangeListener changeListener1 = new ChangeListener() 
            {
                public void stateChanged(ChangeEvent changeEvent) 
                {
                AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
                ButtonModel buttonModel = abstractButton.getModel();
                //boolean armed = buttonModel.isArmed();
                //boolean pressed = buttonModel.isPressed();
                boolean selected = buttonModel.isSelected();
                 if (selected==true)
                 { status.setText("Home Mode is ON");}    
                }
            };
        

        ChangeListener changeListener2 = new ChangeListener() 
            {
                public void stateChanged(ChangeEvent changeEvent) 
                {
                AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
                ButtonModel buttonModel = abstractButton.getModel();
                //boolean armed = buttonModel.isArmed();
                //boolean pressed = buttonModel.isPressed();
                boolean selected = buttonModel.isSelected();
                 if (selected==true)
                 {status.setText("Vacation Mode is ON");}        
                   
                }
            };
        
       
        ChangeListener changeListener3 = new ChangeListener() 
            {
                public void stateChanged(ChangeEvent changeEvent) 
                {
                AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
                ButtonModel buttonModel = abstractButton.getModel();
                //boolean armed = buttonModel.isArmed();
                //boolean pressed = buttonModel.isPressed();
                boolean selected = buttonModel.isSelected();
                 if (selected==true)
                 {status.setText("Official Mode is ON");}        
                   
                }
            };
        
       

        ChangeListener changeListener4 = new ChangeListener() 
            {
                public void stateChanged(ChangeEvent changeEvent) 
                {
                AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
                ButtonModel buttonModel = abstractButton.getModel();
                    
                //boolean pressed = buttonModel.isPressed();
                boolean selected = buttonModel.isSelected();
                 if (selected==true)
                 {status.setText("Recent Mode is ON");}        
                   
                }
            };
        
        pPanel.add(home);
        pPanel.add(vacation);
        pPanel.add(official);
        pPanel.add(recent);
        
        // Attach Listeners
        home.addChangeListener(changeListener1);
        vacation.addChangeListener(changeListener2);
        official.addChangeListener(changeListener3);
        recent.addChangeListener(changeListener4);
        
        
        return mainPanel;
    };
     
    
    
      
   
    public static void main(String[] args) 
    {
      SwingUtilities.invokeLater(() -> 
      {
      photoViewer frame = new photoViewer();
      
      photoComponent pc = new photoComponent(30,30,10);


      frame.setVisible(true);
      frame.add(createMenuBar(), BorderLayout.PAGE_START); 
      frame.add(createPanel(), BorderLayout.SOUTH);
      frame.add(pc,BorderLayout.CENTER);
      frame.setSize(500, 500);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      

        
      });
      
    
}
}
    
