package TableTennis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    @FXML
    private Text name1;
    @FXML
    private Text name2;
    @FXML
    private Button score1;
    @FXML
    private Button score2;
    @FXML
    private Button startbutton1;
    @FXML
    private Button startbutton2;
    @FXML
    private ChoiceBox<String> choicebox1;
    @FXML
    private ChoiceBox<String> choicebox2;
    @FXML
    private StackPane stack1;
    @FXML
    private StackPane stack2;
    @FXML
    private ImageView serv1;
    @FXML
    private ImageView serv2;
    @FXML
    private Text player1;
    @FXML
    private Text player2;
    @FXML
    private Text player3;
    @FXML
    private Text player4;
    @FXML
    private Text player5;
    @FXML
    private Text player6;
    @FXML
    private Button deletetionbutton1;
    @FXML
    private Button deletionbutton2;
    @FXML
    private Button deletetionbutton3;
    @FXML
    private Button deletetionbutton4;
    @FXML
    private Button deletetionbutton5;
    @FXML
    private Button deletetionbutton6;
    @FXML
    private TextField newplayer;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<Player,String> namecol;
    @FXML
    private TableColumn<Player,Integer> totalcol;
    @FXML
    private TableColumn<Player,Integer> winscol;
    @FXML
    private TableColumn<Player,Integer> losescol;
    @FXML
    private TableColumn<Player,Integer> pointscol;
    @FXML
    private TableColumn<Player,Integer> ratingcol;
    @FXML
    private TableColumn<Player,Integer> streakcol;
    @FXML
    private TableColumn<Player,Integer> beststreakcol;
    @FXML
    private TableColumn<Player,Integer> worststreakcol;
    @FXML
    private TableColumn<Player,Integer> undercol;
    @FXML
    private Button firstsubtract;
    @FXML
    private Button secondsubtract;

    private ArrayList<String> playersNameList=new ArrayList<>();

    private ArrayList<Player> playersList=new ArrayList<>();

    private ObservableList<Player> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rectangle clip = new Rectangle(
                serv1.getFitWidth(), serv1.getFitHeight()
        );
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        serv1.setClip(clip);


        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = serv1.snapshot(parameters, null);

        // apply a shadow effect.
        serv1.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        serv1.setImage(image);

        Rectangle clip2 = new Rectangle(
                serv2.getFitWidth(), serv2.getFitHeight()
        );
        clip2.setArcWidth(50);
        clip2.setArcHeight(50);
        serv2.setClip(clip2);


        // snapshot the rounded image.
        SnapshotParameters parameters2 = new SnapshotParameters();
        parameters2.setFill(Color.TRANSPARENT);
        WritableImage image2 = serv1.snapshot(parameters2, null);

        // apply a shadow effect.
        serv2.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        serv2.setImage(image2);



        score1.setDisable(true);
        score2.setDisable(true);
        serv1.setVisible(false);
        serv2.setVisible(false);
        try {
            File file = new File("src\\players.txt");
            Scanner sc = new Scanner(file);
            int i=1;
            while (sc.hasNextLine()) {
                String name=sc.nextLine();
                playersNameList.add(name);
                if (i==1){
                    player1.setText(name);
                    deletetionbutton1.setVisible(true);
                }
                if (i==2){
                    player2.setText(name);
                    deletionbutton2.setVisible(true);
                }
                if (i==3){
                    player3.setText(name);
                    deletetionbutton3.setVisible(true);
                }
                if (i==4){
                    player4.setText(name);
                    deletetionbutton4.setVisible(true);
                }
                if (i==5){
                    player5.setText(name);
                    deletetionbutton5.setVisible(true);
                }
                if (i==6){
                    player6.setText(name);
                    deletetionbutton6.setVisible(true);
                }
                i++;
            }
        } catch (IOException e){}

        readPlayers();

        observableList.addAll(playersList);

        namecol.setCellValueFactory( new PropertyValueFactory<>("Name"));
        totalcol.setCellValueFactory( new PropertyValueFactory<>("Totalgames"));
        winscol.setCellValueFactory(new PropertyValueFactory<>("Wins"));
        losescol.setCellValueFactory(new PropertyValueFactory<>("Loses"));
        pointscol.setCellValueFactory(new PropertyValueFactory<>("Points"));
        ratingcol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        streakcol.setCellValueFactory(new PropertyValueFactory<>("streak"));
        beststreakcol.setCellValueFactory( new PropertyValueFactory<>("Beststreak"));
        worststreakcol.setCellValueFactory( new PropertyValueFactory<>("Worststreak"));
        undercol.setCellValueFactory( new PropertyValueFactory<>("undertable"));

        table.setItems(observableList);

        this.choicebox1.setItems(FXCollections.observableList(playersNameList));
        this.choicebox2.setItems(FXCollections.observableList(playersNameList));
    }

    public void changePlayer(String player, boolean win, int difference, boolean underboolean){
        int total=0;
        int wins=0;
        int loses=0;
        int points=0;
        int rating=0;
        int streak=0;
        int best=0;
        int worst=0;
        int under=0;
        try {
            File file = new File("src\\"+player+".txt");
            Scanner sc = new Scanner(file);
            total=Integer.parseInt(sc.nextLine());
            wins=Integer.parseInt(sc.nextLine());
            loses=Integer.parseInt(sc.nextLine());
            points=Integer.parseInt(sc.nextLine());
            rating=Integer.parseInt(sc.nextLine());
            streak=Integer.parseInt(sc.nextLine());
            best=Integer.parseInt(sc.nextLine());
            worst=Integer.parseInt(sc.nextLine());
            under=Integer.parseInt(sc.nextLine());
        } catch (IOException e){}
        try {
            FileWriter myWriter = new FileWriter("src\\"+player+".txt");
            if (win) {
                myWriter.write("" + (total + 1)+"\n");
                myWriter.write("" + (wins + 1)+"\n");
                myWriter.write(""+loses+"\n");
                myWriter.write(""+(points+1)+"\n");
                myWriter.write(""+(rating+difference)+"\n");
                if (streak<1) myWriter.write("1"+"\n");
                else myWriter.write(""+(streak+1)+"\n");
                if (streak+1>best||best==0) myWriter.write("" + (best + 1)+"\n");
                else myWriter.write("" + best+"\n");
                myWriter.write("" + worst+"\n");
                myWriter.write(""+under+"\n");
            }else {
                myWriter.write("" + (total + 1)+"\n");
                myWriter.write("" + wins+"\n");
                myWriter.write(""+ (loses+1)+"\n");
                myWriter.write(""+(points-1)+"\n");
                myWriter.write(""+(rating-difference)+"\n");
                if (streak>-1) myWriter.write("-1"+"\n");
                else myWriter.write(""+(streak-1)+"\n");
                myWriter.write("" + best+"\n");
                if (streak-1<worst||worst==0)myWriter.write("" + (worst-1)+"\n");
                else myWriter.write("" + worst+"\n");
                if (underboolean) myWriter.write(""+(under+1)+"\n");
                else myWriter.write(""+under+"\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readPlayers(){
        playersList.clear();
        for (int i = 0; i < playersNameList.size(); i++) {
            String name=playersNameList.get(i);
            readPlayer(name);
        }
    }

    public void readPlayer(String name){
        try {
            File file = new File("src\\"+name+".txt");
            Scanner sc = new Scanner(file);
            int total=Integer.parseInt(sc.nextLine());
            int wins=Integer.parseInt(sc.nextLine());
            int loses=Integer.parseInt(sc.nextLine());
            int points=Integer.parseInt(sc.nextLine());
            int rating=Integer.parseInt(sc.nextLine());
            int streak=Integer.parseInt(sc.nextLine());
            int best=Integer.parseInt(sc.nextLine());
            int worst=Integer.parseInt(sc.nextLine());
            int under=Integer.parseInt(sc.nextLine());
            playersList.add(new Player(name,total,wins,loses,points,rating,streak,best,worst,under));
        } catch (IOException e){}
    }

    @FXML
    public void score1clicked() {
        int newScore=Integer.parseInt(score1.getText())+1;
        score1.setText(""+newScore);
        int kokku=Integer.parseInt(score1.getText())+Integer.parseInt(score2.getText());
        if (kokku%2==0) {
            vahetaservija();
        }
        if (newScore>10&&newScore-Integer.parseInt(score2.getText())>1){
            //removing items from table
            for (int i = 0; i < playersList.size(); i++) {
                table.getItems().remove(playersList.get(i));
            }


            int difference=Integer.parseInt(score1.getText())-Integer.parseInt(score2.getText());
            if (score2.getText().equals("0")){
                changePlayer(name1.getText(), true, difference,false);
                changePlayer(name2.getText(), false, difference,true);
            }else{
                changePlayer(name1.getText(), true, difference,false);
                changePlayer(name2.getText(), false, difference,false);
            }
            readPlayers();
            observableList.addAll(playersList);
            table.refresh();

            try {
                File file=new File("src\\results.txt");
                FileWriter myWriter = new FileWriter(file.getAbsoluteFile(), true);
                myWriter.write(name1.getText()+" "+score1.getText()+" - "+score2.getText()+" "+name2.getText()+"\n");
                myWriter.close();
            } catch (IOException e) {}
            Alert messageWindow=new Alert(Alert.AlertType.INFORMATION);
            messageWindow.setContentText(name1.getText()+" won the game "+score1.getText()+" - "+score2.getText());
            messageWindow.show();
            newgame();
        }
        else if (newScore>9&&newScore-Integer.parseInt(score2.getText())>0){
            System.out.println("Matchball");
        }
    }

    @FXML
    public void score2clicked() {
        int newScore=Integer.parseInt(score2.getText())+1;
        score2.setText(""+newScore);
        int kokku=Integer.parseInt(score1.getText())+Integer.parseInt(score2.getText());
        if (kokku%2==0) {
            vahetaservija();
        }
        if (newScore>10&&newScore-Integer.parseInt(score1.getText())>1){
            //removing items from table
            System.out.println(playersList.size());
            for (int i = 0; i < playersList.size(); i++) {
                table.getItems().remove(playersList.get(i));
            }

            int difference=Integer.parseInt(score2.getText())-Integer.parseInt(score1.getText());
            if (score1.getText().equals("0")){
                changePlayer(name2.getText(), true, difference,false);
                changePlayer(name1.getText(), false, difference,true);
            }else{
                changePlayer(name2.getText(), true, difference,false);
                changePlayer(name1.getText(), false, difference,false);
            }

            readPlayers();
            observableList.addAll(playersList);
            table.refresh();

            try {
                File file=new File("src\\results.txt");
                FileWriter myWriter = new FileWriter(file.getAbsoluteFile(), true);
                myWriter.write(name2.getText()+" "+score2.getText()+" - "+score1.getText()+" "+name1.getText()+"\n");
                myWriter.close();
            } catch (IOException e) {}
            Alert messageWindow=new Alert(Alert.AlertType.INFORMATION);
            messageWindow.setContentText(name2.getText()+" won the game "+score2.getText()+" - "+score1.getText());
            messageWindow.show();
            newgame();
        }
        else if (newScore>9&&newScore-Integer.parseInt(score1.getText())>0){
            System.out.println("Matchball");

        }
    }

    public void newgame(){
        score1.setText("");
        score2.setText("");
        startbutton1.setDisable(false);
        startbutton2.setDisable(false);
        startbutton1.setText("Start and Serv");
        startbutton2.setText("Start and Serv");
        choicebox1.setVisible(true);
        choicebox2.setVisible(true);
        serv1.setVisible(false);
        serv2.setVisible(false);
        firstsubtract.setVisible(false);
        secondsubtract.setVisible(false);
        score1.setDisable(true);
        score2.setDisable(true);
        stack1.setBackground(new Background(new BackgroundFill(Color.valueOf("#e6c34d"), CornerRadii.EMPTY, Insets.EMPTY)));
        stack2 .setBackground(new Background(new BackgroundFill(Color.valueOf("#e6c34d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void vahetaservija (){
        if (serv1.isVisible()){
            serv1.setVisible(false);
            serv2.setVisible(true);
        }
        else {
            serv2.setVisible(false);
            serv1.setVisible(true);
        }
    }

    @FXML
    public void start1clicked() throws IOException {
        start(true);
    }
    @FXML
    public void start2clicked() throws IOException {
        start(false);
    }

    public void start (boolean firstserving) throws IOException{
        if (choicebox1.getValue()==null&&choicebox2.getValue()==null){
            Alert errorWindow=new Alert(Alert.AlertType.ERROR);
            errorWindow.setContentText("PLEASE SELECT PLAYERS!");
            errorWindow.show();
        }
        else if (choicebox1.getValue()==null||choicebox2.getValue()==null){
            Alert errorWindow=new Alert(Alert.AlertType.ERROR);
            errorWindow.setContentText("WE NEED 2 PLAYERS!");
            errorWindow.show();
        }
        else if (choicebox1.getValue().equals("Player1")&&choicebox2.getValue().equals("Player1")){
            Alert errorWindow=new Alert(Alert.AlertType.ERROR);
            errorWindow.setContentText("Player1 IS POWERFUL, BUT HE STILL NEEDS SB TO PLAY WITH!");
            errorWindow.show();
        }
        else if (choicebox1.getValue().equals(choicebox2.getValue())){
            Alert errorWindow=new Alert(Alert.AlertType.ERROR);
            errorWindow.setContentText(choicebox1.getValue().toUpperCase()+" IS NOT SO POWERFUL!");
            errorWindow.show();
        }
        else {
            score1.setDisable(false);
            score2.setDisable(false);
            if (firstserving) serv1.setVisible(true);
            else serv2.setVisible(true);
            score1.setText("0");
            score2.setText("0");
            firstsubtract.setVisible(true);
            secondsubtract.setVisible(true);
            name1.setText(choicebox1.getValue());
            name2.setText(choicebox2.getValue());
            startbutton1.setDisable(true);
            startbutton2.setDisable(true);
            choicebox1.setVisible(false);
            choicebox2.setVisible(false);


            /******texts and images can be added
            int random;
            if (name1.getText().equals("Player1")){
                stack1.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
                startbutton1.setText("");
                random=randomNumber(1,4);
                serv1.setImage(new Image(new FileInputStream("src\\eliisa"+random+".jpg")));
            }
            else if (name1.getText().equals("Player2")){
                stack1.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,7);
                startbutton1.setText("");
                serv1.setImage(new Image(new FileInputStream("src\\karl"+random+".jpg")));
            }
            else if (name1.getText().equals("Player3")){
                stack1.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,7);
                startbutton1.setText("Täna pole sinu päev!");
                if (random==5)
                    serv1.setImage(new Image(new FileInputStream("src\\rainer"+random+".png")));
                else
                    serv1.setImage(new Image(new FileInputStream("src\\rainer"+random+".jpg")));
            }
            else {
                stack1.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,2);
                startbutton1.setText("Algaja õnn on sinu poolel!");
                serv1.setImage(new Image(new FileInputStream("src\\kevin"+random+".jpg")));
            }


            if (name2.getText().equals("Player1")){
                stack2.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,4);
                startbutton2.setText("Pimesta vastane iluga!");
                serv2.setImage(new Image(new FileInputStream("src\\eliisa"+random+".jpg")));
            }
            else if (name2.getText().equals("Player2")){
                stack2.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,7);
                startbutton2.setText("Näita, kes on tugevam!");
                serv2.setImage(new Image(new FileInputStream("src\\karl"+random+".jpg")));
            }
            else if (name2.getText().equals("Player3")){
                stack2.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,7);
                startbutton2.setText("Täna pole sinu päev!");
                if (random==5)
                    serv2.setImage(new Image(new FileInputStream("src\\rainer"+random+".png")));
                else
                    serv2.setImage(new Image(new FileInputStream("src\\rainer"+random+".jpg")));
            }
            else {
                stack2.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                random=randomNumber(1,2);
                startbutton1.setText("Algaja õnn on sinu poolel!");
                serv2.setImage(new Image(new FileInputStream("src\\kevin"+random+".jpg")));
            }**/
        }
    }

    @FXML
    public void deleteplayer(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("password.fxml").openStream());
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Password");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
        }
    }
    @FXML
    public void addplayer() {
        if (!newplayer.getText().equals("")) {
            try {
                newplayer.setText("");
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Pane root = fxmlLoader.load(getClass().getResource("password.fxml").openStream());
                stage.setScene(new Scene(root));
                stage.setTitle("Admin Password");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
            }
        }
    }

    @FXML
    public void subtract1(){
        int previousScore=Integer.parseInt(score1.getText());
        if (previousScore>0) {
            score1.setText("" + (previousScore - 1));
            if ((Integer.parseInt(score1.getText()) + Integer.parseInt(score2.getText())) % 2 == 1)
                vahetaservija();
        }
    }

    @FXML
    public void subtract2(){
        int previousScore=Integer.parseInt(score2.getText());
        if (previousScore>0) {
            score2.setText("" + (previousScore - 1));
            if ((Integer.parseInt(score1.getText()) + Integer.parseInt(score2.getText())) % 2 == 1)
                vahetaservija();
        }
    }

    //Handling the key typed event
    EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.A) {
                System.out.println("Key Pressed: " + event.getCode());
                score1.arm();
            }
        }
    };

    public static int randomNumber (int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}