import java.util.*;

public class calculator {
   public static void main(String[] args) {
      System.out.println("Welcome to the Linear Algebra Calculator\n");

      acceptInput();

      System.out.println("\nThank you for using the Linear Algebra Calculator\n");
      return;
   }

   public static void acceptInput() {
      System.out.println("What would you like to compute?");

      // print all options to compute
      System.out.println("The options are:");
      System.out.println("1 - Matrix Addition");
      System.out.println("2 - Scalar Multiplication");
      System.out.println("3 - Transpose");
      System.out.println("4 - Matrix Multiplication");
      System.out.println("5 - Vector Rotation");
      System.out.println("6 - Gaussian Elimination");

      // read in input
      System.out.print("\nPlease enter the digit of the corresponding operation: ");
      int input = IO.readInt();

      // check for input validity
      while (input < 1 | input > 6) {
         IO.reportBadInput();
         System.out.print("Please enter the digit of the corresponding operation again: ");
			input = IO.readInt();
      }

      switch (input) {
         case 1:
            matrixAdd();
            break;
         case 2:
            scalarMult();
            break;
         case 3:
            transpose();
            break;
         case 4:
            matrixMult();
            break;
         case 5:
            vectorRotation();
            break;
         case 6:
            gausElim();
            break;
      }

      System.out.print("Would you like to compute another value? ");
      String in = IO.readString();

      while (!in.equals("yes") && !in.equals("Yes") && !in.equals("no") && !in.equals("No")) {
         IO.reportBadInput();
         System.out.print("Would you like to compute another value? ");
         in = IO.readString();
      }

      if (in.equals("yes") || in.equals("Yes")) {
         acceptInput();
      }

      return;
   }

   // 1 - Matrix Addition
   public static void matrixAdd() {
      // read input for matrix A
      System.out.print("Enter the number of rows in matrix A: ");
      int rowsA = IO.readInt();

      System.out.print("Enter the number of columns in matrix A: ");
      int colsA = IO.readInt();

      double[][] matrixA = readMatrix(rowsA, colsA);

      // read input for matrix B
      System.out.print("Enter the number of rows in matrix B: ");
      int rowsB = IO.readInt();

      if (rowsB != rowsA) {
         System.out.println("Matrix addition not possible");
         return;
      }

      System.out.print("Enter the number of columns in matrix B: ");
      int colsB = IO.readInt();

      if (colsB != colsA) {
         System.out.println("Matrix addition not possible");
         return;
      }

      double[][] matrixB = readMatrix(rowsB, colsB);

      // create matrixC
      int rowsC = rowsA;
      int colsC = colsA;

      double[][] matrixC = new double[rowsC][colsC];

      // fill values in matrixC
      for (int i = 0; i < rowsC; i++) {
         for (int j = 0; j < colsC; j++) {
            matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
         }
      }

      // print the final matrix
      System.out.println("\nThe final matrix is:");
      printMatrix(matrixC);
   }

