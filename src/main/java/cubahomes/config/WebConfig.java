package cubahomes.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private DirConfig dirsConfig;
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("http://localhoslt:8080");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory(dirsConfig.getAnounceImages(), registry);
	}

	private void exposeDirectory(String path, ResourceHandlerRegistry registry) {
		
		Path uploadDir = Paths.get(path);
		String uploadPath = uploadDir.toFile().toURI().toString();
		System.out.println(uploadPath);
		if(path.startsWith("../")) path.replace("../", "");
		
		registry.addResourceHandler("/" + path + "/**").addResourceLocations(uploadPath);
	}
}
