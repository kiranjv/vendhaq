package com.layout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class RoundJPanel extends JPanel {
	private Color background = Color.white;
	private int arcw;
	private int arch;
	public RoundJPanel(Color background, int arcw, int arch) {
		this.background = background;
		this.arcw = arcw;
		this.arch = arch;
	}
	
	
	
    @Override
    protected void paintComponent(Graphics g) {
        // Prepare a red rectangle
        BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gb = bi.createGraphics();
        gb.setPaint(Color.white);
        gb.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        gb.dispose();

        // Set a rounded clipping region:
        RoundRectangle2D r = new RoundRectangle2D.Float(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.arcw, this.arch);
        g.setClip(r);

        // Draw the rectangle (and see whether it has round corners)
        g.drawImage(bi, 0, 0, null);
    }
}