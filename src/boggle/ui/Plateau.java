package boggle.ui;

import boggle.config.ChargerConfig;
import boggle.jeu.*;
import boggle.mots.ArbreLexicalLudo;
import boggle.mots.GrilleLettres;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Plateau extends Application implements EtatJeuListener {

    private List<String> listMotValider = new ArrayList<>();
    private GridPane gridPane;
    private Label labelMotValider;
    private Label labelTextInformation;
    private Label labelJoueur;
    private Button buttonSupprimer;
    private GrilleLettres grilleLettres;
    private Stage stage;

    private GestionTour gestionTour;
    private int nbJoueur,taillePlateau,time;

    public static void main(String[] args) {
        launch(args);
    }

    //2 ème Page de bienvenue
    public void start(Stage stage) {
        this.stage = stage;
        Label labelResume = new Label();
        labelResume.setMinWidth(900);
        labelResume.setMinHeight(100);
        labelResume.setAlignment(Pos.CENTER);
        labelResume.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        Label labelBienvenue = new Label();
        labelBienvenue.setMinWidth(900);
        labelBienvenue.setMinHeight(100);
        labelBienvenue.setAlignment(Pos.CENTER);
        labelBienvenue.setStyle("-fx-font-size: 35; -fx-font-weight: bold;");

        String bienvenue = "BIENVENUE";
        labelBienvenue.setText(bienvenue);

        Line line = new Line(0, 0, 850, 0);
        line.setStrokeWidth(5);
        line.setStroke(Color.ORANGE);
        line.setTranslateX(20);

        String resume = "Dans une limite de temps de 3 minutes, vous devez trouver un maximum de mots en formant des chaînes\nde  lettres contiguës. Plus le mot est long, plus les points qu'il vous rapporte sont importants.\n" +
                "\n" +
                "Vous pouvez passer d'une lettre à la suivante située directement à gauche, à droite, en haut, en bas, ou\nsur l'une des quatre cases diagonales.\n" +
                "\n" +
                "    - Une lettre ne peut pas être utilisée plus d'une fois pour un même mot.\n" +
                "    - Seuls les mots de trois lettres ou plus comptent.\n" +
                "    - Les accents ne sont pas importants. E peut être utilisé comme É, È, Ê, etc...\n\n\n\n\n\n\n\n\n";
        labelResume.setText(resume);

        Button buttonStart = creerButtonStart("Start");
        buttonStart.setMinWidth(900);
        buttonStart.setMinHeight(100);


        //Contruction de la Vbox contenant le score et le boutton recommencer
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(labelBienvenue,line,labelResume,buttonStart);
        Scene scene = new Scene(vboxPrincipale, 900,  400 + 50);

        //Modification du titre de la scène
        stage.setTitle("Bienvenue sur Boogle");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public void startBienvenue() throws IOException {
        new ChargerConfig();
        new GestionTour(this).start();
    }

    public void startSelectMode() {
    	GridPane root = new GridPane();
    	
    	root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);
  
        Label labelTitle = new Label("Veuillez sélectionner un mode de jeu");
        labelTitle.setMinSize(900, 100);
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size: 35; -fx-font-weight: bold;");
        
        root.add(labelTitle, 0, 0, 2, 1);
        //root.setGridLinesVisible(true);
        
        Button onePlayer = creerButtonOnePlayer("1 Joueur");
        onePlayer.setMinSize(400, 100);
        root.setHgap(75);
        root.setVgap(30);
        Button twoPlayers = creerButtonTwoPlayers("2 Joueurs");
        twoPlayers.setMinSize(400, 100);
        Button oneVersusCpu = creerButtonOneVersusCpu("1 Joueur vs CPU");
        oneVersusCpu.setMinSize(400, 100);
        Button config = creerButtonConfig("Autre configuration");
        config.setMinSize(400, 100);
        
        root.add(onePlayer, 0, 1);
        root.add(twoPlayers, 0, 2);
        root.add(oneVersusCpu, 1, 1);
        root.add(config, 1, 2);
        
        Scene scene = new Scene(root,920,450);
        
        //Modification du titre de la scène
        stage.setTitle("Sélection du mode de jeu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    private Button creerButtonOnePlayer(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
				jouerOnePlayer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        return button;
    }
    
    public void jouerOnePlayer() throws IOException{
    	new ChargerConfig();
    	ChargerConfig.setNombreJoueur(1);
        new GestionTour(this).start();
    }
    
    private Button creerButtonTwoPlayers(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
				jouerTwoPlayers();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        return button;
    }
    
    public void jouerTwoPlayers() throws IOException{
    	new ChargerConfig();
        new GestionTour(this).start();
    }
    
    private Button creerButtonOneVersusCpu(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
				jouerOneVersusCpu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        return button;
    }
    
    public void jouerOneVersusCpu() throws IOException{
    	new ChargerConfig();
        new GestionTour(this).start();
    }
    
    private Button creerButtonConfig(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
				jouerConfig();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        return button;
    }
    
    public void jouerConfig() throws IOException{
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);
    	
    	Label labelTitle = new Label("Veuillez sélectionner vos préférences");
        labelTitle.setMinSize(900, 100);
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size: 35; -fx-font-weight: bold;");
        
        root.add(labelTitle, 0, 0, 2, 1);
                
        Label labelNbJoueurs = new Label("Nombre de joueur(s) :");
        TextField fieldNbJoueurs = new TextField();
        fieldNbJoueurs.textProperty().addListener((observable, oldValue, newValue) -> {
        	nbJoueur = Integer.parseInt(newValue);
        });
                
        Label labelTaillePlateau = new Label("Taille du plateau :");
        TextField fieldTaillePlateau = new TextField();
        fieldTaillePlateau.textProperty().addListener((observable, oldValue, newValue) -> {
        	taillePlateau = Integer.parseInt(newValue);
        });
        
        Label labelTime = new Label("Durée de la partie :");
        TextField fieldTime = new TextField();
        fieldTime.textProperty().addListener((observable, oldValue, newValue) -> {
        	time = Integer.parseInt(newValue);
        });
        
        Button buttonSubmit = creerButtonStartConfig("Start");
        
        root.add(labelNbJoueurs, 0, 1);
        root.add(labelTaillePlateau, 0, 2);
        root.add(labelTime, 0, 3);
        root.add(fieldNbJoueurs, 1, 1);
        root.add(fieldTaillePlateau, 1, 2);
        root.add(fieldTime, 1, 3);
        root.add(buttonSubmit, 1, 4);
        
    	Scene scene = new Scene(root, 900, 450);

        //Modification du titre de la scène
        stage.setTitle("Configuration du jeu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    private Button creerButtonStartConfig(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
				jouerPreferences();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        return button;
    }
    
    public void jouerPreferences() throws IOException{
    	new ChargerConfig();
    	ChargerConfig.setNombreJoueur(nbJoueur);
    	ChargerConfig.setTaillePlateau(taillePlateau);
    	ChargerConfig.setTimerSeconde(time);
        new GestionTour(this).start();
    }
    
    
    
    @Override
    public void setTour(Tour tour) throws IOException {
        this.grilleLettres = new GrilleLettres();
        gridPane = grilleLettres.getGridPane();
        grilleLettres.disableAllButton();

        labelTextInformation = new Label();
        labelTextInformation.setMinWidth((ChargerConfig.getTaillePlateau() - 1) * 80 - 20);
        labelTextInformation.setMinHeight(50);

        //Création du label pour le joueur courant
        labelJoueur = new Label("\n"+tour.getJoueur().getName()+"\n"+"     0 pts");
        labelJoueur.setMinWidth(170);
        labelJoueur.setMinHeight(50);
        labelJoueur.setAlignment(Pos.CENTER);
        labelJoueur.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Timer.setGrilleLettres(grilleLettres);
        VBox vBoxTimer = tour.getTimer().generateTimer();
        vBoxTimer.setMinWidth(80);
        vBoxTimer.setMinHeight(50);

        //Construction de la Hbox contenant l'information (partie et joueur)
        HBox hboxInformation = new HBox();
        hboxInformation.getChildren().addAll(vBoxTimer, labelTextInformation, labelJoueur);
        hboxInformation.setPadding(new Insets(0, 20, 0, 20));
        hboxInformation.setSpacing(20);

        //Construction du label pour afficher les mots validés
        labelMotValider = new Label();
        labelMotValider.setMinWidth(168);

        //Construction de la Vbox contenant le score
        VBox vboxScore = new VBox();
        vboxScore.getChildren().addAll(labelMotValider);
        vboxScore.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        //construction de la Hbox contenant les buttons et le score
        HBox hboxButton = new HBox();
        hboxButton.getChildren().addAll(gridPane, vboxScore);
        hboxButton.setPadding(new Insets(10, 20, 20, 20));
        hboxButton.setSpacing(20);


        grilleLettres.setButtonAjouter(creerButtonAjouter("Ajouter",tour));
        grilleLettres.getButtonAjouter().setDisable(true);
        buttonSupprimer = creerButtonSupprimer("Supprimer");
        buttonSupprimer.setDisable(true);
        Timer.setButtonSupprimer(buttonSupprimer);
        //Contruction d'une hbox contenant les bouttons ajout et suppression
        HBox hBoxButtonAction = new HBox();
        hBoxButtonAction.setSpacing(8);
        hBoxButtonAction.getChildren().addAll(grilleLettres.getButtonAjouter(), buttonSupprimer);

        //Construction de la Hbox contenant le mot en cours et les buttons ajouter et supprimer
        HBox hboxMot = new HBox();
        hboxMot.getChildren().addAll(grilleLettres.getLabelMotEnCours(), hBoxButtonAction);
        hboxMot.setPadding(new Insets(0, 20, 0, 20));
        hboxMot.setSpacing(20);

        //Contruction de la Vbox contenant le tout
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(hboxInformation, hboxButton, hboxMot);

        //Construction de la scéne
        Scene scene = new Scene(vboxPrincipale, (ChargerConfig.getTaillePlateau() * 80) + 230, (ChargerConfig.getTaillePlateau() * 80) + 160);

        //Modification du titre de la scène
        stage.setTitle("Plateau de jeu BOGGLE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void finDuJeu(List<Tour> tourList) {

        stage.close();

        Label labelScore = new Label();
        labelScore.setMinWidth(520);
        labelScore.setMinHeight(tourList.size() * 100);
        labelScore.setAlignment(Pos.CENTER);
        labelScore.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        String result = "";
        //Récupération des scores
        for (Tour tour : tourList)
            result += tour.getJoueur().toString() + " \n\n";

        labelScore.setText(result);

        Button buttonRecommencer = creerButtonRecommencer("Recommencer");
        buttonRecommencer.setMinWidth(520);
        buttonRecommencer.setMinHeight(50);

        //Contruction de la Vbox contenant le score et le boutton recommencer
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(labelScore, buttonRecommencer);
        Scene scene = new Scene(vboxPrincipale, 520, tourList.size() * 100 + 50);

        //Modification du titre de la scène
        stage.setTitle("Fin du jeu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Permet la création de boutton
    private Button creerButtonBienvenue(String s) {
        Button button = new Button(s);
        button.setMinWidth(80);
        button.setMinHeight(80);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
                startBienvenue();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return button;
    }

    //Permet la création de boutton
    private Button creerButtonRecommencer(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            start(stage);
        });
        return button;
    }

    //Permet la création de boutton
    private Button creerButtonStart(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            startSelectMode();
        });
        return button;
    }


    //Permet la création du boutton ajouter ainsi q'un traitement particulier
    private Button creerButtonAjouter(String s, Tour tour) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {
            if (!listMotValider.contains(grilleLettres.getLabelMotEnCours().getText())) {
                try {
                    if (estDansDictionnaire(grilleLettres.getLabelMotEnCours().getText())) {
                        tour.getJoueur().updateScore(grilleLettres.getLabelMotEnCours().getText());
                        labelJoueur.setText("\n"+tour.getJoueur().getName()+"\n     "+tour.getJoueur().getScore()+" pts");
                        String toutLesMotsValider = labelMotValider.getText();
                        toutLesMotsValider += grilleLettres.getLabelMotEnCours().getText() + "\n";
                        labelMotValider.setText(toutLesMotsValider);
                        labelMotValider.setAlignment(Pos.CENTER);
                        labelMotValider.setStyle("-fx-font-weight: bold");
                        listMotValider.add(grilleLettres.getLabelMotEnCours().getText());
                        grilleLettres.getLabelMotEnCours().setText("");
                        grilleLettres.getButtonListCheck().clear();
                        grilleLettres.enableAllButton();
                        grilleLettres.getButtonAjouter().setDisable(true);
                        labelTextInformation.setText("Bravo !");
                    } else {
                        labelTextInformation.setText("Ce mot n'existe pas !");
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                labelTextInformation.setText("Ce mot est déjà pris !");
            }
            labelTextInformation.setAlignment(Pos.CENTER);
            labelTextInformation.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
            labelTextInformation.setTextFill(Color.RED);
        });
        return button;
    }

    //Permet la création du boutton supprimer ainsi qu'un traitement particulier
    private Button creerButtonSupprimer(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {
            grilleLettres.getLabelMotEnCours().setText("");
            labelTextInformation.setText("");
            grilleLettres.getButtonAjouter().setDisable(true);
            grilleLettres.getButtonListCheck().clear();
            grilleLettres.enableAllButton();
        });
        return button;
    }

    public boolean estDansDictionnaire(String motEnCours) throws FileNotFoundException {
        return ChargerConfig.getArbreLexicalLudo().contient(motEnCours);
    }
}
