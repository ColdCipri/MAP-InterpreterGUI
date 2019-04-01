package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.Controller;
import Domain.BarrierTablePair;
import Domain.FileTblItem;
import Domain.HeapItem;
import Domain.IPrgState;
import Domain.SemaphoreTableTuple;
import Domain.SymbTblItem;
import Repository.Repo;
import View.TextMenu;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class GUIController {
	public TextMenu menu;
	
	@FXML
	private ListView<String> examples;

	@FXML
	private ListView<String> prgStates;
	
	@FXML
	private ListView<String> stack;
	
	@FXML
	private ListView<String> output;
	
	@FXML
	private TableView<SymbTblItem> symbtbl;
	
	@FXML
	private TableView<FileTblItem> filetbl;
	
	@FXML
	private TableView<HeapItem> heap;

	@FXML
	private TableView<BarrierTablePair> barriers;

	@FXML
	private TableView<SemaphoreTableTuple> semaphores;
	
	@FXML
	private Button startExecution;
	
	@FXML
	private Button nextStepBtn;
	
	@FXML
	private URL location;
	
	@FXML 
    private ResourceBundle resources;
	
	private Controller currentController;
	
	public GUIController() {
		this.menu = Main.init();
	}
	
	public void loadData() {
		int currentSelection = 0;
		currentSelection = prgStates.getSelectionModel().getSelectedIndex();
		if (currentSelection == -1)
			currentSelection = 0;
		Repo r = currentController.getRepo();
		prgStates.setItems(r.toObservableList(prgStates.getItems()));
		if (prgStates.getItems().size() >= currentSelection)
			prgStates.getSelectionModel().select(currentSelection);
		
		IPrgState state = r.getPrgState().get(currentSelection);
		this.stack.setItems(state.getStack().toObservableList(stack.getItems()));
		this.symbtbl.setItems(state.getSymTable().toObservableList(symbtbl.getItems()));
		this.filetbl.setItems(state.getFileTbl().toObservableList(filetbl.getItems()));
		this.heap.setItems(state.getHeap().toObservableList(heap.getItems()));
		this.output.setItems(state.getOutputObj().toObservableList(output.getItems()));
		this.barriers.setItems(state.getBarrierTable().toObservableList(barriers.getItems()));
		this.semaphores.setItems(state.getSemaphoreTable().toObservableList(semaphores.getItems()));
	}
	
private EventHandler<ActionEvent> startExec = new javafx.event.EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		try {
			String key = examples.getSelectionModel().getSelectedItem().split(" : ")[0];
			currentController = menu.getController(key.trim());
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("There was an error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}
	}
};

private EventHandler<ActionEvent> nextStep = new javafx.event.EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		try {
			String key = examples.getSelectionModel().getSelectedItem().split(" : ")[0];
			currentController = menu.getController(key.trim());
			currentController.oneStepRedirect();
			loadData();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("There was an error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
};

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		examples.setItems(menu.toObservableList(examples.getItems()));
		startExecution.setOnAction(startExec);
		nextStepBtn.setOnAction(nextStep);
		prgStates.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				loadData();
			}
		});
		
		TableColumn<SymbTblItem, String> symbolColumn = new TableColumn<>("Symbol");
		TableColumn<SymbTblItem, Integer> valueColumn = new TableColumn<>("Value");
		symbolColumn.setCellValueFactory(new PropertyValueFactory<>("symbol"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		symbtbl.getColumns().clear();
		symbtbl.getColumns().addAll(symbolColumn, valueColumn);
		
		TableColumn<FileTblItem, Integer> descriptorColumn = new TableColumn<>("File Descriptor");
		TableColumn<FileTblItem, String> fValueColumn = new TableColumn<>("Value");
		descriptorColumn.setCellValueFactory(new PropertyValueFactory<>("file descriptor"));
		fValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		filetbl.getColumns().clear();
		filetbl.getColumns().addAll(descriptorColumn, fValueColumn);
		
		TableColumn<HeapItem, Integer> addressColumn = new TableColumn<>("Address");
		TableColumn<HeapItem, Integer> hValueColumn = new TableColumn<>("Value");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		hValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		heap.getColumns().clear();
		heap.getColumns().addAll(addressColumn, hValueColumn);
		
		TableColumn<BarrierTablePair, Integer> BarrierTablekeyColumn = new TableColumn<>("Key");
		TableColumn<BarrierTablePair, Integer> BarrierTablePartiesColumn = new TableColumn<>("Parties");
		TableColumn<BarrierTablePair, String> barrierTableGUIDsColumn = new TableColumn<>("GUIDs");
		BarrierTablekeyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
		BarrierTablePartiesColumn.setCellValueFactory(new PropertyValueFactory<>("parties"));
		barrierTableGUIDsColumn.setCellValueFactory(new PropertyValueFactory<>("guids"));
		barriers.getColumns().clear();
		barriers.getColumns().addAll(BarrierTablekeyColumn,BarrierTablePartiesColumn,barrierTableGUIDsColumn);
		
		TableColumn<SemaphoreTableTuple, Integer> SemaphoreTablekeyColumn = new TableColumn<>("Key");
		TableColumn<SemaphoreTableTuple, Integer> SemaphoreTablePartiesColumn = new TableColumn<>("Parties");
		TableColumn<SemaphoreTableTuple, String> SemaphoreTableGUIDsColumn = new TableColumn<>("GUIDs");
		SemaphoreTablekeyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
		SemaphoreTablePartiesColumn.setCellValueFactory(new PropertyValueFactory<>("parties"));
		SemaphoreTableGUIDsColumn.setCellValueFactory(new PropertyValueFactory<>("guids"));
		semaphores.getColumns().clear();
		semaphores.getColumns().addAll(SemaphoreTablekeyColumn, SemaphoreTablePartiesColumn, SemaphoreTableGUIDsColumn);
		
	}
	
	@FXML
	private void printOutput() {
		
	}

}
