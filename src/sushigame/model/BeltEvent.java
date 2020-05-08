package sushigame.model;

abstract public class BeltEvent {
	public enum EventType {PLATE_PLACED, PLATE_CONSUMED, PLATE_SPOILED, ROTATE}

	private EventType type;

	public BeltEvent(EventType type) {
		this.type = type;
	}

	public EventType getType() {
		return type;
	}
}
