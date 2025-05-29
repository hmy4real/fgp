package fgp.game.bodies;
 
import java.awt.event.KeyEvent;

import fgp.engine.Direction;
import fgp.engine.Layer;
import fgp.game.layers.*;
import fgp.engine.LoadImage;
import fgp.engine.bodies.BodySimple;
import fgp.engine.bodies.IBodyPart;
import fgp.engine.inputs.Keyboarder;
import fgp.engine.inputs.Mouser;
import fgp.engine.inputs.MouserEvent;
import fgp.game.constants.ZIndexes;
 
@LoadImage("ship0.png")
public class ShipHero extends BodySimple implements Keyboarder, Mouser {
 
	private int mushroomsCollected;
	private int koopasDomesticated;

	public ShipHero(Layer l, int x, int y) {
		super(l, x, y, ZIndexes.PLAYER);
	}
	
	
	public void hitBadThing(IBodyPart k) {
		if (mushroomsCollected > 0) {
			k.markForRemoval();
			mushroomsCollected--;
			koopasDomesticated++;
		} else {
			game.lifeLost();
		}
	}
 
	@Override
	protected boolean collision(IBodyPart myPart, IBodyPart otherPart, int dx, int dy) {
		// TODO Auto-generated method stub
		if (otherPart instanceof Void) {
			return false;
		}
		
		if (otherPart instanceof Mushroom) {
			Mushroom m = (Mushroom) otherPart;
			m.markForRemoval();
			mushroomsCollected++;
		}
		if (otherPart instanceof Koopa) {
			Koopa k = (Koopa) otherPart;
			if (mushroomsCollected > 0) {
				k.markForRemoval();
				mushroomsCollected--;
				getImageTracker().spriteIndex = 0;
				koopasDomesticated++;
			} else {
				game.lifeLost();
			}
		}
		
		
		if (otherPart instanceof Door) {
			Door d = (Door) otherPart;
			if (game.getCurrentLevel() == 1 && koopasDomesticated == 1) {
				game.nextLevel();
			}
		}
		return false;
	}

	@Override
	public void keyDown(int keycode, int modifiers) {
		// TODO Auto-generated method stub
		if (keycode == KeyEvent.VK_A || keycode == KeyEvent.VK_LEFT) {
			move(Direction.Left);
		}
		if (keycode == KeyEvent.VK_D || keycode == KeyEvent.VK_RIGHT) {
			move(Direction.Right);
		}
		if (keycode == KeyEvent.VK_W || keycode == KeyEvent.VK_UP) {
			move(Direction.Up);
		}
		if (keycode == KeyEvent.VK_S || keycode == KeyEvent.VK_DOWN) {
			move(Direction.Down);
		}
	}

	@Override
	public void keyUp(int keycode, int modifiers) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDown(MouserEvent me) {
		((CharactersLayer) layer).shootBullet();
		
	}


	@Override
	public void mouseUp(MouserEvent me) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMove(MouserEvent me) {
		// TODO Auto-generated method stub
		
	}
}