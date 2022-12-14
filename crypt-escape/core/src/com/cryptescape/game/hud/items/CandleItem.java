package com.cryptescape.game.hud.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.cryptescape.game.GameScreen;

public class CandleItem extends InventoryItem {
    
    public CandleItem(World world, String name, float x, float y, int zindex) {
        super(world, name, new TextureRegion(GameScreen.atlas.findRegion(name)), x, y, 2f, zindex);      
        super.makeSquareFixture(world, x, y, 1f);
    }
    
    @Override
    public void act(float delta){
        super.defaultAct(delta);
    }
}
