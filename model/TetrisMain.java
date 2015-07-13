package model;

import view.TetrisGUI;

/*
 * Alex Prokopchik
 * May 11, 2013
 * TetrisMain.java
 */

/**
 * Runs Tetris by instantiating and starting the TetrisGUI.
 * 
 * @version 1.0
 * @author alexpro
 */
public final class TetrisMain
{

  /**
   * Private constructor, to prevent instantiation of this class.
   */
  private TetrisMain()
  {
    throw new IllegalStateException();
  }
  



  /**
   * The main method, invokes the TetrisGUIs start method. Command line arguments are
   * ignored.
   * 
   * @param the_args Command line arguments.
   */
  public static void main(final String[] the_args)
  {
    final TetrisGUI gui = new TetrisGUI();
    gui.start();
  }
}