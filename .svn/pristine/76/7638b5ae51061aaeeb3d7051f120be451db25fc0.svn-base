/*
 *Alex Prokopchik
 *May 11, 2013
 *TetrisGUI.java
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.Board;

/**
 * The GUI that shows the visuals of the Tetris game.
 * 
 * @author alexpro
 * @version 1.0
 */
public class TetrisGUI implements Observer
{
  /**
   * The overall width of the frame.
   */
  private static final int FRAME_WIDTH = 471;
  
  /**
   * The overall width of the frame.
   */
  private static final int FRAME_HEIGHT = 600;
  
  /** 
   * The default size for this JFrame. 
   */
  private static final Dimension DEFAULT_FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

  /**
   * The constant that divides the east panel into 3 sections.
   */
  private static final int DIVIDE_EAST = 3;
  /** 
   * The default size for this JPanel that previews the next piece. 
   */
  private static final Dimension DEFAULT_PREVIEW_SIZE = new Dimension(200, 200);
  
  /** 
   * The default size for this JPanel Tetris board. 
   */
  private static final Dimension DEFAULT_BOARD_SIZE = 
      new Dimension(FRAME_WIDTH - (FRAME_WIDTH - (FRAME_HEIGHT / 2)), FRAME_HEIGHT);
 
  /**
   * The default size of the east JPanel.
   */
  private static final Dimension DEFAULT_EAST_SIZE = 
      new Dimension(200, FRAME_HEIGHT);
  
  /**
   * The border surrounding east and board panel.
   */
  private static final Border WHITE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 3);
  
  /**
   * The border surrounding the instructions and next piece panel.
   */
  private static final Border YELLOW_BORDER = BorderFactory.createLineBorder(Color.YELLOW, 5);
 
  /**
   * The universal font for the game.
   */
  private static final String UNIV_FONT = "Arial Black";
  
  /**
   * The font and size of the instructions.
   */
  private static final Font INST_FONT = new Font(UNIV_FONT, Font.PLAIN, 11);
  
  /**
   * The frame for this application's GUI.
   */
  private final JFrame my_frame;
  
  /**
   * The main panel that contains all of the other panels.
   */
  private final JPanel my_master_panel = new JPanel(new BorderLayout());
  
  /**
   * The right side of the Tetris game that includes previewed piece, score, and instructions.
   */
  private final JPanel my_east_panel = new JPanel(new GridLayout(DIVIDE_EAST, 0));
  
  /**
   * The panel that shows the instructions.
   */
  private final JPanel my_inst_panel = new JPanel(new BorderLayout());
  
  /**
   * The Tetris game board.
   */
  private final Board my_game = new Board();
  
  /**
   * The instructions of the game.
   */
  private final JLabel my_instructions = new JLabel("<html>INSTRUCTIONS:<br>"
      + "Move Left: Left Arrow Key<br>" 
      + "Move Right: Right Arrow Key<br>" 
      + "Move Down: Down Arrow Key<br>" 
      + "Rotate: R<br>" 
      + "Drop: Space<br>"
      + "Pause: P<html>", SwingConstants.CENTER);
  
  /**
   * The panel in which the tetris board is located.
   */
  private final BoardPanel my_board_panel = new BoardPanel(my_game);
  
  /**
   * The panel in which the new piece is located.
   */
  private final NewPiecePanel my_newpiece_panel = new NewPiecePanel(my_game);
  
  /**
   * The panel in which the score is located.
   */
  private final ScorePanel my_score_panel = new ScorePanel(my_board_panel);

  /**
   * Construct the GUI.
   * Adds the observer to the board.
   * Sets the sizes for the frame and the new piece, board, and east panel.
   */
  public TetrisGUI()
  {
    my_frame = new JFrame();
    my_game.addObserver(this); 
    
    //set sizes for each panel
    my_frame.setSize(DEFAULT_FRAME_SIZE);
    my_newpiece_panel.setPreferredSize(DEFAULT_PREVIEW_SIZE);
    my_board_panel.setPreferredSize(DEFAULT_BOARD_SIZE);
    my_east_panel.setPreferredSize(DEFAULT_EAST_SIZE);
  }
  
  /**
   *  Sets up the GUI,
   *  Sets the frame to not resize,
   *  Requests focus from the board panel.
   */
  public void start()
  {
    my_frame.setTitle("Alex's Tetris");
    my_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    my_frame.setResizable(false);
    setupFrameVisual();
    my_frame.setVisible(true);
    
    my_board_panel.requestFocus();
  }
  
  /**
   * Sets up all of the visual aspects of the Game.
   */
  private void setupFrameVisual()
  {
    final TetrisMenuBar menu_bar = new TetrisMenuBar(my_board_panel, my_game);
    
    //sets up the layout for the instructions panel.
    my_inst_panel.setBackground(Color.BLACK);
    my_inst_panel.setBorder(YELLOW_BORDER);
    my_instructions.setFont(INST_FONT);
    my_instructions.setForeground(Color.YELLOW);
    my_inst_panel.add(my_instructions);
    
    //sets up the layout for the east panel
    my_east_panel.setBorder(WHITE_BORDER);
    my_newpiece_panel.setBorder(YELLOW_BORDER);
    my_east_panel.setBackground(Color.BLACK);  
    my_east_panel.add(my_newpiece_panel);
    my_east_panel.add(my_score_panel);
    my_east_panel.add(my_inst_panel);
    
    //sets up the master panel layout behind everything.
    my_board_panel.setBorder(WHITE_BORDER);
    my_master_panel.add(my_board_panel, BorderLayout.CENTER);
    my_master_panel.add(my_east_panel, BorderLayout.EAST);
    my_master_panel.setBorder(WHITE_BORDER);

    //Sets up the frame visual
    my_frame.setJMenuBar(menu_bar);
    my_frame.add(my_master_panel); 
  }

  /**
   * Updates the Observer.
   * 
   * @param the_obj the observable object.
   * @param the_arg an argument passed to the notifyObservers method
   */
  public void update(final Observable the_obj, final Object the_arg)
  {
    my_board_panel.repaint();
    my_east_panel.repaint();
  }
}
