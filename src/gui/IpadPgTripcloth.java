package gui;

import javafx.scene.layout.GridPane;

public class IpadPgTripcloth extends IpadPage {

	private class ClothingPane {
		GridPane clothGrid;

		public ClothingPane() {
			clothGrid = new GridPane();

			clothGrid.setPrefSize(1024, 700);

			clothGrid.setGridLinesVisible(true);

		}
		
	}

	public IpadPgTripcloth() {
		super("tripCloth", "clothing", "Back", "");
		// TODO Auto-generated constructor stub
	}

	@Override
	void leftButtonAction() {
		// TODO Auto-generated method stub

	}

	@Override
	void rightButtonAction() {
		// TODO Auto-generated method stub

	}

	@Override
	void createContent() {
		ClothingPane myCloths = new ClothingPane();
	}

}
