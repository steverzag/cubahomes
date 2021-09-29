package cubahomes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cubahomes.services.StorageService;

@Controller
@RequestMapping("/test/")
public class TestController {

	@Autowired
	private StorageService storage;
	@GetMapping("file")
	public String testFile() {
		
		return "test-pages/file";
	}
	
	@PostMapping(value = "file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String testPostFile(@RequestParam List<MultipartFile> file) {
		System.out.println("++++++++++++++++++");
		storage.storeAll("anounces-photos", file);
		return "redirect:file";
	}
}
