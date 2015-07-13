package view;

/*
 * Alex Prokopchik
 * May 11, 2013
 * NewPiecePanel.java
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.Board;

/**
 * The panel in which the new piece takes place.
 * 
 * @author alexpro
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NewPiecePanel extends JPanel
{ 
  /**
   * The size of each block.
   */
  private static final int BLOCK_SIZE = 25;
  
  /**
   * The half way mark of the x and y coord of the new piece panel.
   */
  private static final int HALF_WAY_PANEL = 100;
  
  /**
   * The half way mark of the x and y coord of the new piece panel.
   */
  private static final int HALF_OF_HALF_PANEL = 50;
  
  /**
   * The half way mark of the x and y coord of the new piece panel.
   */
  private static final int SEVENTY_FIVE = HALF_OF_HALF_PANEL + BLOCK_SIZE;
  
  /**
   * The size of the border thickness of each piece.
   */
  private static final int STROKE_SIZE = 4;
  
  /**
   * The color of the background of the panel.
   */
  private static final Color FOREGROUND_COLOR = Color.BLACK;
  
  /**
   * The Tetris game board.
   */
  private final Board my_game;

  /**
   * Constructs the panel.
   * 
   * @param the_game the new piece panel of the Tetris game.
   */
  public NewPiecePanel(final Board the_game) 
  {
    super();
    my_game = the_game;
    setBackground(FOREGROUND_COLOR);
    setFocusable(true);
  }
  
  /**
   * Draws each shape in the new piece panel by drawing each square of the piece separately
   * depending on the coordinates and panel size.
   * 
   * @param the_graphics The graphics context to use for drawing the pieces.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    g2d.setStroke(new BasicStroke(STROKE_SIZE));
    g2d.setColor(Color.WHITE);
    
    if ("IPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(HALF_OF_HALF_PANEL, HALF_WAY_PANEL - BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_OF_HALF_PANEL + BLOCK_SIZE, HALF_WAY_PANEL - BLOCK_SIZE, 
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL, HALF_WAY_PANEL - BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL + BLOCK_SIZE, HALF_WAY_PANEL - BLOCK_SIZE, 
                   BLOCK_SIZE, BLOCK_SIZE); 
    }
    else if ("OPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(SEVENTY_FIVE, SEVENTY_FIVE - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL, SEVENTY_FIVE - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(SEVENTY_FIVE, HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL, HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
    }
    else if ("SPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   SEVENTY_FIVE - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL + BLOCK_SIZE / 2, 
                   SEVENTY_FIVE - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE); 
    }
    else if ("ZPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 + BLOCK_SIZE, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   SEVENTY_FIVE - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, 
                   SEVENTY_FIVE - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE); 
    }
    else if ("JPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, HALF_WAY_PANEL - BLOCK_SIZE / 2,
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL + BLOCK_SIZE / 2, HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE); 
    }
    else if ("TPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, SEVENTY_FIVE - BLOCK_SIZE / 2,
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL + BLOCK_SIZE / 2, HALF_WAY_PANEL - BLOCK_SIZE / 2, 
                   BLOCK_SIZE, BLOCK_SIZE); 
    }
    else if ("LPiece".equals(my_game.getNextPiece().getClass().getSimpleName()))
    {
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2, BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL - BLOCK_SIZE / 2, HALF_WAY_PANEL - BLOCK_SIZE / 2,
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL + BLOCK_SIZE / 2, HALF_WAY_PANEL - BLOCK_SIZE / 2,
                   BLOCK_SIZE, BLOCK_SIZE);
      g2d.drawRect(HALF_WAY_PANEL + BLOCK_SIZE / 2, 
                   HALF_WAY_PANEL - BLOCK_SIZE / 2 - BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE); 
    }
  }  
}
