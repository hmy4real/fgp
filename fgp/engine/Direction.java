package fgp.engine;

/**
 * @author Mr. Hapke
 *
 */
public enum Direction {
	//@formatter:off
	None     ( 0,  0),
	
	Up       ( 0, -1), 
	Down     ( 0,  1), 
	Left     (-1,  0), 
	Right    ( 1,  0), 
	
	UpLeft   (-1, -1),
	UpRight  ( 1, -1), 
	DownLeft (-1,  1),
	DownRight( 1,  1);
	//@formatter:on

	public final int dx;
	public final int dy;

	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public char prettyArrow() {
		switch (this) {
		case None:
			return ' ';

		case Up:
			return '↑';
		case Down:
			return '↓';
		case Left:
			return '←';
		case Right:
			return '→';

		case UpLeft:
			return '↖';
		case UpRight:
			return '↗';
		case DownLeft:
			return '↙';
		case DownRight:
			return '↘';
		}
		return ' ';
	}
}
