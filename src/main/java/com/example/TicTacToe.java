package com.example;

public class TicTacToe {
    // Le plateau de jeu est représenté par une matrice 3x3 de caractères
    private Character[][] board = {
            { '0', '0', '0' },
            { '0', '0', '0' },
            { '0', '0', '0' }
    };
    private Character currentMark = 'X'; // Marque actuelle du joueur
    private boolean isGameOver = false; // Indique si le jeu est terminé

    // Version 0 - Méthode pour vérifier les axes avant de jouer
    public void playV0(int x, int y) {
        verifyAxis(x, 0);
        verifyAxis(y, 1);
    }

    // Version 1 - Méthode pour placer un pion avec spécification du joueur
    public void playV1(int x, int y, char pion) {
        verifyAxis(x, 0);
        verifyAxis(y, 1);
        placerPion(x, y, pion);
    }

    // Version 2 - Méthode pour vérifier que X joue en premier
    public void playV2(int x, int y, char pion) {
        verifXFirst(pion);
        verifyAxis(x, 0);
        verifyAxis(y, 1);
        placerPion(x, y, pion);
    }

    // Version 3 - Méthode pour changer de joueur après chaque coup
    public void playV3(int x, int y, char pion) {
        verifyAxis(x, 0);
        verifyAxis(y, 1);
        switchPlayer();
        placerPion(x, y, pion);
    }

    // Version 4 - Méthode pour jouer et retourner le résultat (gagnant ou null)
    public Character playV4(int x, int y, char pion) {
        verifyAxis(x, 0);
        verifyAxis(y, 1);
        switchPlayer();
        placerPion(x, y, pion);
        return getWinner();
    }

    // Version 5 - Méthode pour jouer et retourner le résultat (gagnant ou null)
    public Character playV5(int x, int y, char pion) {
        verifyAxis(x, 0);
        verifyAxis(y, 1);
        switchPlayer();
        placerPion(x, y, pion);
        return getWinner();
    }

    // Méthode pour vérifier si X joue en premier, sinon lance une exception
    public void verifXFirst(char pion) {
        if (!isGameOver) {
            if (pion != 'X') {
                throw new RuntimeException("X must play first.");
            }
        }
    }

    // Méthode pour placer un pion sur le plateau de jeu
    public void placerPion(int x, int y, char pion) {
        // Vérifie si la cellule est déjà occupée
        if (board[x - 1][y - 1] != '0') {
            throw new RuntimeException("Cell is occupied");
        }
        board[x - 1][y - 1] = currentMark;
        switchPlayer(); // Passe au joueur suivant
    }

    // Méthode principale pour effectuer un coup sur le plateau de jeu
    public Character[][] play(int x, int y) {
        if (isGameOver) {
            throw new RuntimeException("Game is over");
        }
        verifyAxis(x, 0);
        verifyAxis(y, 1);

        // Place le pion du joueur actuel sur le plateau
        placerPion(x, y, currentMark);
        getWinner(); // Vérifie s'il y a un gagnant après le coup
        return board;
    }

    // Méthode pour changer de joueur (X -> O ou O -> X)
    private void switchPlayer() {
        if (currentMark == 'X') {
            currentMark = 'O';
        } else {
            currentMark = 'X';
        }
    }

    // Méthode pour vérifier si l'axe (X ou Y) est dans les limites du plateau
    private void verifyAxis(int xy, int axis) {
        if (xy < 1 || xy > 3) {
            if (axis == 0)
                throw new RuntimeException("X is outside board");
            throw new RuntimeException("Y is outside board");
        }
    }

    // Méthode pour obtenir le plateau de jeu
    public Character[][] getBoard() {
        return board;
    }

    // Méthode pour obtenir le joueur actuel
    public Character getCurrentPlayer() {
        return currentMark;
    }

    // Méthode pour vérifier si le plateau est entièrement rempli
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '0') {
                    return false; // Il y a une cellule vide, le plateau n'est pas plein
                }
            }
        }
        isGameOver = true;
        return true; // Aucune cellule vide trouvée, le plateau est plein
    }

    // Méthode pour vérifier si la partie est terminée
    public boolean isGameOver() {
        return isGameOver;
    }

    // Méthode pour déterminer le gagnant ou s'il y a un match nul
    public Character getWinner() {
        // Vérifie les lignes et colonnes
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                isGameOver = true;
                return board[i][0]; // Gagnant dans la ligne
            }
            if (board[0][i] != '0' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                isGameOver = true;
                return board[0][i]; // Gagnant dans la colonne
            }
        }

        // Vérifie les diagonales
        if (board[0][0] != '0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            isGameOver = true;
            return board[0][0]; // Gagnant dans la diagonale haut-gauche à bas-droite
        }
        if (board[0][2] != '0' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            isGameOver = true;
            return board[0][2]; // Gagnant dans la diagonale haut-droite à bas-gauche
        }

        if (isBoardFull()) {
            isGameOver = true;
            return '0'; // Match nul
        }

        return null; // Pas de gagnant pour l'instant
    }

    // Méthode pour réinitialiser la partie
    public void resetGame() {
        board = new Character[][] {
                { '0', '0', '0' },
                { '0', '0', '0' },
                { '0', '0', '0' }
        };
        currentMark = 'X';
        isGameOver = false;
    }
}
