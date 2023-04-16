package tools;

public class Vector {
    public double[] list;
    public Vector(double ... list){
        this.list = list;
    }
    public static Vector getUnitVector(double ... list){
        double[] ret = list;
        double length = 0;
        for(int i = 0; i < list.length; i++){
            length += list[i]*list[i];
        }
        length = Math.sqrt(length);
        for(int i = 0; i < list.length; i++){
            ret[i] *= (1/length);
        }
        return new Vector(ret);
    }
    public static Vector getUnitVector(Vector vector){
        double[] ret = vector.list.clone();
        double length = vector.length();
        for(int i = 0; i < vector.list.length; i++){
            ret[i] *= (1/length);
        }
        return new Vector(ret);
    }
    public static void scaleVector(Vector vector, double sc){
        for(int i = 0; i < vector.list.length; i++){
            vector.list[i] *= sc;
        }
    }
    public double length(){
        double length = 0;
        for(int i = 0; i < list.length; i++){
            length += list[i]*list[i];
        }
        length = Math.sqrt(length);
        return length;
    }
    public static void main(String[] args){
        Vector v1 = new Vector(1,3,5);
    }
}
