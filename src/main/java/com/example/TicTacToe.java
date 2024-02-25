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

    // Méthode pour effectuer un coup sur le plateau de jeu
    public Character[][] play(int x, int y) {
        if (isGameOver) {
            throw new RuntimeException("Game is over");
        }
        verifyAxis(x, 0);
        verifyAxis(y, 1);

        // Vérifie si la cellule est déjà occupée
        if (board[x - 1][y - 1] != '0') {
            throw new RuntimeException("Cell is occupied");
        }
        board[x - 1][y - 1] = currentMark;
        getWinner(); // Vérifie s'il y a un gagnant après le coup
        switchPlayer(); // Passe au joueur suivant
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
