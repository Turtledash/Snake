package view;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.IGameState;
import util.Settings;
import controller.Direction;
import controller.Event;

public class GUI implements IGUI{
	DirectionManager dm;
	GraphicRepresentation gr;
	AudioManager am;

	public GUI() {
		am = new AudioManager();
		am.playBackgroundMusik();
		gr = new GraphicRepresentation();
		dm = new DirectionManager();
		gr.setPreferredSize(new Dimension(Settings.windowSize, Settings.windowSize));
		JFrame frame = new JFrame(Settings.windowName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(gr);
		frame.pack();
		frame.setVisible(true);
		gr.addKeyListener(dm);
		frame.addKeyListener(dm);
		gr.setFocusable(true);
	}

	@Override
	public void repaint() {
		gr.repaint();
	}

	@Override
	public void update(IGameState gs, Event e) {
		//Update graphics
		gr.updateGameState(gs);
		if (e.win)
			gr.win();
		if (e.loss)
			gr.loose();
		repaint();

		//Update directions
		if (e.hasMoved)
			dm.updateDirection();

		//Update Sounds
		if (e.pickedUpFood)
			am.playFoodSound();
		if (e.win) {
			am.stopBackgroundMusik();
			am.playWinSound();
		}
		if (e.loss) {
			am.stopBackgroundMusik();
			am.playLossSound();
		}

	}

	@Override
	public Direction getDirection() {
		return dm.getDirection();
	}
}
