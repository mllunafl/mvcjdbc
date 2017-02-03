package com.example.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.FileDao;
import com.example.domain.File;



@Service
public class StorageServiceImpl implements StorageService {

	private final Path rootLocation;
	
    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

	@Override
	public void store(Long id, MultipartFile file) {
		Path idPath = Paths.get(this.rootLocation + "/" + id);
		System.out.println("/n!!!/n!!!/n!!!"+idPath);
		try{
			Files.createDirectories(idPath);
		}catch(IOException e){
			throw new StorageException("Couldn't initialize");
		}
		
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			// don't want to read all the file and then write it to memory,
			// stream less memory
			Files.copy(file.getInputStream(), idPath.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Stream<Path> loadAll(Long id) {
		Path idPath = Paths.get(this.rootLocation + "/" + id);
			this.init(id);
	
		try {
			return Files.walk(idPath, 1).filter(path -> !path.equals(idPath))
					.map(path -> idPath.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Resource loadAsResource(Long id, String filename) {
		try {
			System.out.println("filename is" + filename + "for id" + id);
			Path file = rootLocation.resolve(id + "/"+ filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}
	
	@Override
	public void init(Long id) {
		Path idPath = Paths.get(this.rootLocation + "/" + id);
		System.out.println("/n!!!/n!!!/n!!!"+idPath);
		try{
			Files.createDirectories(idPath);
		}catch(IOException e){
			throw new StorageException("Couldn't initialize");
		}
	}
	
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
