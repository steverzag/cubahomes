package cubahomes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application.dirs")
@Configuration
public class DirConfig {
	
	private String anounceImages;
	private String anounceIcons;
	
	public DirConfig() {
	}
	
	public String getAnounceImages() {
		return anounceImages;
	}
	public void setAnounceImages(String anounceImages) {
		this.anounceImages = anounceImages;
	}
	public String getAnounceIcons() {
		return anounceIcons;
	}
	public void setAnounceIcons(String anounceIcons) {
		this.anounceIcons = anounceIcons;
	}

	
}
