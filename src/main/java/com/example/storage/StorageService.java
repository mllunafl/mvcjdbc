package com.example.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.File;

public interface StorageService {

	void init(Long id);
	
	void store(Long id, MultipartFile file);

    Stream<Path> loadAll(Long id);

    Resource loadAsResource(Long id,String filename );

    void deleteAll();
}
