package tests;

import static org.junit.Assert.*;
import java.util.Arrays;
import main.Matrix;
import org.junit.Test;
public class MatrixTest {
    @Test
    public void createvalidarray(){
        double[][] array=new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                array[i][j] = i * 2 / 4;
            }
        }
        Matrix matrix=new Matrix((array));
        assertTrue(Arrays.deepEquals(array,matrix.getarr()));
    }
    @Test
    public void failtocreatevalidsize(){
        int row=-1;
        int column=2;
        assertThrows(IndexOutOfBoundsException.class,()->new Matrix(row,column));
    }
    @Test
    public void failtocreateonnullpointerarray(){
        double [][] array=null;
        assertThrows(NullPointerException.class,()->new Matrix(array));
    }
    @Test
    public void getvalidrow(){
        double[][] array=new double[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
               array[i][j]=i*2/5;
            }
        }
        double[] row=array[0];
        Matrix matrix=new Matrix(array);
        assertTrue(Arrays.equals(row,matrix.get_row(1)));
    }
    @Test
    public void getvalidcolumn(){
        double[][] array=new double[3][3];
        double[] col=new double[3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                array[i][j]=i+2/5;
            }
            col[i]=array[i][0];
        }
        Matrix matrix=new Matrix(array);
        assertTrue(Arrays.equals(col,matrix.get_column(1)));
    }
    @Test
    public void validsubmatrix(){
        double [][] array={{3,4},{6,7}};
        double [][] finalsubarray={{3}};
        Matrix matrix=new Matrix(array);
        Matrix submatrix=new Matrix(finalsubarray);
        var finalmatrix=matrix.submatrix(matrix,2,2);
        assertTrue(Arrays.deepEquals(finalmatrix.getarr(),finalsubarray));
    }
    @Test
    public void validtrans(){
        double [][] array={{2,4},{7,8}};
        double [][] transarray={{2,7},{4,8}};
        Matrix matrix=new Matrix(array);
        matrix.trans();
        assertTrue(Arrays.deepEquals(transarray,matrix.getarr()));
    }
    @Test
    public void determinantmatrix(){
        double [][] array={{3,4},{6,7}};
        Matrix matrix=new Matrix(array);
        double expdet=-3;
        assertEquals(expdet,matrix.determinant(matrix),0.1);
    }
    @Test
    public void addNullpointerMatrixNull(){
        double [][] array={{1,2},{3,4}};
        Matrix matrix =new Matrix(array);
        Matrix matrix2=null;
        assertThrows(NullPointerException.class,()->matrix.add(matrix2));
    }
    @Test
    public void addNumber() {
        double[][] array = {{1, 2}, {3, 4}};
        Matrix matrix = new Matrix(array);
        double val = 5;
        double [][] exparray={{6,7},{8,9}};
        matrix.add(val);
        assertTrue(Arrays.deepEquals(exparray,matrix.getarr()));
    }
    @Test
    public void addMatrix(){
        double[][] array = {{1, 2}, {3, 4}};
        Matrix matrix = new Matrix(array);
        double[][] array2={{4,4},{2,3}};
        Matrix matrix2=new Matrix(array2);
        double [][] exparray={{5,6},{5,7}};
        matrix.add(matrix2);
        assertTrue(Arrays.deepEquals(exparray,matrix.getarr()));
    }
 @Test
    public void multinumber(){
     double[][] array = {{1, 2}, {3, 4}};
     Matrix matrix = new Matrix(array);
     double val = 5;
     double [][] exparray={{5,10},{15,20}};
     matrix.multiply(val);
     assertTrue(Arrays.deepEquals(exparray,matrix.getarr()));
 }
 @Test
 public void multi_illegalexceptionwhensizearenotequal(){
     double[][] array = {{1, 2,3}, {3, 4,6},{4,5,6}};
     Matrix matrix = new Matrix(array);
     double[][] array2={{4,4},{2,3}};
     Matrix matrix2=new Matrix(array2);
     assertThrows(IllegalArgumentException.class,()->matrix.multiply(matrix2));
 }
  @Test
    public void multi_whenmatrixisnull(){
      double[][] array = {{1, 2}, {3, 4}};
      Matrix matrix = new Matrix(array);
      Matrix matrix2=null;
      assertThrows(NullPointerException.class,()->matrix.multiply(matrix2));
  }
  @Test
    public void multi_twomatrixes(){
        double [][] array={{2,3},{4,1}};
        Matrix matrix=new Matrix(array);
        double [][] array2={{6,3},{2,1}};
        Matrix matrix2=new Matrix(array2);
        double [][] exparray={{18,9},{26,13}};
        matrix.multiply(matrix2);
        assertTrue(Arrays.deepEquals(exparray,matrix.getarr()));
  }
}
