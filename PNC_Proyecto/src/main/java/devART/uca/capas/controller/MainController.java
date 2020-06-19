package devART.uca.capas.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import devART.uca.capas.domain.Estudiante;
import devART.uca.capas.dto.EstudianteDTO;
import devART.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/listado")
	public ModelAndView listado() 
	{
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try 
		{
			estudiantes = estudianteService.findAll();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	@PostMapping("/MostrarDTO")
	public ModelAndView MostrarDTO() 
	{
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try 
		{
			estudiantes = estudianteService.findAll();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		mav.setViewName("MostrarDTO");
		mav.addObject("estudiantes", estudiantes);
		return mav;
	}
	@RequestMapping("/inicio")
	public ModelAndView inicio() 
	{
		ModelAndView mav = new ModelAndView();	
		Estudiante estudiante = new Estudiante();
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/formEstudiante")
	public ModelAndView formEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) 
	{	
		ModelAndView mav = new ModelAndView();	
		if(!result.hasErrors()) 
		{
			try 
			{
				estudianteService.insert(estudiante);	
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);	
			mav.addObject("message", "Estudiante Guardado!");
		}
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/modificarEstudiante")
	public ModelAndView modificarEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result)
	{	
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;	
		if(!result.hasErrors()) 
		{
			try {		
				estudianteService.insert(estudiante);
				estudiantes = estudianteService.findAll();
			}catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("message", "Estudiante Modificado!");
			mav.addObject("estudiantes", estudiantes);
			mav.setViewName("listado");
		}
		else {
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);
			mav.setViewName("index");
		}
		return mav;
	}
	
	@PostMapping(value="/bModificar", params="action=Borrar")
	public ModelAndView eliminarEstudiante(@RequestParam(value="codigo") int codigo) {
		
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;

		try {
			
			estudianteService.delete(codigo);
			estudiantes = estudianteService.findAll();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
		
	}
	
	@PostMapping(value="/bModificar", params="action=Editar")
	public ModelAndView redirectModificarEstudiante(@RequestParam(value="codigo") int codigo) 
	{	
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		try 
		{
			estudiante = estudianteService.findOne(codigo);
			mav.addObject("estudiante", estudiante);
			mav.setViewName("modificar");	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			estudiante = new Estudiante();			
			mav.addObject("estudiante", estudiante);
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping(value="/filtrar", method=RequestMethod.POST)
	public ModelAndView filtrar(@RequestParam(value="nombre") String cadena) 
	{	
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try 
		{	
			estudiantes = estudianteService.filtrarPor(cadena);			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}

}