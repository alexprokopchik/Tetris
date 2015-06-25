package view;

/*
 * Alex Prokopchik
 * May 11, 2013
 * ScorePanel.java
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * The panel for which the score takes place.
 * 
 * @author alexpro
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel
{ 
  /**
   * The color of the background of the panel.
   */
  private static final Color FOREGROUND_COLOR = Color.BLACK;
  
  /**
   * The x coordinate of where the score and level strings start.
   */
  private static final int SCORE_LEVEL_X = 10;
  
  /**
   * The y coordinate of where the score string starts.
   */
  private static final int SCORE_Y = 60;
  
  /**
   * The y coordinate of where the level string starts.
   */
  private static final int LEVEL_Y = 132;
  
  /**
   * The font and size of the score.
   */
  private static final Font SCORE_FONT = new Font("Arial Black", Font.PLAIN, 28);
  
  /**
   * This empty String represents the integer score and level as a string.
   */
  private static final String MAKE_STRING = " ";
  
  /**
   * The Tetris board panel.
   */
  private final BoardPanel my_board_panel;
  
  /**
   * Constructs the panel, and sets the background color.
   * 
   * @param the_board_panel the board panel of the Tetris game.
   */
  public ScorePanel(final BoardPanel the_board_panel) 
  {
    super();
    my_board_panel = the_board_panel;
    setBackground(FOREGROUND_COLOR);
  }
  
  /**
   * Draws the string Score and Level and casts and represents each as a string.
   * 
   * @param the_graphics The graphics context to use for drawing score and level.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    
    final int score = my_board_panel.getScore();
    final String score_string = score + MAKE_STRING;  
    final int level = my_board_panel.getLevel();
    final String level_string = level + MAKE_STRING;
    
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
    g2d.setFont(SCORE_FONT);
    g2d.setColor(Color.YELLOW);
    g2d.drawString("Score: " + score_string, SCORE_LEVEL_X, SCORE_Y);
    g2d.drawString("Level: " + level_string, SCORE_LEVEL_X, LEVEL_Y);
  }
}