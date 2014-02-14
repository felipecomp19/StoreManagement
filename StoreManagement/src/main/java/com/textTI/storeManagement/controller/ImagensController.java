package com.textTI.storeManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.textTI.storeManagement.manager.ImagensManager;
import com.textTI.storeManagement.model.FileUpload;

@Controller
@RequestMapping(value="/imagens")
public class ImagensController extends BaseController {

	@Autowired
	private ImagensManager imgManager;
	
	@RequestMapping(value="/list",  method = RequestMethod.GET)
	public String imagens(Model model)
	{
		List<String> imagens = this.imgManager.getAllImagens();
        model.addAttribute("imagens", imagens);
		
		return "/imagens/list";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload()
	{
		return "/imagens/upload";
	}
	
	@RequestMapping(value = "/uploadImagens", method = RequestMethod.POST)
    public String uploadImagens(@ModelAttribute("uploadForm") FileUpload uploadForm, Model model) {
        List<MultipartFile> files = uploadForm.getFiles();
 
        if(files != null && files.size() > 0){
			try {
				this.imgManager.save(files);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", "Erro ao importar imagens");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", "Erro ao importar imagens");
				e.printStackTrace();
			}
        }
        
        List<String> imagens = this.imgManager.getAllImagens();
        model.addAttribute("imagens", imagens);
        
        
        return "/imagens/list";
    }
}
