package GUI;

import java.io.FileInputStream;

import View.TextMenu;
import application.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUIMain extends Application{
	TextMenu menu;

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.menu = Main.init();
		FXMLLoader loader = new FXMLLoader();
		String path = "src/gui/main.fxml";
		FileInputStream fxmlStream = new FileInputStream(path);

		Parent root;
		try {
			root = loader.load(fxmlStream);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + e.getLocalizedMessage());
			e.printStackTrace();
			return;
		}

		Scene mainScene = new Scene(root,Color.TRANSPARENT);
		
		primaryStage.setTitle("ToyLanguage");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
