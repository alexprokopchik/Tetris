package view;
/*
 * Alex Prokopchik
 * May 11, 2013
 * TetrisMenuBar.java
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

import model.Board;


/**
 * The menu bar for the Tetris game.
 * 
 * @author alexpro
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TetrisMenuBar extends JMenuBar
{
  /** The width of the about frame. */
  private static final int ABOUT_WIDTH = 600;
  
  /** The height of the about frame. */
  private static final int ABOUT_HEIGHT = 300;
  
  /**The size of the font of the instructions. */
  private static final int INSTR_SIZE = 18;
  
  /** The number of difficulty buttons in my array. */
  private static final int NUM_OF_DIFF_BUTNS = 3;
  
  /** A string that links the button help with the jframe help. */
  private static final String HELP = "Help";

  /** The File menu. */
  private final JMenu my_file_menu = new JMenu("File");
  
  /** The Options menu. */
  private final JMenu my_options_menu = new JMenu("Options");
  
  /** The Help menu. */
  private final JMenu my_help_menu = new JMenu(HELP);
  
  /** A button to exit the program. */
  private final JMenuItem my_quit_button = new JMenuItem("Quit Game");
  
  /** A button to exit the program. */
  private final JMenuItem my_newgame_button = new JMenuItem("New Game");
  
  /** The Options menu grid check box. */
  private final JCheckBox my_grid_button = new JCheckBox("Grid");
  
  /** The Paint that goes on the frame. */
  private final BoardPanel my_board_panel;
  
  /** The Paint that goes on the frame. */
  private final Board my_game;
  
  /** The Options menu difficulty radio buttons. */
  private final JRadioButtonMenuItem[] my_diff_buttons = 
      new JRadioButtonMenuItem[NUM_OF_DIFF_BUTNS];
  
  /** A button group for the Options menu radio buttons. */
  private final ButtonGroup my_group = new ButtonGroup();
  
  /**
   * Construct the menu bar.
   * 
   * @param the_panel the Panel which will contain things from the menu bar.
   * @param the_game the Board of the game.
   */
  public TetrisMenuBar(final BoardPanel the_panel, final Board the_game) 
  {
    super();
    my_board_panel = the_panel;
    my_game = the_game;
    startMenu();
  }
  
  /**
   * The method that sets up the mnemonics,
   * and creates all of the buttons and action listeners.
   */
  private void startMenu()
  { 
    //adds the new game button
    my_file_menu.add(my_newgame_button);
    my_file_menu.addSeparator();
    
    // setup the Exit button
    my_file_menu.add(my_quit_button);
    
    //creates the instructions button
    createHelpMenuButtonInstructions();
    
    //adds the grid button in options menu
    my_options_menu.add(my_grid_button);
    
    //adds the difficulty level in options menu
    createOptionsMenuButtonDifficulty();
    
    //makes the grid button not focusable
    my_grid_button.setFocusable(false);
    
    //makes the grid button work
    setButtonGrid();
    
    //makes the quit button work
    setButtonQuit();
    
    //makes the new game button work
    setButtonNewGame();
    
    //makes the easy button work
    setButtonEasy();
    
    //makes the normal button work
    setButtonNormal();
    
    //makes the hard button work
    setButtonHard();

    //adds all of the menu bar items
    add(my_file_menu);
    add(my_options_menu);
    add(my_help_menu);
  }

  
  /**
   * This method creates the "Instructions" button under the "Help" menu
   * and gives it an action listener to show a GUI.
   */
  private void createHelpMenuButtonInstructions()
  {
    final JMenuItem instr_button = new JMenuItem("About...");
    instr_button.setMnemonic(KeyEvent.VK_I);
    my_help_menu.add(instr_button);
    instr_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        final JFrame message_frame = new JFrame(HELP);
        final JPanel background = new JPanel(new BorderLayout());
        final JLabel instructions = new JLabel("<html>The score increments by 42 each time " 
            + "you clear a line<br>"
            + "The level increments by 1 everytime you clear 10 lines<br><br>"
            + "For any questions please contact:<br>"
            + "aprokopchik93@gmail.com<br>" 
            + "Thank you and enjoy the game!<html>", SwingConstants.CENTER);
        background.setBackground(Color.BLACK);
        message_frame.add(background, BorderLayout.CENTER);
        instructions.setFont(new Font("Arial Black", Font.PLAIN, INSTR_SIZE));
        instructions.setForeground(Color.YELLOW);
        message_frame.setVisible(true);
        message_frame.setSize(ABOUT_WIDTH, ABOUT_HEIGHT);
        background.add(instructions); 
      }
    });
  }
  
  /**
   * Allows the grid to show up when button is pressed.
   */
  private void setButtonGrid()
  {   
    my_grid_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        if (my_grid_button.isSelected())
        {
          my_board_panel.setDrawGrid(true);
        }
        else 
        {
          my_board_panel.setDrawGrid(false);
        }
        my_board_panel.repaint();
      }
    }); 
  }
  
  /**
   * Allows to quit when button is pressed.
   */
  private void setButtonQuit()
  {   
    my_quit_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        my_board_panel.setQuit(true);
        my_board_panel.repaint();
      }
      
    }); 
  }
  
  /**
   * Allows to create a new game when button is pressed.
   */
  private void setButtonNewGame()
  {   
    my_newgame_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        my_game.newGame(my_game.getWidth(), my_game.getHeight(), null);
        my_board_panel.setNewGame(true);
      }
    }); 
  }
  
  /**
   * Creates the difficulty radio buttons (1, 2, and 4).
   */
  private void createOptionsMenuButtonDifficulty()
  {
    final JMenu difficulty_button = new JMenu("Difficulty");
    my_options_menu.add(difficulty_button);
    difficulty_button.setMnemonic(KeyEvent.VK_D);
     
    final String[] names = {"Easy", "Normal", "Hard"}; 
    for (int i = 0; i < my_diff_buttons.length; i++) 
    {  
      my_diff_buttons[i] = new JRadioButtonMenuItem(names[i]); 
      difficulty_button.add(my_diff_buttons[i]);
      my_group.add(my_diff_buttons[i]);
    } 
    my_diff_buttons[1].setSelected(true);
    my_diff_buttons[0].setMnemonic(KeyEvent.VK_E);
    my_diff_buttons[1].setMnemonic(KeyEvent.VK_N);
    my_diff_buttons[2].setMnemonic(KeyEvent.VK_H);
  }
  
  /**
   * Allows to create an easy game when button is pressed.
   */
  private void setButtonEasy()
  {   
    my_diff_buttons[0].addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        my_board_panel.setDifficulty(true, false, false);
      }
    }); 
  }
  
  /**
   * Allows to create an normal game when button is pressed.
   */
  private void setButtonNormal()
  {   
    my_diff_buttons[1].addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        my_board_panel.setDifficulty(false, true, false);
      }
    }); 
  }
  
  /**
   * Allows to create an hard game when button is pressed.
   */
  private void setButtonHard()
  {   
    my_diff_buttons[2].addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) 
      {
        my_board_panel.setDifficulty(false, false, true);
      }
    }); 
  }
  

}