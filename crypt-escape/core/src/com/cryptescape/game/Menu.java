package com.cryptescape.game;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu implements Screen {

	final MainCE game;
	private Stage stage;
	private static int size[];
	OrthographicCamera camera;

	public Menu(final MainCE gam) {
		game = gam;
		stage = new Stage(new ScreenViewport());
		size = new int[] { 24, 24 }; // testing constants

		camera = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		camera.position.set(Constants.CAMERA_WIDTH / 2, Constants.CAMERA_HEIGHT / 2, 0);
		camera.update();

		
		//Temp disabled
//		Scanner s = new Scanner(System.in);
//		System.out.println("Do you want launch a graphical menu? (y/n)");
//		if (s.nextLine().equals("y")) {

			// All this is a temp menu start screen.
			Skin skin = new Skin(Gdx.files.internal("Old Assets/skin/glassy-ui.json"));
			Label title = new Label("Crypt Escape V1.1", skin, "big-black");
			title.setAlignment(Align.center);
			title.setY(Gdx.graphics.getHeight() * 2 / 3);
			title.setWidth(Gdx.graphics.getWidth());
			stage.addActor(title);

			TextButton playButton = new TextButton("Start!", skin);
			playButton.setWidth(Gdx.graphics.getWidth() / 2);
			playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
					Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2);
			playButton.addListener(new InputListener() {
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					game.setScreen(new GameScreen(game));
				}

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
			});

			stage.addActor(playButton);

//		} else {
//			game.setScreen(new GameScreen(game));
//		}
//		s.close();
		
	
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0.2f, 0.8f, 1);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		stage.act();
		stage.draw();

//
//		old title screen
//		game.batch.begin();
//		game.font.draw(game.batch, "Crypt Escape V1.1", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-300);
//		game.font.draw(game.batch, "Click anywhere to begin!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-400);
//		game.font.draw(game.batch, Integer.toString(Gdx.graphics.getWidth()), 100, 100);
//		game.batch.end();
//
//		if (Gdx.input.isTouched()) {
//			game.setScreen(new GameScreen(game));
//			dispose();
//		}
	}

	// Y, X map size gen
	public static int[] getSize() {
		if (size[0] >= 3 && size[1] >= 3) {
			return size;
		} else {
			return new int[] { 3, 3 };
		}

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}