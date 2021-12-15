import java.util.*;

public class Puissance {
    
    public static int[][] grille = new int[6][7];
    public static int jeu = 0;
    public static int mode = 1;

    // Description: Boucle qui initialise chaque ligne avec 7 valeurs à 0.
    public static void initialiseGrille() {
        for (int i = 0; i < 6; i++)
            grille[i] = new int[] {0, 0, 0, 0, 0, 0, 0};
    }

    // Paramètre <Joueur>, 1 = Joueur 1 & 2 = Joueur 2.
    // Paramètre <Colonne>, Valeur entre 0 et 6, correspond à la colonne ou le jeton va être inséré.
    // Description: On regarde où le jeton peut être inséré au plus bas de la grille (au plus haut dans la matrice). 
    public static void jouer(int joueur, int colonne) {
        for (int ligne = 0; ligne < 6; ligne++) {
            if (grille[ligne][colonne] == 0) {
                grille[ligne][colonne] = joueur;
                aGagne(joueur, ligne, colonne);
                break;
            }
        }
    }

    // Description: Affichage de la grille inversé sur l'axe Y par rapport à la matrice
    // Bonus: Couleur intégrée dans l'affichage avec:
    // Jaune: \u001B[33m
    // Blanc: \u001B[37m
    // Rouge: \u001B[31m
    public static void afficheGrille() {
        for (int ligne = 5; ligne >= 0; ligne--) {
            for (int colonne = 0; colonne < 7; colonne++) {
                if (grille[ligne][colonne] == 1)
                    System.out.print("|\u001B[33mX\u001B[37m");
                else if (grille[ligne][colonne] == 2)
                    System.out.print("|\u001B[31mO\u001B[37m");
                else 
                    System.out.print("| ");
                if (colonne == 6)
                    System.out.print("|");
            }
            System.out.println("");
        }
        for (int numero = 0; numero < 7; numero++)
            System.out.print(" " + numero);
        System.out.println();
    }

