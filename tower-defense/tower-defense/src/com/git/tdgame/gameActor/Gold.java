package com.git.tdgame.gameActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Gold extends Actor
{
	// Actor variables
    private final int WIDTH 	= 32;
    private final int HEIGHT 	= 32;
    private int cash = 0;
    private float incomeTimer = 0; // Add 1 gold each second
    
    // Sprite variables
    private Texture texture;
    private Sprite sprite;
    private double spritePos = 0;
    private int numberOfFrames = 0;
    
    private BitmapFont font;

    public Gold (Vector2 position, int cash)
    {
    	this.setWidth(WIDTH);
    	this.setHeight(HEIGHT);
    	this.cash = cash;
    	
    	setPosition(position.x,position.y);
    	
    	texture = new Texture(Gdx.files.internal("data/game/gui/gold.png"));
    	numberOfFrames = (int)(texture.getWidth()/WIDTH);
        sprite = new com.badlogic.gdx.graphics.g2d.Sprite(texture,WIDTH,HEIGHT);
        font = new BitmapFont(true);
        font.setColor(0.9f, 0.9f, 0, 1);
    	font.setScale(1.5f, 1.5f);
    }

    public void draw (SpriteBatch batch, float parentAlpha)
    {
    	// Move sprite region
    	spritePos = (spritePos+0.2) % numberOfFrames;
    	sprite.setRegion((int)spritePos*WIDTH, 0, WIDTH, HEIGHT);
    	
    	batch.draw(sprite,getX(),getY()+HEIGHT,getOriginX(),getOriginY(),WIDTH,HEIGHT,1,-1,0);
    	
    	font.draw(batch, new String(cash + " g"), WIDTH, getY()+HEIGHT/4);
    }

    public void act (float delta)
    {
    	incomeTimer += delta*1000;
    	
    	if(incomeTimer > 1000)
    	{
    		this.cash += 1;
    		incomeTimer -= 1000;
    	}
    	super.act(delta);
    }
    
	public void addGold(int gold)
	{
		this.cash += gold;
	}
	
	public boolean spentGold(int gold)
	{
		if(this.cash >= gold)
		{
			this.cash -= gold;
			return true;
		}
		return false;
	}
	
	public boolean hasEnoughGold(int gold)
	{
		if(this.cash >= gold)
		{
			return true;
		}
		return false;
	}


}
