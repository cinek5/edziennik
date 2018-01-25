package com.cinek.edziennik.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cinek.edziennik.model.ProfilePictureFile;
import com.cinek.edziennik.repository.FileUploadRepository;
import com.cinek.edziennik.service.UserService;

@Controller
public class UploadFileController {
	@Autowired
    UserService userService;
	@Autowired
	FileUploadRepository fileUploadRepository;
	 @RequestMapping(value = "/doUpload", method = RequestMethod.GET)
	    public String showUploadForm(HttpServletRequest request) {
	        return "upload";
	    }
	     
	    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	    public String handleFileUpload(HttpServletRequest request,
	            @RequestParam CommonsMultipartFile fileToUpload) throws Exception {
	          
	        if (fileToUpload != null) {
	        	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    		String username = user.getUsername();
	    		com.cinek.edziennik.model.User userModel = userService.findByUsername(username);
	                  
	                System.out.println("Saving file: " + fileToUpload.getOriginalFilename());
	                 
	                ProfilePictureFile uploadFile = new  ProfilePictureFile();
	                uploadFile.setFileName("profilePic"+userModel.getId()+"."+fileToUpload.getOriginalFilename().split("\\.")[1]);
	                uploadFile.setData(fileToUpload.getBytes());
	                
	                userService.addProfilePictureToUser(userModel, uploadFile);              
	               
	        }
	  
	        return "redirect:/";
	    }  
	    @RequestMapping(value="download/{imageId}",method= RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
	    @ResponseBody
	    public byte[] downloadImage(@PathVariable Long imageId) { 
	    
	    	ProfilePictureFile image = fileUploadRepository.getProfilPictureFileById(imageId);
	    	if (image!=null)
			return image.getData();
	    	else return null;
	    }
}

