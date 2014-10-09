package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.Field;
import model.IGameState;
import util.Settings;

public class GraphicRepresentation extends JPanel {
	private static final long serialVersionUID = 3156990937873127822L;
	private boolean win;
	private boolean loss;
	private IGameState gs;

	public GraphicRepresentation() {
		this.win = false;
		this.loss = false;
	}

	public void updateGameState(IGameState gs) {
		this.gs = gs;
	}

	public void win() {
		if (loss)
			throw new IllegalStateException("You can't win and loose at the same time!");
		this.win = true;
	}

	public void loose() {
		if (win)
			throw new IllegalStateException("You can't win and loose at the same time!");
		this.loss = true;
	}

	@Override
	public void paint(Graphics g) {
		if (gs != null) {
			fillBackground(g);
			paintSnake(g);
			paintFood(g);
			if (loss)
				paintLoss(g);
			if (win)
				paintWin(g);
		}
	}

	public void fillBackground(Graphics g) {
		g.setColor(Settings.backgroundColor);
		g.fillRect(0, 0, Settings.windowSize, Settings.windowSize);
	}

	public void paintSnake(Graphics g) {
		int fieldSize = Settings.windowSize/Settings.fields;
		g.setColor(Color.white);
		for (Field f: gs.getSnake())
			g.fillRect(fieldSize*f.x + 1, fieldSize*f.y + 1, fieldSize - 2, fieldSize - 2);
	}

	public void paintFood(Graphics g) {
		int fieldSize = Settings.windowSize/Settings.fields;
		GraphicsConfiguration gc = ((Graphics2D) g).getDeviceConfiguration();
		BufferedImage img = gc.createCompatibleImage(fieldSize, fieldSize, Transparency.TRANSLUCENT);
		Graphics2D g2 = img.createGraphics();
		g2.setComposite(AlphaComposite.Clear);
		g2.fillRect(0, 0, fieldSize, fieldSize);
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillOval(0, 0, fieldSize-4, fieldSize-4);
		g2.dispose();
		g.drawImage(img, gs.getFoodField().x*fieldSize+2, gs.getFoodField().y*fieldSize+2, null);
	}

	public void paintWin(Graphics g) {
		g.setColor(Settings.winMessageColor);
		FontMetrics fm = g.getFontMetrics();
		g.setFont(new Font("", Settings.winMessageStyle, Settings.winMessageSize));
		int x = (Settings.windowSize - fm.stringWidth(Settings.winMessage))/2;
		g.drawString(Settings.winMessage, x, Settings.winMessageOffset);
	}

	public void paintLoss(Graphics g) {
		g.setColor(Settings.lossMessageColor);
		g.setFont(new Font("", Settings.lossMessageStyle, Settings.lossMessageSize));
		FontMetrics fm = g.getFontMetrics();
		int x = (Settings.windowSize - fm.stringWidth(Settings.lossMessage))/2;
		g.drawString(Settings.lossMessage, x, Settings.lossMessageOffset);
	}
}
