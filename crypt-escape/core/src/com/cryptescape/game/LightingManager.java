package com.cryptescape.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LightingManager {
    private static Filter blockLighting = null;
    private static RayHandler rayHandler;
    private static PointLight playerLight;
    private static ConeLight playerFlashlight;
    
    private static Vector2 mousePosition = new Vector2(0, 0);
    
    public static void createLights() {
        blockLighting = new Filter();
        blockLighting.groupIndex = -2; //Objects with an index of -2 will NEVER collide
        
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(GameScreen.world);
        rayHandler.setAmbientLight(0f);
        rayHandler.setShadows(true);
        rayHandler.setBlur(true);
        
        
        //rays, color, how far out to project. This is the candle
        playerLight = new PointLight(rayHandler, 100, null, 2.5f, GameScreen.player.getX(), GameScreen.player.getY());
        playerLight.setSoftnessLength(2f);
        playerLight.setXray(true);
        
        //This is the flashlight
        playerFlashlight = new ConeLight(rayHandler, 300, Color.WHITE, 7f, GameScreen.player.getX(), GameScreen.player.getY(), 0, 20f);
        playerFlashlight.setSoftnessLength(2f);
        playerFlashlight.setXray(false);
        playerFlashlight.setContactFilter(blockLighting);

    }
    
    
    public static void updateLights() {
        mousePosition.x = (GameScreen.player.getX() - (Constants.VIEWPORT_WIDTH/2)) + (((float)InputHandler.relativeMousePosition.x/Gdx.graphics.getWidth()) * Constants.VIEWPORT_WIDTH);
        mousePosition.y = (GameScreen.player.getY() + (Constants.VIEWPORT_HEIGHT/2)) - (((float)InputHandler.relativeMousePosition.y/Gdx.graphics.getHeight()) * Constants.VIEWPORT_HEIGHT);
        
        playerLight.setPosition(GameScreen.player.getX(), GameScreen.player.getY());
        playerLight.setDistance(GameScreen.player.getCandleLevel());
        
        if(!InputHandler.tab_pressed) {
	        playerFlashlight.setDistance(GameScreen.player.getBatteryLevel());
	        playerFlashlight.setPosition(GameScreen.player.getX(), GameScreen.player.getY());
	        playerFlashlight.setDirection(getAngle(GameScreen.player.getX(), GameScreen.player.getY(), mousePosition));
        }
        else{
        	 playerFlashlight.setDistance(0f);
        	 
        }
	        
        rayHandler.setCombinedMatrix(GameScreen.camera);
        rayHandler.updateAndRender();
    }
    
    
    public static float getAngle(Float x, Float y, Vector2 target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - y, target.x - x));
        if(angle < 0){
            angle += 360;
        }
        return angle;
    }
    
    
    public static void dispose() {
        rayHandler.dispose();
        playerLight.dispose();
        playerFlashlight.dispose();
    }
}
