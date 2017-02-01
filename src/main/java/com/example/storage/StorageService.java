package com.example.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init(String id);
	
	void store(MultipartFile file, String id);

    Stream<Path> loadAll(String id);

    Resource loadAsResource(String filename, String id);

    void deleteAll();
}
