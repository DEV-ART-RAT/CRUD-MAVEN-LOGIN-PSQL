package devART.uca.capas.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import devART.uca.capas.domain.Expediente;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.service.ExpedienteServiceImpl;
import devART.uca.capas.service.MateriaService;
import devART.uca.capas.service.UserDetailsServiceImpl;
import devART.uca.capas.utils.WebUtils;
 
@Controller
public class UserController {
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	ExpedienteServiceImpl expedienteService;
	
	@Autowired
	MateriaService materiaService;
 
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName );
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        return "userInfoPage";
    }
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Ingreso No Autorizado <br>" + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    
    @RequestMapping("/ingresarMateria")
	public ModelAndView ingresarMateria() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("materia", new Materia());
		mav.setViewName("ingresarMateria");
		return mav;
	}
	
	@PostMapping("/nuevaMateria")
	public ModelAndView newMateria(@Valid @ModelAttribute Materia materia, BindingResult result) {
		
		ModelAndView mav = new ModelAndView(); 
		if(!result.hasErrors()) {
			try {
				materiaService.insert(materia);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			materia = new Materia();
			mav.addObject("materia", materia); 
			
		}
		mav.setViewName("ingresarMateria");
		return mav;
	}

    //cordinador
    @RequestMapping(value = { "/coordi" }, method = RequestMethod.GET)
    public String coordinadorpage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Expediente/coordinador";
    }
    @RequestMapping(value = { "/expediente" }, method = RequestMethod.GET)
    public String expediente(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Expediente/buscarExpediente";
    }
    @RequestMapping(value = { "/editarexpediente" }, method = RequestMethod.GET)
    public String editarexpediente(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Expediente/editarExpediente";
    }
    @RequestMapping(value = { "/resultadoExpediente" }, method = RequestMethod.GET)
    public String resultadoExpediente(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Expediente/resultadobusquedaExpediente";
    }
    @RequestMapping(value = { "/macursa" }, method = RequestMethod.GET)
    public String materiascursadas(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "MateriasCursadas";
    }
    
    @RequestMapping("/guardarExpediente")
	public ModelAndView guardarExpediente(@Valid @ModelAttribute Expediente expediente, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			try {
//				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//				LocalDate ahora = LocalDate.now();
//				
//				LocalDate fechaNac = LocalDate.parse(expediente.getD_fnacimiento(), fmt);
//				Period periodo = Period.between(fechaNac, ahora);
//				expediente.setS_edad(Integer.toString(periodo.getYears()));
				expedienteService.insert(expediente);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			expediente = new Expediente();
			mav.addObject("expediente", expediente); 
			mav.addObject("message", "Estudiante Guardado!");
		}

		mav.setViewName("/Expediente/AgregarExpediente");
		return mav;
	}

    @RequestMapping("/NuevoExpediente")
    public ModelAndView NuevoExpediente() {
    	ModelAndView mav = new ModelAndView();
    	Expediente expediente=new Expediente();
    	mav.addObject("expediente", expediente);
		mav.setViewName("/Expediente/AgregarExpediente");
        return mav;
    }
 
}
