package chienlvm.fsoft.vn.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/getImage")
public class FileController {

	@Value("${rootPath}")
	private String rootPath;

	@Autowired
	public FileController(@Value("${rootPath}") String rootPath) {
		this.rootPath = rootPath;
	}

	@GetMapping("/{filename}")
	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
		byte[] image = new byte[0];
		try {
			File uploadRootDir = new File(rootPath + filename);
			if (!uploadRootDir.exists()) {
				// image 404
				image = FileUtils.readFileToByteArray(new File(rootPath + "404.jpg"));
			} else {
				image = FileUtils.readFileToByteArray(new File(rootPath + filename));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
	}

}