package hu.virgo.selenium.framework.context;

public class SystemUnderTest {
	private SutType type;
	private String baseUrl;
	private String adminBaseUrl;

	public SystemUnderTest(SutType type, String baseUrl, String adminBaseUrl) {
		this.type = type;
		this.baseUrl = baseUrl;
	}

	public SutType getType() {
		return type;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getAdminBaseUrl() {
		return adminBaseUrl;
	}

}
