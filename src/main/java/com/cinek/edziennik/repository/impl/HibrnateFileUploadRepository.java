package com.cinek.edziennik.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cinek.edziennik.model.ProfilePictureFile;
import com.cinek.edziennik.repository.FileUploadRepository;
@Transactional
@Repository
public class HibrnateFileUploadRepository implements FileUploadRepository{
@PersistenceContext
EntityManager entityManager;
	@Override
	public void save(ProfilePictureFile profilPictureFile) {
		entityManager.persist(profilPictureFile);
		
	}
	@Override
	public ProfilePictureFile getProfilPictureFileById(Long imageId) {
		ProfilePictureFile image = entityManager.find(ProfilePictureFile.class, imageId);
		return image;
	}

}
