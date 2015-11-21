
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
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Point;





public class photoComponent extends JComponent
{
   Image image;
   Dimension size;
   Boolean picFlip = false;
   Boolean picNote = false;
   Boolean picText = false;
   Boolean picWrit = false;
  
  
   private Image drawingBuffer;
   Color color;
   BasicStroke stroke;  
    
    private Point textPoint  = new Point();
    private ArrayList<Character> textArray;
    private boolean textWrit = false;
    

   
   
    
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
        
        
        addDrawingController();
        color = Color.BLACK;
        stroke = new BasicStroke(1);
        
        addWritingController();
        this.textArray = new ArrayList<>();
        setFocusable(true);
        
        addFlipController();
      
    }
    
    public void paintComponent(Graphics g) 
    {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            
            if(picFlip)
            {
                   if (drawingBuffer == null) 
                     {
                       makeDrawingBuffer();
                       
                     }

                     if(picNote)
                    {
                     
                     try
                     {
                     g2d.drawImage(drawingBuffer, 0, 0, null);
                      }
                     finally
                     {
                     }
                      
                     } 
                if(textWrit && !picWrit)
                {
                   
                    g.setColor(Color.RED);
                    char[] text = new char[textArray.size()];
                    for(int i = 0; i<textArray.size(); i++)
                    {
                        text[i] = textArray.get(i);
                    }
                    g.drawChars(text, 0, text.length, 100, 100);
                }
                if(picWrit)
                {
                    
                    g.setColor(Color.BLUE);
                    g.drawLine(textPoint.x, textPoint.y, textPoint.x, textPoint.y-5);
                    if(textWrit)
                    {
                        
                        char[] text = new char[textArray.size()];
                        for(int i = 0; i<textArray.size(); i++)
                        {
                            text[i] = textArray.get(i);
                        }
                        g.drawChars(text, 0, text.length, textPoint.x, textPoint.y);
                        picText = true;
                    }
                }
                }
             
            else
            {
                 g2d.drawImage(image, 3, 4, null);
            }
      
 } 
    public Image getImage()
    {
        return this.drawingBuffer;
    }

    public void setImage(Image newDrawingBuffer)
    {
        this.drawingBuffer = newDrawingBuffer;
    }
    
    
    private void makeDrawingBuffer() 
    {
        drawingBuffer = createImage(image.getWidth(null), image.getHeight(null));
        fillWithWhite();
    }

    
    private void fillWithWhite()
    {
        try 
        {
            final Graphics2D g = (Graphics2D) drawingBuffer.getGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
        }
        finally
        { }

       
        this.repaint();
    }

    
    
    private void drawLineSegment(int x1, int y1, int x2, int y2)
    {
        try
        {
            final Graphics2D g = (Graphics2D) drawingBuffer.getGraphics();
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
        }
        finally 
        {
        }

        this.repaint();
    }
    
    private void addFlipController()
    {
        FlipController controller = new FlipController();
        addMouseListener(controller);
        
    }

    private class FlipController implements MouseListener
    {
               
        
        public void mouseClicked(MouseEvent e)
        {
        if(e.getClickCount()==2)
        { 
           if(!picFlip)
           {
               picFlip = true;
            repaint();
           }
           else
           {
               picFlip = false;
               repaint();
           }
        }
        else if(e.getClickCount()==1)
        {
            if(picFlip)
            {
               if(picWrit)
                picWrit = false;
                else{
                     picWrit = true;
                      textPoint = e.getPoint(); 
                      }
                            requestFocus();
                            repaint();
        }
        }
        
        }
        
        public void mousePressed(MouseEvent e) 
        {
            if(picFlip)
            {
                  picNote = true;
            }
         }
        
    
        public void mouseDragged(MouseEvent e) 
        {
            if(!picFlip)
            {
                picNote = true;
                repaint();
            }
            
        }
        
        public void mouseEntered(MouseEvent e) 
        {
        }
        public void mouseReleased(MouseEvent e)
        {
        }
         public void mouseExited(MouseEvent e)
        {
        }
            
       }
        
    
    
    private void addDrawingController() 
    {
        DrawingController controller = new DrawingController();
        addMouseListener(controller);
        addMouseMotionListener(controller);
    }

    
    private class DrawingController implements MouseListener, MouseMotionListener
    {
        private int lastX, lastY;

        public void mousePressed(MouseEvent e) 
        {
            lastX = e.getX();
            lastY = e.getY();
        }

        public void mouseDragged(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            drawLineSegment(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        }

       
        public void mouseMoved(MouseEvent e) 
        {
        }

        public void mouseClicked(MouseEvent e)
        {
        }

        public void mouseReleased(MouseEvent e) 
        {
        }

        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }
    }
    
     private void addWritingController() 
     {
        WritingController controller = new WritingController();
        addKeyListener(controller);
     }
     
     private class WritingController implements KeyListener 
     {
         
                    public void keyTyped(KeyEvent e)
                    {
                        requestFocus();
                        
                        if((int)e.getKeyChar() == 8)
                            textArray.remove(textArray.size()-1);
                        else if((int)e.getKeyChar() == 27)
                            picWrit = false;
                        else{
                            textArray.add(e.getKeyChar());
                            textWrit = true;
                        }
                        repaint();
                    }

                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                    }

                    @Override
                    public void keyReleased(KeyEvent e)
                    {                    
                    }
     
     }

}
