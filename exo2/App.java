package exo2;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class App<T> {
    //Question 1 : définition de prédicats
    Predicate<Paire<Integer, Double>> ttP = p -> p.fst < 100; // appel à la paire avec la fonction fst 
    Predicate<Paire<Integer, Double>> ttG = p -> p.fst > 200; // appel à la paire avec la fonction fst 
    Predicate<Paire<Integer, Double>> tIncorrecte = p -> ttP.test(p) || ttG.test(p);
    Predicate<Paire<Integer, Double>> tCorrecte = Predicate.not(tIncorrecte);
    Predicate<Paire<Integer, Double>> ptL = p -> p.snd > 150.0;
    Predicate<Paire<Integer, Double>> pCorrect = Predicate.not(ptL);
    Predicate<Paire<Integer, Double>>  aAutorisé= p -> tCorrecte.test(p) && pCorrect.test(p);

    //Question 2 : Création de la méthode  qui renvoi la liste des éléments qui vérifient la conjonction des prédicat
    public <T> List<T> filtragePredicatif(List<Predicate<T>> prd, List<T> es){

        List<T> result = new ArrayList<>();

        Predicate<T> predicat = x -> true;

        for(Predicate<T> p : prd){
            predicat = predicat.and(p);
        }
        for(T e : es) {
            if (predicat.test(e)){
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        App app = new App<>();
        //Création de tests au bornes ( taille trop grande/petite, poids: trop lourd, taille/poids: parfait/incorrect)
        Paire Petit = new Paire(90, 80.0); 
        Paire Grand = new Paire(220, 80.0); 
        Paire Parfait = new Paire(180, 95.0); 
        Paire Incorrect = new Paire(300, 300.0); 
        Paire Lourd = new Paire(150, 300.0); 
        Paire Leger = new Paire(150, 50.0); 
       
        System.out.println("Taille: 90 cm, poids: 80.0 kg-> ok+"+app.aAutorisé.test(Petit));
        System.out.println("Taille: 220 cm, poids: 100.0 kg-> ok+"+app.aAutorisé.test(Grand));
        System.out.println("Taille: 180 cm, poids: 95.0 kg-> ok" +app.aAutorisé.test(Parfait));
        System.out.println("Taille: 300 cm, poids: 300.0 kg-> ok+"+app.aAutorisé.test(Incorrect));
        System.out.println("Taille: 150 cm, poids: 300.0 kg-> ok+"+app.aAutorisé.test(Lourd));
        System.out.println("Taille: 150 cm, poids: 50.0 kg-> ok+"+app.aAutorisé.test(Leger));

    }
}
