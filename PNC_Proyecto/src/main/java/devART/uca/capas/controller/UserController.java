package devART.uca.capas.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import devART.uca.capas.domain.AppUser;
import devART.uca.capas.domain.Expediente;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.domain.UserRole;
import devART.uca.capas.service.AppRoleService;
import devART.uca.capas.service.AppUserService;
import devART.uca.capas.service.ExpedienteServiceImpl;
import devART.uca.capas.service.MateriaService;
import devART.uca.capas.service.UserDetailsServiceImpl;
import devART.uca.capas.service.UserRoleService;
import devART.uca.capas.utils.EncrytedPasswordUtils;
import devART.uca.capas.utils.WebUtils;
 
@Controller
public class UserController {
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	ExpedienteServiceImpl expedienteService;
	
	@Autowired
	MateriaService materiaService;
	
	@Autowired
	AppUserService userServices;
	
	@Autowired
	AppRoleService roleServices;
	
	@Autowired
	UserRoleService userRoleServices;
 
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
         
        return "/Administrador/administrador";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
    
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Login");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		
		List<Expediente> expedientes = null;
		try {
			
			expedientes = expedienteService.findAllExpe();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("expedientes", expedientes);
		mav.setViewName("/Coordinador/coordinador");
		
		return mav;
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
    
    @RequestMapping("/registrarUsuario")
   	public ModelAndView ingresarUsuario() {
   		ModelAndView mav = new ModelAndView();
   		System.out.println("aqui estoy registrando :v");
   		AppUser appuser = new AppUser();
   		mav.addObject("userNew", appuser);
   		mav.setViewName("registerPage");
   		return mav;
   	}
    
    @RequestMapping("/validarRegistrarUsuario")
   	public ModelAndView ingresarUsuarioVerificar(@Valid @ModelAttribute AppUser usery,BindingResult result,@RequestParam Long role ) {
    	ModelAndView mav = new ModelAndView(); 
		if(result.hasErrors()) {
			//AppUser appuser = new AppUser();
//	   		mav.addObject("userNew", usery);
//			mav.addObject("message", "No se pudo ingresar");
			mav.setViewName("registerPage");
		}
		else {
			try {
				//System.out.println("role: "+role);
				//usery.setUserId((long) 5);
				//System.out.println("id: "+usery.getUserId() +" nombre: "+usery.getUserName()+" password:"+ usery.getEncrytedPassword());
					if(userServices.findOne(usery.getUserName())==null) 
					{
						usery.setEnabled(false);
						//usery.setUserId((long) 5);
						usery.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(usery.getEncrytedPassword()));
						userServices.insert(usery);
						userRoleServices.insert(new UserRole(userServices.findOne(usery.getUserName()),roleServices.findOne(role)));
						System.out.println("se ingreso usuario: "+usery.toString());
					}else {
						mav.addObject("userNew", new AppUser());
						mav.addObject("message", "Error Usuario ya existe");
						System.out.println("usuario ya existe");
						mav.setViewName("registerPage");
						return mav;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
	   		AppUser appuser = new AppUser();
	   		mav.addObject("userNew", appuser);
	   		mav.addObject("message", "Usuario ingresado!");
			mav.setViewName("loginPage");
		}
		return mav;

   	}
    
    @RequestMapping("/ingresarMateria")
	public ModelAndView ingresarMateria() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("materia", new Materia());
		mav.setViewName("/Administrador/ingresarMateria");
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
		mav.setViewName("/Administrador/ingresarMateria");
		return mav;
	}

	@RequestMapping(value = "/materiasLista", method = RequestMethod.GET)
	public ModelAndView listadoMateria() {
		ModelAndView mav = new ModelAndView();
		List<Materia> materias = null;
		try {
			materias = materiaService.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("materias", materias);
		mav.setViewName("/Administrador/editorMaterias");

		return mav;
	}

}


//https://stackoverflow.com/questions/24802681/org-springframework-validation-beanpropertybindingresult-exception