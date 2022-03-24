package main;

public class Matrix {

    private  double[][] arr;
    /**
     * number of rows
     */
    private int _rows;
    /**
     * number of columns
     */
    private int _columns;

    /**
     * creates zero matrix from entered dimensions
     * @param columns number of columns
     * @param rows number of rows
     */
    public Matrix(int columns,int rows) {
        if (columns <= 0 || rows<=0){
            throw new IndexOutOfBoundsException("column and rows must be positive");
        }
        arr=new double[columns][rows];
        _columns=columns;
        _rows=rows;
    }

    /**
     * create a matrix from entered array
     * @param array  incoming array
     */
    public Matrix(double array[][]){
        if(array==null){
            throw new NullPointerException("Array must not be null");
        }
        arr=array;
        _columns=array.length;
        _rows=array[0].length;
    }

    /**
     * gets number of columns
     * @return number of columns
     */
    public int get_columns(){
        return _columns;
    }
    /**
     * gets number of rows
     * @return number of rows
     */
    public int get_rows(){
        return _rows;
    }

    /**
     * gets special row from array
     * @param row_index number of special row
     * @return array of special row
     */
    public double[] get_row(int row_index) {
        if(row_index>_rows || row_index<=0 )
            throw new IndexOutOfBoundsException("index of row out of range ");
        else return arr[row_index-1];
    }

    /**
     * gets special column from array
     * @param column_index number of special column
     * @return array of special column
     */
    public double[] get_column(int column_index) {
        if (column_index > _rows || column_index <= 0)
            throw new IndexOutOfBoundsException("index of column out of range ");
        else {
            var column=new double[_rows];
            for (int i=0;i<_rows;i++){
                column[i]=arr[i][column_index-1];
        }
            return  column;
    }
    }

    /**
     *gets a submatrix from matrix  by deleting specified row and column
     * @param _matrix
     * @param row
     * @param column
     * @return submatrix
     */
    public  Matrix submatrix(Matrix _matrix,int row,int column) {
        Matrix matrix = new Matrix(_columns - 1, _rows - 1);
        for (int i = 0; i < _matrix._columns; i++) {
            if (i != column - 1)
                for (int j = 0; j < _matrix._rows; j++) {
                    if (j != row - 1)
                        matrix.arr[i][j] = _matrix.arr[i][j];

                }
        }
        return matrix;
    }

    /**
     * calculate determinant from matrix
     * @param matrix
     * @return determinant
     */
    public double determinant(Matrix matrix){
        if(_columns!=_rows){
            throw new IllegalArgumentException("Matrix is not square");
        }
        if(_columns==2)
            return matrix.arr[0][0]*matrix.arr[1][1]-matrix.arr[0][1]*matrix.arr[1][0];
        double determinant=0;
        for (int i=0;i<_columns;i++)
            determinant+=Math.pow(-1,i)*matrix.arr[0][i]*determinant(submatrix(matrix,0,i));
        return determinant;
    }
    public Matrix reversal() {
        Matrix matrix_inverse=new Matrix(_columns, _rows);
        for (int i=0;i< matrix_inverse._rows;i++){
            for (int j=0;j< matrix_inverse._columns;j++)
                matrix_inverse.arr[i][j]=Math.pow(-1,i+j)*determinant(submatrix(this,i,j));
        }
        double determinant=1.0/determinant(this);
        for (int i = 0; i < matrix_inverse._columns; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = matrix_inverse.arr[i][j];
                matrix_inverse.arr[i][j] = matrix_inverse.arr[j][i] * determinant;
                matrix_inverse.arr[j][i] = temp * determinant;
            }
        }
        arr=matrix_inverse.arr;
        return matrix_inverse;
    }

    /**
     * gets wrapped array from matrix
     * @return array two dimensional
     */
    public double[][] getarr(){
        return arr;
    }
    public Matrix trans()  {
        Matrix matrix=new Matrix(_columns,_rows);
        for(int i=0;i<_rows;i++){
            for(int j=0;j<_columns;j++) {
                matrix.arr[i][j] = arr[j][i];
            }
        }
        arr=matrix.arr;
        return matrix;
    }

    /**
     * adding operation with two matrixes
     * @param matrix second matrix that will be added
     */
    public void add(Matrix matrix){
        if(matrix==null)
            throw new NullPointerException("Matrix is null");
        if(_rows!=matrix._rows && _columns!=matrix._columns)
            throw new IllegalArgumentException("Cannot add matrixes of different dimensions");
        for(int i=0;i<_rows;i++){
            for(int j=0;j<_columns;j++)
                arr[i][j]+=matrix.arr[i][j];
        }
    }

    /**
     * adding operation with value
     * @param val value that will be added to matrix
     */
    public void add(double val){
        for(int i=0;i<_columns;i++){
            for(int j=0;j<_rows;j++)
                arr[i][j]+=val;
        }
    }

    /**
     * multiply operation with value
     * @param val value that will be multiplied to matrix
     */
    public void multiply(double val){
        for(int i=0;i<_columns;i++){
            for(int j=0;j<_rows;j++)
                arr[i][j]*=val;
        }
    }

    /**
     * multiply operation with two matrixes
     * @param matrix matrix that is multiplied by
     * @return result matrix
     */
    public Matrix multiply(Matrix matrix){
        if ( matrix==null)
            throw new NullPointerException("Cannot multiply with null matrix");
        if(_columns!=matrix._rows)
            throw new IllegalArgumentException( "Cannot multiply those matrixes");
        Matrix result=new Matrix(_columns,matrix._rows);
        for (int row = 0; row < result._columns; row++) {
            for (int col = 0; col < result._rows; col++) {
                result.arr[row][col] = MultiCell(arr, matrix.arr, row, col);
            }
        }
        arr=result.arr;
        return result;
    }

    /**
     * function returns value from multiplying row and column
     * @param arr1 first arr
     * @param arr2 second arr
     * @param row row to multiply
     * @param col column to multuply
     * @return
     */
    public double MultiCell(double[][] arr1,double[][] arr2,int row,int col){
        double cell=0;
        for(int i=0;i<arr2.length;i++){
            cell+=arr1[row][i]*arr2[i][col];
        }
        return cell;
    }
}
