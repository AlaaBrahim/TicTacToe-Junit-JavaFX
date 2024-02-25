package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {
    // Instance de la logique du jeu Tic Tac Toe
    private TicTacToe ticTacToe = new TicTacToe();
    // Tableau de boutons représentant le plateau de jeu
    private Button[][] buttons = new Button[3][3];

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        launch(args);
    }

    // Méthode pour initialiser l'interface graphique
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Création des boutons et ajout au GridPane
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = createButton(i, j);
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        // Création de la scène avec le GridPane
        Scene scene = new Scene(gridPane, 300, 300);

        // Configuration de la fenêtre principale
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour créer un bouton avec des propriétés spécifiques
    private Button createButton(int x, int y) {
        Button button = new Button();
        button.setMinSize(100, 100);
        button.setOnAction(e -> handleButtonClick(x, y));
        return button;
    }

    // Gestionnaire d'événement pour le clic sur un bouton
    private void handleButtonClick(int x, int y) {
        try {
            buttons[x][y].setText(ticTacToe.getCurrentPlayer().toString());
            ticTacToe.play(x + 1, y + 1);
            checkWinner();
        } catch (RuntimeException ex) {
            showAlert("Coup invalide", ex.getMessage());
        }
    }

    // Vérifie s'il y a un gagnant ou un match nul
    private void checkWinner() {
        Character winner = ticTacToe.getWinner();
        if (winner != null) {
            if (winner == '0') {
                showAlert("Fin de la partie", "Match nul !");
            } else {
                showAlert("Fin de la partie", "Joueur " + winner + " remporte la partie !");
            }
            resetGame();
        }
    }

    // Réinitialise le jeu et les boutons
    private void resetGame() {
        ticTacToe.resetGame();
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");
            }
        }
    }

    // Affiche une boîte de dialogue avec un titre et un contenu spécifiques
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
