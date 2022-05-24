/**
 * This program moves a "snake" about the screen.
 *
 * @author M. Allen
 */
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class SnakeWrangler implements ActionListener
{
    // graphical elements
    private JFrame window;
    private JButton moveButton, changeButton;
    private Timer timer;
    private Rectangle[] snake;
    
    /*
     * main() to run constructor
     */
    public static void main( String[] args )
    {
        SnakeWrangler wrangler = new SnakeWrangler();
        wrangler.makeWindow();
    }
    
    /*
     * Create the window (600 x 600), with white background, containing a
     * blue/red "snake" and two buttons
     */
    private void makeWindow()
    {
        window = new JFrame();
        window.setLayout( null );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setTitle( "Ssssssssssss!" );
        window.setLocation( 50, 50 );
        window.setSize( 500, 500 );
        window.setResizable( false );
        window.getContentPane().setBackground( Color.white );
        window.setVisible( true );
        
        addSnake();
        addButtons();
        window.repaint();
        
        timer = new Timer( 150, this );
    }
      
    /*
     * Handles user actions, by checking which button was pressed, or responding
     * to the timer.
     */
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == moveButton )
        {
            if ( timer.isRunning() )
            {
                timer.stop();
                moveButton.setText( "Move" );
            }
            else
            {
                timer.start();
                moveButton.setText( "Stop" );
            }
        }
        else if ( e.getSource() == timer )
        {
            move();
        }
        else if ( e.getSource() == changeButton )
        {
            colorSnake();
        }
    }
        
    /*
     * Adds the two action-buttons to the window.
     */
    
    
    private void addButtons()
    {
        moveButton = new JButton( "Move" );
        moveButton.setBounds( window.getWidth() / 2 - 150,
                             window.getHeight() - 55, 100, 25 );
        moveButton.addActionListener( this );
        window.add( moveButton, 0 );
        
        changeButton = new JButton( "Color" );
        changeButton.setBounds( window.getWidth() / 2 + 50,
                               window.getHeight() - 55, 100, 25 );
        changeButton.addActionListener( this );
        window.add( changeButton, 0 );
    }
    
    /*
     * Adds the "snake" object to screen.
     */
    private void addSnake()
    {
        int x = 100;
        int y = 225;
        int recSize = 50;
        snake = new Rectangle[5];
        
        
        for (int i = 0; i < snake.length ; i++  )
        {
        snake[i] = new Rectangle (x + ( i* recSize ), y , recSize, recSize);
        window.add(snake[i]);
        snake[0].setBackground(Color.red);
        snake[i].setBackground(Color.blue);
        }
        colorSnake();
        
        
    }
    
    /*
     * Moves the snake to the left: wraps around the screen
     */
    private void move()
    {
        for ( int i = 0 ; i < snake.length ; i++)
        {
        	int x = snake[i].getX();
        	int y = snake[i].getY();
        	int w = snake[i].getWidth();
        	
        	x = x - w;
        
        	if( x < 0)
        	{
       		x += window.getWidth();
        	}
        
        	snake[i].setLocation(x , y);
        	
        	
        	
        }
        window.repaint();
    }
    
    
    private void colorSnake()
    {
    	if( snake[0].getBackground().equals(Color.red))
    	{
    		snake[0].setBackground(Color.blue);
    		for ( int i = 1 ; i < snake.length ; i++)
    		{
    			snake[i].setBackground(Color.red);
    		}
    	}
    	
    	else
    	{
    		snake[0].setBackground(Color.red);
    		for ( int i = 1 ; i < snake.length ; i++)
    		{
    			snake[i].setBackground(Color.blue);
    		}
    	}

    }

}
