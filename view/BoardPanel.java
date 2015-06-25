package view;

/*
 * Alex Prokopchik
 * May 11, 2013
 * BoardPanel.java
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Block;
import model.Board;

/**
 * The panel for which the game takes place. The board panel of the Tetris game.
 * 
 * @author alexpro
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel
{  
  /**
   * The delay of the timer.
   */
  private static final int DELAY = 1000;
  
  /**
   * The size of the blocks.
   */
  private static final int BLOCK_SIZE = 25;
  
  /**
   * The offset of the blocks to be centered with grid.
   */
  private static final int BLOCK_OFFSET = 4;
  
  /**
   * The color of the background of the panel.
   */
  private static final Color FOREGROUND_COLOR = Color.BLACK;

  /**
   * The offset of the start of the grid for the x axis since there is a border around panel.
   */
  private static final int GRID_X_OFFSET = 4;
  
  /**
   * The offset of the start of the grid for the y axis since there is a border around panel.
   */
  private static final int GRID_Y_OFFSET = 14;
  
  /**
   * An offset that determines where the pieces should stop at the bottom.
   */
  private static final int BLOCK_STOP = 488;
  
  /**
   * The thickness of the line around each shape.
   */
  private static final int SHAPE_THICKNESS = 4;
  
  /**
   * The amount of squares drawn per piece.
   */
  private static final int FOUR = 4;
  
  /**
   * The font and size of the game over indication.
   */
  private static final Font GAME_OVER_FONT = new Font("Arial Black", Font.PLAIN, 24);
  
  /** 
   * The x coord of the game over indication. 
   */
  private static final int GAMEOVER_X = 50;
  
  /** 
   * The y coord of the game over indication. 
   */
  private static final int GAMEOVER_Y = 200;
  
  /** 
   * The name of the tetris song. 
   */
  private static final String SONG_NAME = "tetris/audio/UNKNOWN - BassHunter - Tetris.wav";
  
  /**
   * The song that plays as tetris starts.
   */
  private final SoundPlayer my_music = new SoundPlayer();
  
  /**
   * The Tetris game board.
   */
  private final Board my_game;
  
  /**
   * The timer for the game.
   */
  private final Timer my_timer;
  
  /**
   * The score of the game.
   */
  private int my_score;

  /**
   * The level of the game.
   */
  private int my_level;
  
  /**
   * A boolean for grid that determines if button is pressed.
   */
  private boolean my_grid;
  
  /**
   * A boolean for quit that determines if button is pressed.
   */
  private boolean my_quit;
  
  /**
   * A boolean for new game that determines if button is pressed.
   */
  private boolean my_new_game;
  
  /**
   * A boolean that determines if pause is pressed.
   */
  private boolean my_paused;
  
  /**
   * A boolean that determines if easy is pressed.
   */
  private boolean my_easy;
  
  /**
   * A boolean that determines if normal is pressed.
   */
  private boolean my_normal;
  
  /**
   * A boolean that determines if hard is pressed.
   */
  private boolean my_hard;

  /**
   * Constructs the panel.
   * 
   * @param the_game the board of the Tetris game.
   */
  public BoardPanel(final Board the_game) 
  {
    super();
    my_game = the_game;
    my_timer = new Timer(DELAY, new TimerListener());
    my_timer.start();
    setBackground(FOREGROUND_COLOR);
    my_music.loop(SONG_NAME);
    addKeyListener(new MyKeyListener());
    setFocusable(true);
  }
  
  /**
   * Draws the grid on the board panel if the grid checkbox is selected,
   * Draws the shapes if the game starts,
   * Stops the timer if the game ends,
   * Displays game over when the game ends.
   * 
   * @param the_graphics The graphics context to use for drawing the grid and pieces.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;
    my_score = my_game.getScore();
    my_level = my_game.getLevel();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    //  draws the Grid
    if (my_grid)
    {
      g2d.setPaint(Color.WHITE);
      g2d.setStroke(new BasicStroke(1));
      for (int i = 0; i <= getWidth(); i++) 
      {
        g2d.drawLine(i * BLOCK_SIZE + GRID_X_OFFSET, 0, 
                     i * BLOCK_SIZE + GRID_X_OFFSET, getSize().height);
      }

      for (int i = 0; i <= getHeight(); i++) 
      {
        g2d.drawLine(0, i * BLOCK_SIZE + GRID_Y_OFFSET, getSize().width,
                     i * BLOCK_SIZE + GRID_Y_OFFSET);
      }
    }
    
    //functions the new game button
    else if (my_new_game)
    {
      my_quit = false;
      my_timer.start();
      //Sets the delay for each difficulty button.
      getDifficulty();
    }
    
    
    //starts the game and functions the quit button
    else if (!my_game.gameIsOver())
    {
      for (int i = my_game.getHeight() + FOUR; i > my_game.getHeight(); i--)
      {
        drawPieces(g2d, i);
      }
      
      //Sets the delay for each difficulty button.
      getDifficulty();
    }
    for (int i = my_game.getHeight(); i >= 0; i--)
    {
      drawPieces(g2d, i);
    }
    if (my_game.gameIsOver() || my_quit)
    {
      my_new_game = false;
      g2d.setFont(GAME_OVER_FONT);
      g2d.setColor(Color.RED);
      my_timer.stop();
      g2d.drawString("Game Over!", GAMEOVER_X, GAMEOVER_Y);
    }
  }
  
  /**
   * The method that draws all of the shapes.
   * 
   * @param the_graphics2d The graphics context to use for drawing the pieces.
   * @param the_row the row of the pieces drawn.
   */
  public void drawPieces(final Graphics2D the_graphics2d, final int the_row)
  {
    the_graphics2d.setStroke(new BasicStroke(SHAPE_THICKNESS));
    if (my_game.getFrozenBlocks().size() - 1 < the_row)
    {
      for (int i = 0; i < my_game.getWidth(); i++)
      {
        if (my_game.currentPieceAt(i, the_row))
        {
          the_graphics2d.setPaint(Color.WHITE);
          the_graphics2d.drawRect(i * BLOCK_SIZE + BLOCK_OFFSET, BLOCK_STOP 
                                  - ((the_row - 1) * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
        }
      }
    }
    else
    {
      final Block[] row_blocks = my_game.getFrozenBlocks().get(the_row);
      
      for (int i = 0; i < my_game.getWidth(); i++)
      {
        if (my_game.currentPieceAt(i, the_row))
        {
          the_graphics2d.setPaint(Color.WHITE);
          the_graphics2d.drawRect(i * BLOCK_SIZE + BLOCK_OFFSET, BLOCK_STOP 
                                  - ((the_row - 1) * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
        }
        else if (row_blocks[i] != Block.EMPTY)
        {
          the_graphics2d.setPaint(Color.YELLOW);
          the_graphics2d.drawRect(i * BLOCK_SIZE + BLOCK_OFFSET, BLOCK_STOP 
                                  - ((the_row - 1) * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
        }
      }
    }
  }
  
  /**
   * The method that determines if the game is paused or not.
   */
  private void pause()
  {
    if (my_paused)
    {
      my_music.play(SONG_NAME);
      my_timer.start();
      my_paused = false;
    }
    else
    {
      my_music.pause(SONG_NAME);
      my_timer.stop();
      my_paused = true;
    }
  }
  
  /**
   * Setter for the grid button to determine if it is selected or not.
   * @param the_grid boolean value that sets the grid button to draw or not.
   */
  public void setDrawGrid(final boolean the_grid)
  {
    my_grid = the_grid;
  }
  
  /**
   * Setter for the quit button to determine if it is selected or not.
   * @param the_quit boolean value that sets the quit button to quit or not.
   */
  public void setQuit(final boolean the_quit)
  {
    my_quit = the_quit;
  }
  
  /**
   * Setter for the new game button to determine if it is selected or not.
   * @param the_new_game boolean value that sets the new game button to 
   * create a new game or not.
   */
  public void setNewGame(final boolean the_new_game)
  {
    my_new_game = the_new_game;
  }
  
  /**
   * Setter for the easy, normal, and hard buttons to determine if they are selected or not.
   * 
   * @param the_easy boolean value that sets the easy button to 
   * set the game to easy or not.
   * @param the_normal boolean value that sets the normal button to 
   * set the game to normal or not.
   * @param the_hard boolean value that sets the hard button to 
   * set the game to hard or not.
   */
  public void setDifficulty(final boolean the_easy, final boolean the_normal, 
                            final boolean the_hard)
  {
    my_easy = the_easy;
    my_normal = the_normal;
    my_hard = the_hard;
  }
  
  /**
   * Getter for the score to determine what it is depending on if new game is pressed.
   * @return the_score int value that gets the score
   */
  public int getScore()
  {
    return my_score;
  }
  
  /**
   * Getter for the level to determine what it is depending on if new game is pressed.
   * @return the_score int value that gets the score
   */
  public int getLevel()
  {
    return my_level;
  }
  
  /**
   * Getter for the difficulty buttons that set the delays for each difficulty.
   */
  private void getDifficulty()
  {
    if (my_easy)
    {
      my_timer.setDelay(DELAY - my_game.getDelay() / 2);
    }
    else if (my_normal)
    {
      my_timer.setDelay(DELAY - my_game.getDelay());
    }
    else if (my_hard)
    {
      my_timer.setDelay(DELAY - my_game.getDelay() * 2);
    }
  }
  
  /**
   * Sets the Key listeners to the keys pressed.
   * Sets the piece to rotate if R is pressed,
   * Sets the piece to move left if the left arrow is pressed,
   * Sets the piece to move right if the right arrow is pressed,
   * Sets the piece to move down if the down arrow is pressed,
   * Sets the piece to drop if space is pressed.
   * 
   * @param the_event The event.
   */
  private void myKey(final KeyEvent the_event)
  {
    if (the_event.getKeyCode() == KeyEvent.VK_R)
    {
      my_game.rotate();
    }
    else if (the_event.getKeyCode() == KeyEvent.VK_LEFT)
    {
      my_game.moveLeft();
    }
    else if (the_event.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      my_game.moveRight();
    }
    else if (the_event.getKeyCode() == KeyEvent.VK_DOWN)
    {
      my_game.moveDown();
    }
    else if (the_event.getKeyCode() == KeyEvent.VK_SPACE)
    {
      my_game.hardDrop();
    }
  }
  
  /**
   * Listens for the timer.
   * @author alexpro
   * @version 1.0
   */
  private class TimerListener implements ActionListener
  {
    /**
     * Updates the game by one step.
     * 
     * @param the_event The event.
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      my_game.step();
    }
  }
  
  /**
   * Listens for key presses, to move pieces on the panel.
   */
  private class MyKeyListener extends KeyAdapter
  {
    /**
     * Handles a movement when key is pressed. Also calls myKey to set the key 
     * listeners to the key.
     * Sets the game to pause if P is pressed.
     * 
     * @param the_event The event.
     */
    public void keyPressed(final KeyEvent the_event)
    {
      if (!my_quit) 
      {
        if (!my_paused && !my_game.gameIsOver())
        {
          myKey(the_event);
        }
        if (the_event.getKeyCode() == KeyEvent.VK_P)
        {
          pause();
        }
      }
    }
  }
}
