package dsa1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent>{
	
	private Scene firstScene, secondScene;
	private Button upload, back, bnw;
	private FileChooser fileChooser;
	private BorderPane borderpane;
	private ImageProcessor imgpro;
	private File file;
	private Stage window;
	private BufferedImage buffImg;
	
	//Stage container
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setScene(uploadScene());
		window.show();
	}
	
	/**
	 * The stage/window for uploading.
	 * @return the stage for picture selection.
	 */
	private Scene uploadScene()
	{
		//Scene title
		Label label = new Label("Upload an arial image of sheep");
		
		//upload button
		upload = new Button("Upload");
		upload.setOnAction(this);
		
		//layout
		borderpane = new BorderPane();
		borderpane.setTop(label);
		borderpane.setCenter(upload);
		
		firstScene = new Scene(borderpane, 200, 200);
		return firstScene;
	}
	
	/**
	 * The main scene where the image will be displayed and where the option to view black and white will be.
	 * @return main scene.
	 * @throws IOException
	 */
	private Scene imageScene() throws IOException
	{
		ImageView imageView = new ImageView();
		Image img = SwingFXUtils.toFXImage(imgpro.resize(buffImg), null);
		imageView.setImage(img);

		borderpane = new BorderPane();
		
		Label label = new Label("Sheep Recognition");
		bnw = new Button("View Black and White");
		back = new Button("Go Back");
		
		back.setOnAction(this);
		bnw.setOnAction(this);
		
		borderpane.setBottom(bnw);
		borderpane.setTop(label);
		borderpane.setTop(back);
		borderpane.setCenter(imageView);
		
		
		secondScene= new Scene(borderpane, buffImg.getWidth(), buffImg.getHeight());
		return secondScene;
	}
	
	/**
	 * Handles all the button clicks.
	 */
	@Override
	public void handle(ActionEvent event)
	{
		imgpro = new ImageProcessor();
		
		//upload button
		if(event.getSource() == upload)
		{
			try {
				chooseImage(); //user picks an image file
				window.setScene(imageScene()); //use main scene
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(event.getSource() == bnw)
		{
			//uses a new window to view the black and white image.
			Stage bnwStage = new Stage();
			borderpane = new BorderPane();
			ImageView imageView = new ImageView();
			
			Image img = SwingFXUtils.toFXImage(imgpro.blackAndWhite(buffImg), null);
			imageView.setImage(img);
			
			borderpane.getChildren().add(imageView);
			Scene bnwScene = new Scene(borderpane, buffImg.getWidth(), buffImg.getHeight());
			bnwStage.setScene(bnwScene);
			bnwStage.show();
		}
		
		//go back to the upload scene.
		else if(event.getSource() == back)
		{
			window.setScene(uploadScene());
		}
		
	}
	
	/**
	 * user picks an image using file chooser. It will only display jpeg and png files
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void chooseImage() throws IOException
	{
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG Files (*.jpg)","*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG Files (*.PNG)","*.PNG");

		file = fileChooser.showOpenDialog(null);
		buffImg = ImageIO.read(file);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
