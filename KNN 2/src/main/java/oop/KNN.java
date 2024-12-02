package oop;

import java.util.Arrays;
import java.util.Comparator;


/**
 * Nous sommes intéressés à prédire le succès d'un étudiant à
 * l'examen LEPL1402 en fonction des notes qu'il ou elle a obtenues
 * aux exercices inginious pendant le quadrimestre.
 * Pour faire cette prédiction, nous utiliserons les données collectées
 * au cours des années passées pour les anciens étudiants. Ces données
 * historiques contiennent pour chaque étudiant :
 * - les notes (sur 100) obtenues pour chaque exercice inginious.
 * - si l'étudiant a réussi (true) l'examen ou non (false)
 *
 * L'algorithme de prédiction que vous devez écrire (méthode predictSuccess)
 * prend les notes d'exercice du étudiant et doit faire un "vote
 * majoritaire" parmi les k anciens étudiants ayant le profil de notes
 * le plus similaire à celui de cet étudiant.
 * La similarité entre les notes de deux étudiants est calculée avec
 * la méthode EuclideanDistance fournie (plus la distance est petite,
 * plus les deux étudiants sont similaires).
 * Si le nombre de réussites est supérieur (strictement) à k/2 parmi les
 * k étudiants les plus similaires, l'algorithme prédit le succès (vrai),
 * sinon il prédit l'échec (faux).
 *
 *
 * Voici un exemple où nous avons les données de six anciens étudiants pour
 * deux exercices inginious:
 *
 *   KNN.Student[] étudiants = new KNN.Student[] {
 *     new KNN.Student(new double[]{90,91}, true),  // étudiant_0
 *     new KNN.Student(new double[]{80,75}, true),  // étudiant_1
 *     new KNN.Student(new double[]{70,65}, false), // étudiant_2
 *     new KNN.Student(new double[]{30,40}, true),  // étudiant_3
 *     new KNN.Student(new double[]{20,30}, false), // étudiant_4
 *     new KNN.Student(new double[]{45,33}, false), // étudiant_5
 *   };
 *
 * Essayons de prédire le succès d'un nouvel étudiant X qui a
 * obtenu les notes [88,95] aux exercices:
 * Si nous calculons la différence entre cet étudiant et les
 * six anciens, nous pouvons voir que les k=3 étudiants les plus
 * similaires sont l'étudiant_0, l'étudiant_1 et l'étudiant_2.
 * Nous pouvons également voir que l'étudiant_0 et l'étudiant_1
 * ont réussi et que l'étudiant_2 a échoué à l'examen.
 * Cela signifie que nous avons 2 x true et 1 x false (majorité true),
 * donc la méthode devrait prédire une réussite pour l'étudiant X:
 *
 *   double [] goodResults = new double[]{88,95} ;
 *   assertTrue(KNN.predictSuccess(students,goodResults,3)) ;
 *
 * Votre implémentation de la predictSuccess doit s'exécuter en O(n.log(n))
 * où n est le nombre de données historiques.
 * Notez que c'est la complexité temporelle du meilleur algorithme
 * de tri disponible dans l'API Java.
 * Ne réinventez donc pas l'eau chaude et n'hésitez pas à les utiliser.
 */

public class KNN {

    static class Student {

        final double [] grades; // grades[i] = inginious grades obtained by this student for exercise i
        final boolean success;  // true if the student succeeded at the exam, false otherwise

        public Student(double [] grades, boolean success) {
            this.grades = grades;
            this.success = success;
        }
    }

    static class Resultat {
        final double distanceeucli;
        final boolean succes;

        public Resultat(double distanceeucli, boolean succes) {
            this.distanceeucli = distanceeucli;
            this.succes = succes;
        }

        @Override
        public String toString() {
            return "Resultat{distanceeucli='" + distanceeucli + "', bool=" + succes + "}";
        }
    }

    public static boolean predictSuccess(Student [] students, double[] grades, int k) {
        Resultat [] resultats = new Resultat[students.length];

        for(int i=0; i<students.length; i++){
            resultats[i] = new Resultat(euclideanDistance(students[i].grades, grades), students[i].success);
        }
        Arrays.sort(resultats, Comparator.comparingDouble(resultat -> resultat.distanceeucli));
        int count = 0 ;
        for(int j=0 ; j<k ; j++){
            if(resultats[j].succes == true){
                count+=1;
            }
        }
        boolean succeed = false ;
        if(count > (k/2) ){
            succeed = true ;
        }
        return succeed;
    }



    public static double euclideanDistance(double[] a, double[] b) {
        double dist = 0;
        for (int i = 0; i < a.length; i++) {
            dist += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(dist);
    }


}