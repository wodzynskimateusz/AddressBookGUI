package addressbook;

import addressbook.model.Person;
import addressbook.viewcontroller.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main(){
        personData.add(new Person("Jan","Testowy"));
        personData.add(new Person("Katarzyna","Kowalska"));
        personData.add(new Person("Zygmunt","Nowak"));
        personData.add(new Person("Jakub","Zieliński"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Książka adresowa");
        initRootLayout();
        showPersonOverview();
    }

    // Wczytujemy scenę
    private void initRootLayout() {
        try {
            root = FXMLLoader.load(getClass().getResource("viewcontroller/RootLayout.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Wczytujemy widok szczegółów osoby
    public void showPersonOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewcontroller/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();
            root.setCenter(personOverview);
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Person> getPersonData(){
        return personData;
    }
}
