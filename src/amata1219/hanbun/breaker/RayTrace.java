package amata1219.hanbun.breaker;

import java.util.ArrayList;

import org.bukkit.util.Vector;

public class RayTrace {

	Vector origin, direction;

	RayTrace(Vector origin, Vector direction) {
		this.origin = origin;
		this.direction = direction;
	}

	public Vector getPostion(double blocksAway) {
		return origin.clone().add(direction.clone().multiply(blocksAway));
	}

	public boolean isOnLine(Vector position) {
		double t = (position.getX() - origin.getX()) / direction.getX();
		;
		if (position.getBlockY() == origin.getY() + (t * direction.getY()) && position.getBlockZ() == origin.getZ() + (t * direction.getZ())) {
			return true;
		}
		return false;
	}

	public ArrayList<Vector> traverse(double blocksAway, double accuracy) {
		ArrayList<Vector> positions = new ArrayList<Vector>();
		for (double d = 0; d <= blocksAway; d += accuracy) {
			positions.add(getPostion(d));
		}
		return positions;
	}

	public Vector positionOfIntersection(Vector min, Vector max, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, min, max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(Vector min, Vector max, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, min, max)) {
				return true;
			}
		}
		return false;
	}

	public static boolean intersects(Vector position, Vector min, Vector max) {
		if (position.getX() < min.getX() || position.getX() > max.getX()) {
			return false;
		} else if (position.getY() < min.getY() || position.getY() > max.getY()) {
			return false;
		} else if (position.getZ() < min.getZ() || position.getZ() > max.getZ()) {
			return false;
		}
		return true;
	}

}
