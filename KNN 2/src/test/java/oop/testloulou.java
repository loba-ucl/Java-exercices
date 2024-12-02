package oop;

import java.util.Arrays;

public class testloulou {
    public static void main(String[] args){
        KNN.Student[] students = new KNN.Student[] {
                new KNN.Student(new double[]{90,91}, true),
                new KNN.Student(new double[]{80,75}, true),
                new KNN.Student(new double[]{70,65}, false), // surprisingly, this student has failed despite a good preparation
                new KNN.Student(new double[]{30,40}, true),  // surprisingly, this student has succeeded despite a poor preparation
                new KNN.Student(new double[]{20,30}, false),
                new KNN.Student(new double[]{45,33}, false),
        };

        double [] goodResults = new double[]{88,95};
        //KNN.Resultat [] result = KNN.predictSuccess(students , goodResults , 3);
        //for ( Resultat person : result ) {
       //     System.out.println(person);
        //}
        //System.out.println("le nouvelle array est : " + Arrays.toString(result));

    }
}
