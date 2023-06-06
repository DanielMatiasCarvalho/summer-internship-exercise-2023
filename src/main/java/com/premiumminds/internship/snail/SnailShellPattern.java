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

/**
 * Created by DanielMatiasCarvalho on 02-06-2023.
 */
class SnailShellSearch implements Callable<int[]> {

  int[][] matrix; //The matrix with the values
  int[] snail_shell;  //The array with the values, from the outer objects to the inner objects
  int number_of_values; //Total number of values

    public SnailShellSearch(int[][] matrix_original) {

      matrix=matrix_original;
      number_of_values = matrix.length * matrix.length;
      snail_shell= new int[number_of_values];
    }

    @Override
    public int[] call() {
      int length_matrix=matrix.length; //Number of lines in the matrix
      if (matrix[0].length==0) { // If the matrix is empty
        int[] response = new int[0];
        return response;
      } else if (matrix[0].length==1){ // If the matrix as only an element
        snail_shell[0]=matrix[0][0];
        return snail_shell;
      };

      int i = 0; //Defines the number of values the program has been through
      int x=0, y=0; //Current position
      int flag=0;  //Flag if we have reached the end of the matriz;
  
      /*There are four types of movements, right, bottom, left and up. 
       * These variables define the limits of the amount os values 
       * acessed through each type of movement
       */
      int right_limit, bottom_limit, left_limit, up_limit;
  
      // Each of these types of movements, at the beginning, goes until the limit of the matrix
      right_limit=bottom_limit=left_limit=length_matrix;
  
      up_limit=length_matrix-1; //At the beginning, acess all the lines in this movement, except the first
  
      while (i < number_of_values){
        for (int j=0; j <= right_limit; j++){
          snail_shell[i++]=matrix[x][y++];
          if(i >= number_of_values) {
            flag=1;
            break;
          }
        }

        if (flag==1) break;

        x++;
        y--;
        right_limit--;
  
        for (int j=1; j <= bottom_limit; j++){
          snail_shell[i++]=matrix[x++][y];
          if(i >= number_of_values) {
            flag=1;
            break;
          }
        }
        
        if (flag==1) break;
        y--;
        bottom_limit--;
  
        for (int j=1; j <= left_limit; j++) {
          snail_shell[i++]=matrix[x][y--];
          if(i >= number_of_values) {
            flag=1;
            break;
          }
        }
        
        if (flag==1) break;
        x--;
        left_limit--;
  
        for (int j=1; j <= up_limit; j++) {
          snail_shell[i++]=matrix[x--][y];
          if(i >= number_of_values) {
            flag=1;
            break;
          }
        }
        
        if (flag==1) break;
        y++;
        up_limit--;
      }

      return snail_shell;
    }
}
