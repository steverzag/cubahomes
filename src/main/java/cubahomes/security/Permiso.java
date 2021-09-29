package cubahomes.security;

public enum Permiso {

	PLAN_READ("plan:read"), PLAN_WRITE("plan:write");
	
	private String permiso;
	
	private Permiso(String permiso) {
		this.permiso = permiso;
	}
	
	public String getPermiso() {
		return permiso;
	}
}
