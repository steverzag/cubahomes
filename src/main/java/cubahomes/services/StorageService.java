package cubahomes.services;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public void init();

	public void store(String dir, MultipartFile file);
	
	public void storeAll(String dir, List<MultipartFile> file);

	public Stream<Path> loadAll();

	public Path load(String filename);

	public Resource loadAsResource(String filename);
	
	public boolean delete(String resource);
	
	public void deleteAll(List<String> resources);

	public void deleteAll();

}
