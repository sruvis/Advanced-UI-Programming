
///*
// * Sruthi Coimbatore Viswanathan
// * Advanced UI Programming
// * U-PSUD M2 HCI 2015
// * References: Java2s.com/Tutorial, Stack Overflow
//*/
package photoviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JComponent;





public class photoComponent extends JComponent
{
   Image image;
   Dimension size;
   Boolean imgFlip = false;
   
   int currentX, currentY, oldX, oldY;

   
       
    public photoComponent(int x, int y, int diameter)
    {
        super();
        this.setLocation(x, y);
        this.setSize(diameter, diameter);
        size = new Dimension();
        image = new ImageIcon(this.getClass().getResource("fav 2.jpg")).getImage();
        size.width = image.getWidth(null);
        size.height = image.getHeight(null);
        setPreferredSize(size);
        
        repaint();
        
        addMouseListener(new MouseAdapter()
       {
        @Override
        public void mouseClicked(MouseEvent e)
        {
        if(e.getClickCount()==2)
        { 
           if(!imgFlip)
           {
               imgFlip = true;
            repaint();
           }
           else
           {
               imgFlip = false;
               repaint();
           }
        }
        
        }
        
        public void mousePressed(MouseEvent e) 
        {
            if(!imgFlip)
            {
                  oldX = e.getX();
                  oldY = e.getY();
            }
         }
        
    
        public void mouseDragged(MouseEvent e) 
        {
            if(!imgFlip)
             repaint();
            
        }
            
       });
        
        
       
    }
    
    public void paintComponent(Graphics g) 
    {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            


            
            if(imgFlip)
            {
                g2d.setColor(Color.WHITE);
           
                g2d.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
                //status.setText("Image Flipped: Photo Note");
                setDoubleBuffered(false);
                  
                currentX = getX();
             currentY = getY();
                if (g2d != null)
                 g2d.drawLine(oldX, oldY, currentX, currentY);
                repaint();
                oldX = currentX;
                oldY = currentY;
             }
            else
            {
                 g2d.drawImage(image, 3, 4, null);
            }
      
 }  
    
}
