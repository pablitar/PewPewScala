package com.uqbar.vainilla.appearances;


import java.awt.Color;
import java.awt.Graphics2D;

import com.uqbar.vainilla.GameComponent;

public class FilledArc implements Appearance {

	private final Color color;
	private final int radius;
	private final double startAngle;
	private final double arcAngle;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public FilledArc(Color color, int radius, double startAngle, double arcAngle) {
		this.color = color;
		this.radius = radius;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.radius * 2;
	}

	@Override
	public double getHeight() {
		return this.radius * 2;
	}

	@Override
	@SuppressWarnings("unchecked")
	public FilledArc copy() {
		return new FilledArc(this.color, this.radius, this.startAngle, this.arcAngle);
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		int x = (int) (component.getX() );
		int y = (int) (component.getY() );

		graphics.setColor(this.color);
		graphics.fillArc(x, y, (int) this.getWidth(), (int) this.getHeight(), (int) this.startAngle,
				(int) this.arcAngle);
	}
}