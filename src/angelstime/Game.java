package angelstime;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import angelstime.SpriteTween;

	public class Game implements ApplicationListener {
		 private OrthographicCamera camera;
		    private SpriteBatch batch;
		    private Texture texture;
		    private Sprite sprite1;
		    private TweenManager manager1;
		    private long startTime;
		    private long delta;
		    private float w,h;
		@Override
	    public void create() {        
			w = Gdx.graphics.getWidth();
	        h = Gdx.graphics.getHeight();
	        
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, 640, 480);
	        batch = new SpriteBatch();
	        
	        texture = new Texture("data/quads.png");
	        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
     
	        sprite1 = new Sprite(texture);
	        sprite1.setPosition(50,0.25f*h);

	        
	        Tween.registerAccessor(Sprite.class, new SpriteTween());
	        manager1 = new TweenManager();
	        Tween.to(sprite1,SpriteTween.POSITION_Y,1000f) //** tween POSITION_X for a duration **//
	            .target(w-100) // ** final POSITION_X **//
	            .ease(TweenEquations.easeInOutQuad) //** easing equation **//
	            .repeat(1000,1000f) //** ten more times **//
	            .start(manager1); //** start it
	     
	        startTime = TimeUtils.millis();
	    
			
		
		}

	    @Override
	    public void dispose() {
	        batch.dispose();
	        texture.dispose();
	        //font.dispose();
	    }

	    @Override
	    public void render() {        
	    	Gdx.gl.glClearColor(0, 1, 1, 1);
	        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        delta = (TimeUtils.millis()-startTime)/1000; // **get time delta **//
	        manager1.update(delta); //** update sprite1 **//
	        batch.setProjectionMatrix(camera.combined);
	        batch.begin();
	        sprite1.draw(batch);	     
	        batch.end();
	        
	        double u = 10;
	        for(int i = 0; i < 10000000; i++)
	        {
	        	u /= i + 1;
	        	System.out.println(u);
	        }
	    }

	    @Override
	    public void resize(int width, int height) {
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }
}
