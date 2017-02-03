package com.example.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.File;

public interface StorageService {

	void init(Integer id);
	
	void store(Integer id, MultipartFile file);

    Stream<Path> loadAll(Integer id);

    Resource loadAsResource(Integer id,String filename );

    void deleteAll();
}
