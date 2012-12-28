package com.layout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BackgroundImageComponent extends JPanel {
	BufferedImage image;

	public BackgroundImageComponent(BufferedImage image) {
		this.image = image;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		int x = (w - imageWidth) / 2; // center the image
		int y = (h - imageHeight) / 2; // in its container
		g.drawImage(image, x, y, this);
	}

	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}
}