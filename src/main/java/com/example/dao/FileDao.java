package com.example.dao;

import java.util.List;

import com.example.domain.File;

public interface FileDao {
	void addFile(int id, File file);
	void deleteFile(int id);
	List<String> fileList();
}
