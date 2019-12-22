package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML ListView<String> listView;
    @FXML Button buttonNow;
    @FXML Button openRegister;
    @FXML Button openLogin;
    @FXML Pane paneListView;
    public static List<Leki> leki;

    private Stage stage;
    Alert alert;
    ArrayList<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list=new ArrayList();

        for(Leki c:leki=Main.connector.selectLeki())
            list.add("■Ean: "+c.getEan1()+"    ■Nazwa: "+c.getName());

        //paneListView.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        listView.setItems((FXCollections.observableList(list)));
        //listView.setItems(list);
        buttonfinaladd.setVisible(false);
        textfieldnamedd.setVisible(false);
        textfieldeanadd.setVisible(false);
        textFieldDel.setVisible(false);
        buttonfinaldel.setVisible(false);
        labelnUsun.setVisible(false);

        try {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                textFieldDel.setText(newValue.toString().substring(6,20));
            }
        });
        }catch (java.lang.NullPointerException e){
            e.printStackTrace();
        }


        textfieldeanadd.lengthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = textfieldeanadd.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9' )) {
                        textfieldeanadd.setText(textfieldeanadd.getText().substring(0,textfieldeanadd.getText().length()-1));
                    }
                }
            }

        });

        textFieldDel.lengthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = textFieldDel.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9' )) {
                        textFieldDel.setText(textFieldDel.getText().substring(0,textFieldDel.getText().length()-1));
                    }
                }
            }

        });
    }

    @FXML
    private void openRegister() throws IOException{
        //ObservableList<String> list2= FXCollections.observableArrayList("1","2","3");
        //listView.setItems(list2);
        //Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + "" + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        Alert alert = new Alert(Alert.AlertType.ERROR, "Delete " + "" + " ?", ButtonType.YES);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        }
    }


    @FXML Button buttonfinaladd;
    @FXML Button buttonaddprod;
    @FXML TextField textfieldnamedd;
    @FXML TextField textfieldeanadd;
    @FXML
    private  void unlockAdd() throws IOException{
        textfieldnamedd.setVisible(true);
        textfieldeanadd.setVisible(true);
        buttonfinaladd.setVisible(true);
        buttonaddprod.setDisable(true);
        textfieldeanadd.setFocusTraversable(false);
        textfieldnamedd.setFocusTraversable(false);

    }

    @FXML
    private  void add() throws IOException{
        buttonfinaladd.setVisible(false);
        textfieldnamedd.setVisible(false);
        textfieldeanadd.setVisible(false);
        buttonaddprod.setDisable(false);

        System.out.println("■"+(textfieldeanadd.getText().toString().length()==13));
        if((textfieldeanadd.getText().toString().equals("") && textfieldeanadd.getText().toString().equals("")) || !(textfieldeanadd.getText().toString().length()==13)){
            alert = new Alert(Alert.AlertType.ERROR, "Nie wpisano wszystkich danych bądź kod EAN jest krótszy/większy niż 13 znaków", ButtonType.OK);
            alert.setTitle("Błąd");
            alert.setHeaderText("Bład podczas dodwania produktu");
            alert.showAndWait();

        }else{

        Main.connector.insertLeki( textfieldnamedd.getText().toString(),textfieldeanadd.getText().toString());
        list=new ArrayList();
        for(Leki c:Main.connector.selectLeki())
            list.add("■Ean: "+c.getEan1()+"    ■Nazwa: "+c.getName());
        //paneListView.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        listView.setItems((FXCollections.observableList(list)));
        }


    }



    @FXML Button buttonfinaldel;
    @FXML Button buttondelprod;
    @FXML TextField textFieldDel;
    @FXML Label labelnUsun;
    @FXML
    private  void unlockDel() throws IOException{
        textFieldDel.setVisible(true);
        buttonfinaldel.setVisible(true);
        labelnUsun.setVisible(true);
        buttondelprod.setDisable(true);
        textFieldDel.setFocusTraversable(false);
    }

    @FXML
    private  void del() throws IOException{
        buttondelprod.setDisable(false);
        buttonfinaldel.setVisible(false);
        labelnUsun.setVisible(false);
        textFieldDel.setVisible(false);

        if(textFieldDel.getText().toString().equals("")){
            alert = new Alert(Alert.AlertType.ERROR, "Nie wpisano wszystkich danych", ButtonType.OK);
            alert.setTitle("Błąd");
            alert.setHeaderText("Bład podczas usuwania produktu");
            alert.showAndWait();

        }else{
        Main.connector.deleteLeki(textFieldDel.getText().toString());

        list=new ArrayList();
        for(Leki c:Main.connector.selectLeki())
            list.add("■Ean: "+c.getEan1()+"    ■Nazwa: "+c.getName());
        //paneListView.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        listView.setItems((FXCollections.observableList(list)));
        }
    }

    @FXML
    private void aboutAouthor() throws IOException{
        alert = new Alert(Alert.AlertType.INFORMATION, "ProjektMed", ButtonType.OK);
        alert.setTitle("O autorze");
        alert.setHeaderText("Tomasz Budziński");
        alert.showAndWait();
    }

    @FXML
    private void help() throws IOException{
        alert = new Alert(Alert.AlertType.INFORMATION, "■ Istnieją dwie bazy jedna należy do użytkownika druga jest ogólną dostępnych Leków\n■ System pozwala dodawać i usuwać produkty lekowe do ogólnej bazy danych\n■ Pozwala przywrócić domyślnie wartości obu baz", ButtonType.OK);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Zaawansowany system zarządzanai domową apteczką");
        alert.showAndWait();
    }

    @FXML
    private void resetUserBase() throws IOException{
        Main.connector.executeSQL(SQLCommand.createDatabase);
        Main.connector.executeSQL(SQLCommand.createBaseUserLeki);
        Main.connector.executeSQL(SQLCommand.createBaseUserLeki2);
    }
    @FXML
    private void resetBase() throws IOException{
        Main.connector.executeSQL(SQLCommand.createDatabase);
        Main.connector.executeSQL(SQLCommand.createBaseLeki);
        Main.connector.executeSQL(SQLCommand.createBaseLeki2);
        //Main.connector.executeSQL(SQLCommand.insertBaseLekiLock);
        Main.connector.executeSQL(SQLCommand.insertBaseLeki);
        //Main.connector.executeSQL(SQLCommand.insertBaseLekiUnlock);
        list=new ArrayList();
        for(Leki c:Main.connector.selectLeki())
            list.add("■Ean: "+c.getEan1()+"    ■Nazwa: "+c.getName());
        //paneListView.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        listView.setItems((FXCollections.observableList(list)));
    }

}

