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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import devART.uca.capas.domain.AppUser;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.domain.UserRole;
import devART.uca.capas.service.AppRoleService;
import devART.uca.capas.service.AppUserService;
=======
import devART.uca.capas.domain.Expediente;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.service.ExpedienteServiceImpl;
>>>>>>> 92b32bd799edefbf3b90848892c606758ae40c56
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
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        //System.out.println("User Name: " + userName );
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        return "/Coordinador/coordinador";
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
    
    @RequestMapping("/registarUsuario")
   	public ModelAndView ingresarUsuario() {
   		ModelAndView mav = new ModelAndView();
   		System.out.println("aqui estoy registrando :v");
   		mav.addObject("userNew", new AppUser());
   		mav.setViewName("logupPage");
   		return mav;
   	}
    
    @RequestMapping("/validarRegistarUsuario")
   	public String ingresarUsuarioVerificar(@Valid @ModelAttribute AppUser usery,@RequestParam Long role, BindingResult result) {
    	//ModelAndView mav = new ModelAndView(); 
		if(!result.hasErrors()) {
			try {
				//System.out.println("role: "+role);
				//usery.setUserId((long) 5);
				//System.out.println("id: "+usery.getUserId() +" nombre: "+usery.getUserName()+" password:"+ usery.getEncrytedPassword());
				if(userServices.findOne(usery.getUserName())==null) {
					
				
				usery.setEnabled(false);
				//usery.setUserId((long) 5);
				usery.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(usery.getEncrytedPassword()));
				userServices.insert(usery);
				
				
				
				userRoleServices.insert(new UserRole(userServices.findOne(usery.getUserName()),roleServices.findOne(role)));
				System.out.println("se ingreso usuario: "+usery.toString());
				}
				
			}catch (Exception e) {
				System.out.println("megaF con usuario");
				e.printStackTrace();
			}
						
		}
		//mav.setViewName("ingresarMateria");
		return "redirect:/welcome";
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
		mav.setViewName("ingresarMateria");
		return mav;
	}

    //cordinador
    @RequestMapping(value = { "/coordi" }, method = RequestMethod.GET)
    public String coordinadorpage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Coordinador/coordinador";
    }
    @RequestMapping(value = { "/expediente" }, method = RequestMethod.GET)
    public String expediente(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Coordinador/buscarExpediente";
    }
    @RequestMapping(value = { "/editarexpediente" }, method = RequestMethod.GET)
    public String editarexpediente(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Coordinador/editarExpediente";
    }
    @RequestMapping(value = { "/resultadoExpediente" }, method = RequestMethod.GET)
    public String resultadoExpediente(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/Coordinador/resultadobusquedaExpediente";
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
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ahora = LocalDate.now();
				LocalDate fechaNac = LocalDate.parse(expediente.getD_fnacimiento(), fmt);
				System.out.println("Fecha NAch es  "+fechaNac);
				System.out.println("getD_fnacimiento() es  "+expediente.getD_fnacimiento());
				Period periodo = Period.between(fechaNac, ahora);
				expediente.setS_edad(Integer.toString(periodo.getYears()));
				expedienteService.insert(expediente);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			expediente = new Expediente();
			mav.addObject("expediente", expediente); 
			mav.addObject("message", "Estudiante Guardado!");
		}

		mav.setViewName("/Coordinador/AgregarExpediente");
		return mav;
	}

    @RequestMapping("/NuevoExpediente")
    public ModelAndView NuevoExpediente() {
    	ModelAndView mav = new ModelAndView();
    	Expediente expediente=new Expediente();
    	mav.addObject("expediente", expediente);
		mav.setViewName("/Coordinador/AgregarExpediente");
        return mav;
    }
 
}
