
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
import java.awt.BasicStroke;
import java.awt.Menu;
import java.awt.MenuBar;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import java.awt.BasicStroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;





public class photoComponent extends JComponent
{
   Image image;
   Dimension size;
   Boolean picFlip = false;
   Boolean picNote = false;
  
  private Image drawingBuffer;
    Color color;
    BasicStroke stroke;  
   
   
    
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
      
    
        
        addMouseListener(new MouseAdapter()
       {
        @Override
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
            
       });
        
        
       
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
//                g2d.setColor(Color.WHITE);
//           
//                g2d.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
                //status.setText("Image Flipped: Photo Note");
                if(picNote)
                {
                     // If this is the first time paintComponent() is being called,
                     // make our drawing buffer.
                     
                     try
                     {// Copy the drawing buffer to the screen.
                     g2d.drawImage(drawingBuffer, 0, 0, null);
                      } finally
                     {
                     }
       
       
                }   
                }
             
            else
            {
                 g2d.drawImage(image, 3, 4, null);
            }
      
 } 
    public Image getImage() {
        return this.drawingBuffer;
    }

    public void setImage(Image newDrawingBuffer) {
        this.drawingBuffer = newDrawingBuffer;
    }
    
    /*
     * Make the drawing buffer and draw some starting content for it.
     */
    private void makeDrawingBuffer() {
        drawingBuffer = createImage(image.getWidth(null), image.getHeight(null));
        fillWithWhite();
    }

    /*
     * Make the drawing buffer entirely white.
     */
    private void fillWithWhite() {
        try {
            final Graphics2D g = (Graphics2D) drawingBuffer.getGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
        } finally {
        }

        // IMPORTANT! every time we draw on the internal drawing buffer, we
        // have to notify Swing to repaint this component on the screen.
        this.repaint();
    }

    
    /*
     * Draw a line between two points (x1, y1) and (x2, y2), specified in
     * pixels relative to the upper-left corner of the drawing buffer.
     */
    private void drawLineSegment(int x1, int y1, int x2, int y2) {
        try {
            final Graphics2D g = (Graphics2D) drawingBuffer.getGraphics();
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
        } finally {
        }

        this.repaint();
    }

    /*
     * Add the mouse listener that supports the user's freehand drawing.
     */
    private void addDrawingController() {
        DrawingController controller = new DrawingController();
        addMouseListener(controller);
        addMouseMotionListener(controller);
    }

    /*
     * DrawingController handles the user's freehand drawing.
     */
    private class DrawingController implements MouseListener, MouseMotionListener {
        // store the coordinates of the last mouse event, so we can
        // draw a line segment from that last point to the point of the next
        // mouse event.
        private int lastX, lastY;

        /*
         * When mouse button is pressed down, start drawing.
         */
        public void mousePressed(MouseEvent e) {
            lastX = e.getX();
            lastY = e.getY();
        }

        /*
         * When mouse moves while a button is pressed down, draw a line
         * segment.
         */
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            drawLineSegment(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        }

        // Ignore all these other mouse events.
        public void mouseMoved(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public void changeColorAndSize(Color newColor, BasicStroke basicStroke) {
        color = newColor;
        stroke = basicStroke;
    }
    
}
