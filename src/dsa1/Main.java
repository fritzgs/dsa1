package dsa1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent>{
	
	private Scene firstScene, secondScene;
	private Button upload, back, bnw, estimateTotal;
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
		Label label = new Label("Upload an Image");
		Label bot = new Label("DSA1- Fritz Santos");
		
		//upload button
		upload = new Button("Upload");
		upload.setOnAction(this);
		
		//layout
		borderpane = new BorderPane();
		borderpane.setTop(label);
		borderpane.setBottom(bot);
		borderpane.setCenter(upload);
		
		firstScene = new Scene(borderpane, 200, 200);
		return firstScene;
	}
	
	/**
	 * The main scene where the image will be displayed and where the option to view black and white will be.
	 * @return main scene.
	 * @throws IOException
	 */
	private Scene imageScene(String s) throws IOException
	{
		ImageView imageView = new ImageView();
		Image img = SwingFXUtils.toFXImage(imgpro.resize(buffImg), null);
		imageView.setImage(img);		
		
		Label label = new Label("Sheep Recognition - Fritz Gerald Santos");
		bnw = new Button("View Black and White");
		back = new Button("Go Back");
		estimateTotal = new Button("Total");
		
		estimateTotal.setOnAction(this);
		back.setOnAction(this);
		bnw.setOnAction(this);
		
		borderpane = new BorderPane();

		HBox topLeft = new HBox(back);
		HBox topRight = new HBox(label);
		topRight.setAlignment(Pos.CENTER_RIGHT);
		HBox.setHgrow(topRight, Priority.ALWAYS);
		
		HBox right = new HBox(new Label("Amount of sheeps:  "),new Label(s), new Label("  "), estimateTotal);
		right.setAlignment(Pos.CENTER_RIGHT);
		HBox.setHgrow(right, Priority.ALWAYS);
		
		HBox left = new HBox(bnw);
		
		HBox top = new HBox(topLeft, topRight);
		HBox bottom = new HBox(left, right);
		
		borderpane.setBottom(bottom);
		borderpane.setTop(top);
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
				window.setScene(imageScene("?")); //use main scene
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
			buffImg = null;
			window.setScene(uploadScene());
		}
		else if(event.getSource() == estimateTotal)
		{
			try {
				BufferedImage resized = imgpro.resize(buffImg);
				imgpro.adjacentJoin(imgpro.blackAndWhite(resized));
				String total = String.valueOf(imgpro.getSheepTotal());
				window.setScene(imageScene(total));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
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
