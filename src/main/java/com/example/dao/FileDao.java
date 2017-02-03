package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.File;

public interface FileDao extends JpaRepository<File, Long>{

}
