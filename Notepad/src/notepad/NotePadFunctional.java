package notepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class NotePadFunctional extends BorderPane {

    protected final MenuBar MmenuBar;
    protected final Menu fileMenu;
    protected final MenuItem newMenuItem;
    protected final MenuItem openMenuItem;
    protected final MenuItem saveMenuItem;
    protected final MenuItem exitMenuItem;
    protected final Menu editMenu;
    protected final MenuItem cutMenuItem;
    protected final MenuItem copyMenuItem;
    protected final MenuItem pasteMenuItem;
    protected final MenuItem deleteMenuItem;
    protected final MenuItem selectAllMenuItem;
    protected final Menu helpMenu;
    protected final MenuItem aboutMenuItem;
    //protected final DialogPane helpDialogPane;
    //protected final AnchorPane helpAnchorPane;
    // protected final AnchorPane helpAnchorPane2;
    protected final AnchorPane anchorPane;
    protected final TextArea mTextArea;
    Stage stage;
    File selectedFile = null;
    boolean isNewFile = true;
    File dest = null;
    FileChooser fileChooser = new FileChooser();

    public synchronized void saveFile() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (selectedFile == null) {
                    openNewFile();
                    try {

                        FileWriter fileWriter = new FileWriter(selectedFile);
                        fileWriter.write(mTextArea.getText());
                        fileWriter.flush();
                        fileWriter.close();
                         System.err.println("saved");
                        // Files.copy(saveFile.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(NotePadFunctional.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        FileWriter fileWriter = new FileWriter(selectedFile);
                        fileWriter.write(mTextArea.getText());
                        fileWriter.flush();
                        fileWriter.close();
                        System.out.println("saved");
                        // Files.copy(saveFile.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(NotePadFunctional.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();

    }

    public  void openNewFile() {

        
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                String format = "text" + currentDate + ".txt";
                String userDirectory = "C:\\Users\\Abd-Elmalek\\Desktop";
                //System.getProperty("user.dir");
                String path = userDirectory + "\\" + format;
                //String path = userDirectory + "\\" + "text";


                try {
                    selectedFile = new File(path);
                    selectedFile.createNewFile();
                    isNewFile = true;

                } catch (IOException ex) {
                    Logger.getLogger(NotePadFunctional.class.getName()).log(Level.SEVERE, null, ex);
                }
           
        

        
    }

    public NotePadFunctional(Stage s) {
        this.stage = s;
        MmenuBar = new MenuBar();
        fileMenu = new Menu();
        newMenuItem = new MenuItem();
        openMenuItem = new MenuItem();
        saveMenuItem = new MenuItem();
        exitMenuItem = new MenuItem();
        editMenu = new Menu();
        cutMenuItem = new MenuItem();
        copyMenuItem = new MenuItem();
        pasteMenuItem = new MenuItem();
        deleteMenuItem = new MenuItem();
        selectAllMenuItem = new MenuItem();
        helpMenu = new Menu();
        aboutMenuItem = new MenuItem();
        // helpDialogPane = new DialogPane();
        //helpAnchorPane = new AnchorPane();
        //helpAnchorPane2 = new AnchorPane();
        anchorPane = new AnchorPane();
        mTextArea = new TextArea();
        mTextArea.setEditable(true);
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        Alert alertDialg = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save current file ?", yes, no);
        final Alert aboutDialg = new Alert(Alert.AlertType.INFORMATION, "About this app", ok);
        aboutDialg.setContentText("This app is not save for daily work, use is at your own risk XD");

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setPrefWidth(1000.0);
        setPrefHeight(1000.0);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);

        BorderPane.setAlignment(MmenuBar, javafx.geometry.Pos.CENTER);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

        newMenuItem.setMnemonicParsing(false);
        newMenuItem.setText("New");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Optional<ButtonType> result = null;
                if (isNewFile) {
                    //saveFile();
                    result = alertDialg.showAndWait();
                    if (result.get() == yes) {

                        saveFile();
                        openNewFile();
                        mTextArea.clear();

                    } else {
                        openNewFile();
                        mTextArea.clear();
                    }
                }

                /*
                alertDialg.setOnShown( new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                     String type = event.getEventType().getName();
                          
                           if(type == ButtonType.OK.toString()){
                               saveFile();
                           }else{
                            mTextArea.clear();
                           }
                    
                    }
                });
                alertDialg.showAndWait();
                 */
            }
        });

        openMenuItem.setMnemonicParsing(false);
        openMenuItem.setText("Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction((ActionEvent event) -> {
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*.txt"));
            selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                
                try {
                    String path = selectedFile.getPath();
                    //FileInputStream fis = new FileInputStream(path);
                    // FileReader reader = new FileReader(selectedFile);
                    
                    List<String> lines = new ArrayList<String>();
                    
                    lines = Files.readAllLines(selectedFile.toPath(), StandardCharsets.UTF_8);
                    //int size = fis.available();
                    //byte[] b = new byte[size];
                    //fis.read(b);
                    String text = "";
                    
                    Iterator<String> itr = lines.iterator();
                    while (itr.hasNext()) {
                        text += itr.next();
                    }
                    /*
                    while(reader.read() != -1){
                    text.add((char)reader.read());
                    }
                    */
                    mTextArea.setText(text);
                    isNewFile = true;
                    
                    //fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(NotePadFunctional.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        saveMenuItem.setMnemonicParsing(false);
        saveMenuItem.setText("Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveMenuItem.setOnAction((ActionEvent event) -> {
            // fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
            // File dest = fileChooser.showSaveDialog(stage);
            //  File saveFile = new File(get)
            //  if (dest != null)
            saveFile();
        });

        exitMenuItem.setMnemonicParsing(false);
        exitMenuItem.setText("Exit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveFile();
                System.exit(0);
            }
        });

        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

        cutMenuItem.setMnemonicParsing(false);
        cutMenuItem.setText("Cut");
        cutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String cutText = mTextArea.getSelectedText().replace("\n", "");
                if (cutText != null) {
                    content.putString(cutText);
                }
                clipboard.setContent(content);
                mTextArea.deleteText(mTextArea.getSelection());
            }
        });

        copyMenuItem.setMnemonicParsing(false);
        copyMenuItem.setText("Copy");
        copyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String copyText = mTextArea.getSelectedText().replace("\n", "");
                if (copyText != null) {
                    content.putString(copyText);
                }
                clipboard.setContent(content);
            }
        });

        pasteMenuItem.setMnemonicParsing(false);
        pasteMenuItem.setText("Paste");
        pasteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pasteText = clipboard.getString();
                mTextArea.insertText(mTextArea.getCaretPosition(), pasteText);
            }
        });

        deleteMenuItem.setMnemonicParsing(false);
        deleteMenuItem.setText("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mTextArea.deleteText(mTextArea.getSelection());
            }
        });

        selectAllMenuItem.setMnemonicParsing(false);
        selectAllMenuItem.setText("Select All");
        selectAllMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mTextArea.selectAll();
            }
        });

        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonicParsing(false);
        aboutMenuItem.setText("About");

        /*
          helpDialogPane.setMaxHeight(USE_PREF_SIZE);
        helpDialogPane.setMaxWidth(USE_PREF_SIZE);
        helpDialogPane.setMinHeight(USE_PREF_SIZE);
        helpDialogPane.setMinWidth(USE_PREF_SIZE);
        helpDialogPane.setPrefHeight(300.0);
        helpDialogPane.setPrefWidth(480.0);

        helpAnchorPane.setMinHeight(0.0);
        helpAnchorPane.setMinWidth(0.0);
        helpAnchorPane.setPrefHeight(200.0);
        helpAnchorPane.setPrefWidth(320.0);
        //helpDialogPane.setHeader(aboutDialg);

        helpAnchorPane2.setMinHeight(0.0);
        helpAnchorPane2.setMinWidth(0.0);
        helpAnchorPane2.setPrefHeight(200.0);
        helpAnchorPane2.setPrefWidth(320.0);
        helpDialogPane.setContent(helpAnchorPane2);

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(320.0);
        helpDialogPane.setExpandableContent(anchorPane);
         */
        // DialogPane dialogPane = new DialogPane();
        //aboutDialg.setDialogPane(dialogPane);
        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutDialg.showAndWait();
            }
        });
        setTop(MmenuBar);

        BorderPane.setAlignment(mTextArea, javafx.geometry.Pos.CENTER);
        mTextArea.setPrefHeight(200.0);
        mTextArea.setPrefWidth(200.0);
        setCenter(mTextArea);

        fileMenu.getItems().add(newMenuItem);
        fileMenu.getItems().add(openMenuItem);
        fileMenu.getItems().add(saveMenuItem);
        fileMenu.getItems().add(exitMenuItem);
        MmenuBar.getMenus().add(fileMenu);
        editMenu.getItems().add(cutMenuItem);
        editMenu.getItems().add(copyMenuItem);
        editMenu.getItems().add(pasteMenuItem);
        editMenu.getItems().add(deleteMenuItem);
        editMenu.getItems().add(selectAllMenuItem);
        MmenuBar.getMenus().add(editMenu);
        helpMenu.getItems().add(aboutMenuItem);
        MmenuBar.getMenus().add(helpMenu);
        //openNewFile();

    }
}