   // 2 - Scalar Multiplication
   public static void scalarMult() {
      System.out.print("Enter the number of rows: ");
      int rows = IO.readInt();

      System.out.print("Enter the number of columns: ");
      int cols = IO.readInt();

      double[][] matrix = readMatrix(rows, cols);

      System.out.print("Enter the scalar: ");
      double scalar = IO.readDouble();

      // compute the scalar multiplication
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            matrix[i][j] *= scalar;
         }
      }

      // print the final matrix
      System.out.println("\nThe final matrix is:");
      printMatrix(matrix);
   }

   // 3 - Transpose
   public static void transpose() {
      System.out.print("Enter the number of rows: ");
      int rows = IO.readInt();

      System.out.print("Enter the number of columns: ");
      int cols = IO.readInt();

      double[][] matrix = readMatrix(rows, cols);

      int rowsT = cols;
      int colsT = rows;

      double[][] matrixT = new double[rowsT][colsT];

      // fills in the values for matrixT
      for (int i = 0; i < rowsT; i++) {
         for (int j = 0; j < colsT; j++) {
            matrixT[i][j] = matrix[j][i];
         }
      }

      // print the final matrix
      System.out.println("\nThe final matrix is:");
      printMatrix(matrixT);
   }

   // 4 - Matrix Multiplication
   public static void matrixMult() {
      // read input for matrix A
      System.out.print("Enter the number of rows in matrix A: ");
      int rowsA = IO.readInt();

      System.out.print("Enter the number of columns in matrix A: ");
      int colsA = IO.readInt();

      double[][] matrixA = readMatrix(rowsA, colsA);

      // read input for matrix B
      System.out.print("Enter the number of rows in matrix B: ");
      int rowsB = IO.readInt();

      System.out.print("Enter the number of columns in matrix B: ");
      int colsB = IO.readInt();

      if (rowsB != colsA) {
         System.out.println("Matrix multiplication not possible");
         return;
      }

      double[][] matrixB = readMatrix(rowsB, colsB);

      // multiply matrices
      int rowsC = rowsA;
      int colsC = colsB;

      double[][] matrixC = new double[rowsC][colsC];

      for (int i = 0; i < rowsC; i++) {
         for (int j = 0; j < colsC; j++) {
            for (int k = 0; k < rowsB; k++) {
               matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
            }
         }
      }

      // print the final matrix
      System.out.println("\nThe final matrix is:");
      printMatrix(matrixC);
   }

   // 5 - Vector Rotation
   public static void vectorRotation() {
      double[][] vector = new double[2][1];

      // read in the inputs for the vector
      System.out.print("Please enter the X Value: ");
      vector[0][0] = IO.readDouble();

      System.out.print("Please enter the Y Value: ");
      vector[1][0] = IO.readDouble();

      // read in input for the angle of rotation
      System.out.print("Please enter the angle of rotation in degrees: ");
      double theta = IO.readDouble();
      theta = Math.toRadians(theta);

      // create rotation matrix
      double[][] rotMatrix = new double[2][2];
      rotMatrix[0][0] = Math.cos(theta);
      rotMatrix[0][1] = 0 - Math.sin(theta);
      rotMatrix[1][0] = Math.sin(theta);
      rotMatrix[1][1] = Math.cos(theta);

      double[][] rotVector = matrixMult(rotMatrix, vector);

      // print the final vector
      System.out.println("\nThe final vector is:");
      printMatrix(rotVector);
   }

   // 6 - Gaussian Elimination
   public static void gausElim() {
      // create matrix
      System.out.print("Enter the number of rows: ");
      int rows = IO.readInt();

      System.out.print("Enter the number of columns: ");
      int cols = IO.readInt();

      double[][] matrix = readMatrix(rows, cols);

      // ask for row echelon form or reduced row echelon form
      System.out.println("Would you like to compute the row echelon form or reduced row echelon form?");
      System.out.println("1 - Row Echelon Form");
      System.out.println("2 - Reduced Row Echelon Form");
      System.out.print("Please enter the digit of the corresponding operation: ");
      int form = IO.readInt();

      while (form < 1 || form > 2) {
         IO.reportBadInput();
         System.out.print("Please enter the digit of the corresponding operation again: ");
			form = IO.readInt();
      }

      // down algorithm
      
   }

   // reads in the individual values of a matrix and returns the matrix
   public static double[][] readMatrix(int rows, int cols) {
      // create array of proper size
      double[][] matrix = new double[rows][cols];

      // read each value of the matrix and store in 2D array
      System.out.println("Please enter each value of the matrix from left to right.");
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            System.out.print("Please enter element (" + i + ", " + j + "): ");
            matrix[i][j] = IO.readDouble();
         }
      }

      return matrix;
   }

   // multiplies two given matricies
   public static double[][] matrixMult(double[][] matrixA, double[][] matrixB) {
      int rowsA = matrixA.length;
      int colsA = matrixA[0].length;

      int rowsB = matrixB.length;
      int colsB = matrixB[0].length;

      if (rowsB != colsA) {
         System.out.println("Matrix multiplication not possible");
         return null;
      }

      // multiply matrices
      int rowsC = rowsA;
      int colsC = colsB;

      double[][] matrixC = new double[rowsC][colsC];

      for (int i = 0; i < rowsC; i++) {
         for (int j = 0; j < colsC; j++) {
            for (int k = 0; k < rowsB; k++) {
               matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
            }
         }
      }

      return matrixC;
   }

   // prints a matrix
   public static void printMatrix(double[][] matrix) {
      int rows = matrix.length;
      int cols = matrix[0].length;

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            System.out.print(matrix[i][j] + "\t");
         }

         System.out.println("");
      }

      System.out.println("");

      return;
   }
}
