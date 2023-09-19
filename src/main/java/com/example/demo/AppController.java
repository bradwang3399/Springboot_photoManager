package com.example.demo;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AppController {
	@Autowired
    private PhotoService service;
     
	@RequestMapping("/")
    public String viewHomePage(Model model) {
		List<Photo> listPhotos = service.listAll();
		model.addAttribute("listPhotos", listPhotos);
    	return "index";
    }
	

	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Photo photo = new Photo();
		model.addAttribute("photo", photo);
		
		return "new_photo";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("photo") Photo photo,
			RedirectAttributes ra,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    photo.setimage(fileName);
	    
	    Photo savedPhoto = service.save(photo);
	    String uploadDir = "/Users/mincongwang/Desktop/new";
	    
	    Path uploadPath = Paths.get(uploadDir);
	    
	    if(!Files.exists(uploadPath)) {
	    	Files.createDirectories(uploadPath);
	    }
	    
	    try(InputStream inputStream = multipartFile.getInputStream()){
	    Path filePath = uploadPath.resolve(fileName);
	    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	    }catch (IOException e) {
	    	throw new IOException("Could not save upload file: " + fileName);
	    }
	    
	    ra.addFlashAttribute("message", "Photo uploaded");
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit");
	    Photo product = service.get(id);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
}