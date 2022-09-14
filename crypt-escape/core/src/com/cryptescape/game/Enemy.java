package com.cryptescape.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends Movables {
	public boolean isRunning = false;
	private TextureRegion spriteRegion;
	private float counter = 0f;
	private boolean changeAnimation = true;
	int[][] previousRoom;

	public Enemy(double x, double y, Texture t) {
		super(x, y, 3.0, 2.5, t);
	}
	
	//this will be the base of the AI
	public void decideDirection() {
		this.setAccel(0.1, 0.1);
		//this sort of stuff. Want to make a method that somehow
		//decides the direction of the enemy to go in.
	}
	
	//Add more methods to help build complex behavior from simple actions IE:
	public double[] sniff() {
		double[]
		
		
		return 
	}
	

	
	public TextureRegion spriteStage() {
		// Gdx.graphics.getDeltaTime();
		if (changeAnimation == true) {
			changeAnimation = false;
			if (spritePos + 1 < width) {
				spritePos += 1;
			} else {
				spritePos = 0;
			}

			if ((vel[0] >= 1) && ((vel[1] <= 1) && (vel[1] >= -1))) { // East
				spriteRegion.setRegion(0 + 32 * spritePos, 32, 32, 32);

			} else if ((vel[0] <= -1) && ((vel[1] <= 1) && (vel[1] >= -1))) { // West
				spriteRegion.setRegion(0 + 32 * spritePos, 96, 32, 32);

			} else if (((vel[0] <= 1) && (vel[0] >= -1)) && (vel[1] >= 1)) { // North
				spriteRegion.setRegion(0 + 32 * spritePos, 0, 32, 32);

			} else if (((vel[0] <= 1) && (vel[0] >= -1)) && (vel[1] <= -1)) { // South
				spriteRegion.setRegion(0 + 32 * spritePos, 64, 32, 32);

			} else if ((vel[0] > 0) && (vel[1] > 0)) { // Northwest
				spriteRegion.setRegion(0 + 32 * spritePos, 128, 32, 32);

			} else if ((vel[0] > 0) && (vel[1] < 0)) { // Southwest
				spriteRegion.setRegion(0 + 32 * spritePos, 192, 32, 32);

			} else if ((vel[0] < 0) && (vel[1] > 0)) { // Northeast
				spriteRegion.setRegion(0 + 32 * spritePos, 160, 32, 32);

			} else if ((vel[0] < 0) && (vel[1] < 0)) { // Southeast
				spriteRegion.setRegion(0 + 32 * spritePos, 224, 32, 32);

			} else if ((vel[0] == 0) && (vel[1] == 0)) { // Standing still
				spriteRegion.setRegion(0, 64, 32, 32);
			}

		}
		if (counter >= 30 * Gdx.graphics.getDeltaTime()) {
			changeAnimation = true;
			counter = 0;
		}
		counter += Gdx.graphics.getDeltaTime();
		// else return Error Section
		// spriteRegion.setRegion(0 + 32 * spritePos, 160, 32, 32);
		return spriteRegion;

	}

}
