package com.upc.ia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.upc.ia.entity.CarPlate;
import com.upc.ia.repository.CarPlateRepository;
import com.upc.ia.service.GoogleVisionService;

@Controller
@RequestMapping("/carplates/")
public class CarPlateController {

	@Autowired
	private CarPlateRepository carPlateRepository;
	
	@Autowired
	private GoogleVisionService googleVisionService;

	@GetMapping("showForm")
	public String showForm(CarPlate carplate) {
		return "add-carplate";
	}

	@GetMapping("list")
	public String carplates(Model model) {
		model.addAttribute("carplates", this.carPlateRepository.findAll());
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@RequestParam MultipartFile file) {

		try {
			// String path = session.getServletContext().getRealPath("/");
			String filename = file.getOriginalFilename();
			// Process image with google vision
			googleVisionService.processImage(file);
			
			System.out.println("filename: " + filename);
			CarPlate carPlate = new CarPlate("test", filename);
			this.carPlateRepository.save(carPlate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list";
	}

	@PostMapping("add2")
	public String add2(@Valid CarPlate carplate, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-carplate";
		}

		this.carPlateRepository.save(carplate);
		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {

		CarPlate carplate = this.carPlateRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid carplate id : " + id));

		this.carPlateRepository.delete(carplate);
		model.addAttribute("carplates", this.carPlateRepository.findAll());
		return "index";

	}
}
