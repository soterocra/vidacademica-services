package online.vidacademica.services.entities.enums;

public enum PostType {
	POST(1),
	COMMENT(2);
	
	private int code;
	
	private PostType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static PostType valueOf(int code) {
		for (PostType value : PostType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid PostType code");
	}
	
}
