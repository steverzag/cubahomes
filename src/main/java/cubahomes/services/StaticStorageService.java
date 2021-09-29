package cubahomes.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service("staticStorage")
public class StaticStorageService implements StorageService {

	@Override
	public void init() {

	}

	@Override
	public void store(String dir, MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(dir);
		System.out.println(path.toString());
		try {
			if (!Files.exists(path))
				Files.createDirectories(path);
			
			InputStream in = file.getInputStream();
			path = path.resolve(filename);
			Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void storeAll(String dir, List<MultipartFile> files) {
		for (MultipartFile file : files) {
			store(dir, file);
		}
	}

	@Override
	public Stream<Path> loadAll() {

		return null;
	}

	@Override
	public Path load(String filename) {

		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {

		return null;
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public boolean delete(String resource) {
		Path path = Paths.get(resource);
		boolean b = false;
		try {
			b = Files.deleteIfExists(path);
		} catch (IOException e) {
			// TODO: handle exception
		}
		return b;
	}

	@Override
	public void deleteAll(List<String> resources) {
		resources.forEach(r -> delete(r));
	}

}