    // Description: En partant d'une coordonnée on va compter le nombre de case à droite et à gauche comportant un jeton du joueur courant. 
    // On regarde en premier la direction de droite. Si on sort de la grille ou qu'un jeton n'est pas celui du joueur courant on arrête la boucle et on passe sur le côté gauche et on fait la même chose.
    // On vérifie en amont que l'on ne sort pas de la matrice
    // Si la somme est égale à 4 cela signifie que 4 jetons sont alignés
    public static int aGagneHor(int joueur, int x, int y) {
        int countCase = 1;

        for (int i = 1; i < 4; i++) {
            if (((x + i) < 7) && (grille[y][x + i] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        for (int i = 1; i < 4; i++) {
            if (((x - i) >= 0)  && (grille[y][x - i] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        return (countCase >= 4) ? 1 : 0;
    }

    // Description: En partant d'une coordonnée on va compter le nombre de case en haut et en bas comportant un jeton du joueur courant. 
    // On regarde en premier la direction de haut. Si on sort de la grille ou qu'un jeton n'est pas celui du joueur courant on arrête la boucle et on passe sur la direction bas et on fait la même chose.
    // On vérifie en amont que l'on ne sort pas de la matrice
    // Si la somme est égale à 4 cela signifie que 4 jetons sont alignés
    public static int aGagneVer(int joueur, int x, int y) {
        int countCase = 1;

        for (int i = 1; i < 4; i++) {
            if ( ((y + i) < 6) && (grille[y + i][x] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        for (int i = 1; i < 4; i++) {
            if ( ((y - i) >= 0) && (grille[y - i][x] == joueur) )
                countCase = countCase + 1;
            else
                break;
        }
        return (countCase >= 4) ? 1 : 0;
    }

    // Description: En partant d'une coordonnée on va compter le nombre de case en diagonale en haut à droite et en diagonale en bas à gauche comportant un jeton du joueur courant. 
    // On regarde en premier la diagonale haut droite. Si on sort de la grille ou qu'un jeton n'est pas celui du joueur courant on arrête la boucle et on passe sur la diagonale bas gauche et on fait la même chose.
    // On vérifie en amont que l'on ne sort pas de la matrice
    // Si la somme est égale à 4 cela signifie que 4 jetons sont alignés
    public static int aGagneDiagMont(int joueur, int x, int y) {
        int countCase = 1;

        for (int i = 1; i < 4; i++) {
            if (((x + i) < 7 && (y + i) < 6) && (grille[y + i][x + i] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        for (int i = 1; i < 4; i++) {
            if (((x - i) >= 0 && (y - i) >= 0) && (grille[y - i][x - i] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        return (countCase >= 4) ? 1 : 0;
    }

    // Description: En partant d'une coordonnée on va compter le nombre de case en diagonale en haut à gauche et en diagonale en bas à droit comportant un jeton du joueur courant. 
    // On regarde en premier la diagonale haut gaucge. Si on sort de la grille ou qu'un jeton n'est pas celui du joueur courant on arrête la boucle et on passe sur la diagonale bas droit et on fait la même chose.
    // On vérifie en amont que l'on ne sort pas de la matrice
    // Si la somme est égale à 4 cela signifie que 4 jetons sont alignés
    public static int aGagneDiagDesc(int joueur, int x, int y) {
        int countCase = 1;

        for (int i = 1; i < 4; i++) {
            if (((x + i) < 7 && (y - i) >= 0) && (grille[y - i][x + i] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        for (int i = 1; i < 4; i++) {
            if (((x - i) >= 0 && (y + i) < 6) && (grille[y + i][x - i] == joueur))
                countCase = countCase + 1;
            else
                break;
        }
        return (countCase >= 4) ? 1 : 0;
    }

    // Description: On vérifie à chaque coup joué si le joueur courant à gagné. Si oui on arrête le jeu et on affiche le message de victoire.
    // Si le joueur courant n'a pas gagné. On regarde si le match est nul. 
    public static void aGagne(int joueur, int ligne, int colonne) {
        Boolean estGagne = false;

        if (aGagneHor(joueur, colonne, ligne) == 1 || aGagneVer(joueur, colonne, ligne ) == 1 || aGagneDiagMont(joueur, colonne, ligne) == 1 || aGagneDiagDesc(joueur, colonne, ligne) == 1 )
            estGagne = true;

        if (estGagne) {
            clearScreen();
            spashScreen();
            afficheGrille();
            System.out.println("Le joueur " + joueur + " a gagné !");
            jeu = 2;
            
        }
        else if (matchNul()) {
            clearScreen();
            spashScreen();
            afficheGrille();
            System.out.println("Match Nul !");
            jeu = 2;
        }
    }

    // Description: On vérifie si toutes les cases sont utilieés, dans le cas où on ne peut plus poser de jeton, le jeu s'arrête avec une égalité
    public static Boolean matchNul() {
        Boolean estNul = true;

        for (int ligne = 5; ligne >= 0; ligne--) {
            for (int colonne = 6; colonne >= 0; colonne--) {
                if (estNul && grille[ligne][colonne] == 0) {
                    estNul = false;
                    break;
                }
            }
        }
        return estNul;
    }

    // Description: Choisie une valeur aléatoire entre 0 et 6, pour le choix de la colonne et ajoute le jeton au plus bas de la grille
    public static void joueCoupRandom() {
        int colonne = (int)(Math.random() * 6) + 0;

        for (int ligne = 0; ligne < 6; ligne++) {
            if (grille[ligne][colonne] == 0) {
                jouer(2, colonne);
                break;
            }
        }
    }

    // Description: Pour chaque case on va regarder si l'IA joue un coup sur une case {X,Y} permet qu'elle gagne la partie
    // On utilise les méthodes aGagne<Dir> pour faire la vérification de victoire.
    // Si la case permet de la gagne on joue le coup sur la case. On regarde si la case contient bien un jeton en dessous de lui (au dessus dans la matrice)
    // Dans le cas contraire on retourne false et l'ia va jouer un coup aléatoire
    public static int joueCoupRandom2() {
        for (int ligne = 0; ligne < 6; ligne++) {
            for (int colonne = 0; colonne < 7; colonne++) {
                // C'est une casse jouable et que en dessous il y a un jeton
                if (grille[ligne][colonne] == 0 && (ligne == 0 || grille[ligne - 1][colonne] != 0)) {
                    grille[ligne][colonne] = 2;
                    if (aGagneHor(2, colonne, ligne) == 1 || aGagneVer(2, colonne, ligne) == 1 || aGagneDiagMont(2, colonne, ligne) == 1 || aGagneDiagDesc(2, colonne, ligne) == 1) {
                        grille[ligne][colonne] = 0;
                        jouer(2, colonne);
                        return 1;
                    }
                    grille[ligne][colonne] = 0;

                }
            }
        }
        return 0;
    }

    // Description: Pour chaque case on va regarder si le joueur joue un coup sur une case {X,Y} permet que le joueur gagne la partie
    // On utilise les méthodes aGagne<Dir> pour faire la vérification de victoire.
    // Si la case permet de la gagne on joue le coup sur la case. On regarde si la case contient bien un jeton en dessous de lui (au dessus dans la matrice)
    // Dans le cas contraire on retourne false et l'ia va jouer un coup aléatoire
    public static int joueCoupRandom3() {
        for (int ligne = 0; ligne < 6; ligne++) {
            for (int colonne = 0; colonne < 7; colonne++) {
                // C'est une casse jouable et que en dessous il y a un jeton
                if (grille[ligne][colonne] == 0 && (ligne == 0 || grille[ligne - 1][colonne] != 0)) {
                    grille[ligne][colonne] = 1;
                    if (aGagneHor(1, colonne, ligne) == 1 || aGagneVer(1, colonne, ligne) == 1 || aGagneDiagMont(1, colonne, ligne) == 1 || aGagneDiagDesc(1, colonne, ligne) == 1) {
                        grille[ligne][colonne] = 0;
                        jouer(2, colonne);
                        return 1;
                    }
                    grille[ligne][colonne] = 0;

                }
            }
        }
        return 0;
    }

    // Boucle du Jeu 1 vs 1
    public static void jeu() {
        int joueur = 1;

        initialiseGrille();

        while (jeu == 1) {
            clearScreen();
            spashScreen();
            afficheGrille();
            Scanner sc= new Scanner(System.in);
            System.out.println(""); 
            System.out.print("Quel coup pour le joueur " + joueur + " ? : ");
            int caseSelect = 0;

            try {
                caseSelect = sc.nextInt();
                jouer(joueur, caseSelect);
                joueur = (joueur == 1) ? 2 : 1;
            } catch(Exception e) {
                System.out.println("Erreur: Veuillez choisir un chiffre entre 0 et 6");
            }
        }
    }

    // Boucle du Jeu 1 IA
    public static void jeuIA() {
        int joueur = 1;

        initialiseGrille();

        while (jeu == 1) {
            clearScreen();
            spashScreen();
            afficheGrille();
            // Joueur
            if (joueur == 1) {
                Scanner sc= new Scanner(System.in);
                System.out.println(""); 
                System.out.print("Quel coup pour le joueur " + joueur + " ? : ");
                int caseSelect = 0;

                try {
                    caseSelect = sc.nextInt();
                    jouer(joueur, caseSelect);
                    joueur = (joueur == 1) ? 2 : 1;
                } catch(Exception e) {
                    System.out.println("Erreur: Veuillez choisir un chiffre entre 0 et 6");
                }
            // IA
            } else {
                int aGagne = 0;
                int aCoince = 0;
                System.out.println("Essaye de win");
                aGagne = joueCoupRandom2();
                if (aGagne == 0) {
                    System.out.println("Essaye de coincer");
                    aCoince = joueCoupRandom3();
                }
                if (aGagne == 0 && aCoince == 0) {
                System.out.println("Joue Random");
                    joueCoupRandom();
                }
                joueur = (joueur == 1) ? 2 : 1;
            }
        }


    }

    // Permet d'effacer l'écran pour ne pas avoir de défilement
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }    

    // Ecran de victoire
    public static void winScreen() {
        while (jeu == 2) {
            int caseSelectionne = 0;
            Scanner sc= new Scanner(System.in);
            System.out.print("Appuyez sur 0 pour retourner au Menu et sur un 1 pour recommancer: ");
            caseSelectionne = 0;
            
            try {
                caseSelectionne = sc.nextInt();
            } catch(Exception e) {
                System.out.println("Erreur: Veuillez choisir un chiffre entre 0 ou 1");
            } 
           
            jeu = (caseSelectionne == 0) ? 0 : 1;
            initialiseGrille();
        }
    }

    // Description: Texte Ascii
    public static void spashScreen() {
        System.out.println("\u001B[33m  ___ _   _ ___ ___ ___   _   _  _  ___ ___   _ _  ");
        System.out.println(" | _ \\ | | |_ _/ __/ __| /_\\ | \\| |/ __| __| | | | ");
        System.out.println(" |  _/ |_| || |\\__ \\__ \\/ _ \\| .` | (__| _|  |_  _|");
        System.out.println(" |_|  \\___/|___|___/___/_/ \\_\\_|\\_|\\___|___|   |_| \u001B[37m");
        System.out.println("");
    }

    // Description Menu pour choisir le mode de jeu soit 1VS1, soit jouer contre l'IA ou soit quitter le programme
    public static void menu() {
        while (jeu == 0) {
            clearScreen();
            spashScreen();
            int action = 0;

            
            System.out.println("");
            System.out.println("--------------------------------------------------------");
            System.out.println("  __  __ ___ _  _ _   _ ");
            System.out.println(" |  \\/  | __| \\| | | | |");
            System.out.println(" | |\\/| | _|| .` | |_| |");
            System.out.println(" |_|  |_|___|_|\\_|\\___/ ");
            System.out.println("");
            System.out.println(" 1 - 2 Joueurs (1 VS 1)");
            System.out.println(" 2 - Ordinateur (IA)");
            System.out.println(" 3 - Quitter");
            System.out.println("");
            System.out.println("--------------------------------------------------------");
            
            Scanner sc= new Scanner(System.in);
            System.out.println("");
            System.out.print("Choissez une action: ");  
            try {
                action = sc.nextInt();
            } catch(Exception e) {
                System.out.println("Erreur: Veuillez choisir un chiffre entre 1 et 3");
            } 
            if (action == 1) {
                jeu = 1;
                mode = 1;
            }
            else if (action == 2) {
                jeu = 1;
                mode = 2;
            }
            else if (action == 3) {
                clearScreen();
                System.exit(0);
            }
        }
    }

    // Boucle du programme
    public static void main(String[] args) {
        // Pour ne pas quitter le jeu
        while (jeu != 3) {
            menu();
            if (mode == 1)
                jeu();
            if (mode == 2)
                jeuIA();
            if (jeu == 2)
                winScreen();
        }
    }
}
