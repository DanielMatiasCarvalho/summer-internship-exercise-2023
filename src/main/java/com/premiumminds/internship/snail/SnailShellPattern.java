package com.premiumminds.internship.snail;

import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  private ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    SnailShellSearch search = new SnailShellSearch(matrix);
    return executor.submit(search);
  };

}

class SnailShellSearch implements Callable<int[]> {

  int[][] matrix;
  int[] snail_shell;
  int number_of_values;

    public SnailShellSearch(int[][] matrix_original) {

      matrix=matrix_original;
      number_of_values = matrix.length * matrix.length;
      snail_shell= new int[number_of_values];
    }

    @Override
    public int[] call() {
      int length_matrix=matrix.length;
      int i = 0; //Defines the number of values the program has been through
      int x=0, y=0; //Current position
  
      /*There are four types of movements, right, bottom, left and up. 
       * These variables define the limits of the amount os values 
       * acessed through each type of movement
       */
      int right_limit, bottom_limit, left_limit, up_limit;
  
      // Each of these types of movements, at the beginning, goes until the limit of the matrix
      right_limit=bottom_limit=left_limit=length_matrix;
  
      up_limit=length_matrix-1; //At the beginning, acess all the lines in this movement, except the first
  
      while (i < number_of_values){
        for (int j=0; j < right_limit; j++){
          snail_shell[i++]=matrix[x][y++];
        }
  
        x++;
        right_limit--;
  
        for (int j=0; j < bottom_limit; j++){
          snail_shell[i++]=matrix[x++][y];
        }
  
        y--;
        bottom_limit--;
  
        for (int j=0; j < left_limit; j++) {
          snail_shell[i++]=matrix[x][y--];
        }
  
        x--;
        left_limit--;
  
        for (int j=0; j < up_limit; j++) {
          snail_shell[i++]=matrix[x--][y];
        }
        
        y++;
        up_limit--;
      }

      return snail_shell;
    }
}
