package devART.uca.capas.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import devART.uca.capas.domain.*;
import devART.uca.capas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	DptoService dptoService;

	@Autowired
	MunicipioService municipioService;

	@Autowired
	UserExpedienteService userExpedienteService;

	/*
    @RequestMapping(value = {  "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
	 */

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "/Administrador/administrador";
    }


    @RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Login");
        return "welcomePage";
    }

    @RequestMapping(value = "/userCoordinador", method = RequestMethod.GET)
    public ModelAndView listadoCoordinador(Principal principal) {
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

    @RequestMapping(value = "/userAdministardor", method = RequestMethod.GET)
    public ModelAndView listadoAdministrador(Principal principal) {
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


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String listado(Principal principal) {

        User auth = (User) ((Authentication) principal).getPrincipal();
        String rol = WebUtils.getRole(auth);
        System.out.println(rol);

        if(rol.equals("ROLE_USER")){
            return "redirect:/userCoordinador";
        }

        if(rol.equals("ROLE_ADMIN")){
            return "redirect:/403";
        }
		return "redirect:/";
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
   		List<Dpto> dptos = null;
   		List<Municipio> municipios=null;
   		try {
			dptos = dptoService.findAll();
			municipios = municipioService.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(dptos);
		System.out.println(municipios);
   		mav.addObject("userNew", new AppUser());
		mav.addObject("userNewExp", new UserExpediente());
		mav.addObject("dptos", dptos);
		mav.addObject("municipios",municipios);
   		mav.setViewName("registerPage");
   		mav.addObject("userInfo","registro");
   		return mav;
   	}

    @RequestMapping("/validarRegistrarUsuario")
	public ModelAndView ingresarUsuarioVerificar(@RequestParam("role") Long role,
												 @ModelAttribute("userNew") @Valid AppUser usery,BindingResult result1, @ModelAttribute("userNewExp") @Valid UserExpediente userExp ,BindingResult result ) {    	ModelAndView mav = new ModelAndView();
		if(result.hasErrors() || result1.hasErrors()) {
			//AppUser appuser = new AppUser();
//	   		mav.addObject("userNew", usery);
//			mav.addObject("message", "No se pudo ingresar");

			List<Dpto> dptos = null;
			List<Municipio> municipios=null;
			try {
				dptos = dptoService.findAll();
				municipios = municipioService.findAll();
			}catch (Exception e){
				e.printStackTrace();
			}
			mav.addObject("dptos",dptos);
			mav.addObject("municipios",municipios);
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
						userExp.setCodigo(usery.getUserId());

						DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate ahora = LocalDate.now();
						LocalDate fechaNac = LocalDate.parse(userExp.getFnacimiento(), fmt);
						//System.out.println("Fecha Nacimiento es  "+fechaNac);
						//System.out.println("getD_fnacimiento() es  "+expediente.getD_fnacimiento());
						Period periodo = Period.between(fechaNac, ahora);
						if(periodo.getYears()>999){
							userExp.setEdad(Integer.toString(999));
							userExpedienteService.insert(userExp);
						}else {
							userExp.setEdad(Integer.toString(periodo.getYears()));
							userExpedienteService.insert(userExp);
						}


						//userExpedienteService.insert(userExp);
						//System.out.println("se ingreso usuario: "+usery.toString());
					}else {
						mav.addObject("userNew", new AppUser());
						mav.addObject("message", "Nombre de Usuario ya existe");
						//System.out.println("Nombre de usuario ya existe");
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

    @RequestMapping("/administarUsuario")
    public ModelAndView administarUsuario(Principal principal) {
        ModelAndView mav = new ModelAndView();
        //System.out.println("aqui estoy registrando :v");
        //User loginedUser = (User) ((Authentication) principal).getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//auth.getAuthorities().;
        List<Dpto> dptos = null;
        List<Municipio> municipios=null;
        List<AppUser> users = null;
        List<UserExpediente> expes=null;
        try {
            dptos = dptoService.findAll();
            municipios = municipioService.findAll();
            users = userServices.findAll();
            expes= userExpedienteService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        List<UsuarioManager> usuarioManagers = WebUtils.getListUsers(users,expes,dptos,municipios,auth.getName());
        //System.out.println(dptos);
        //System.out.println(municipios);
        mav.addObject("userList", usuarioManagers);
        //mav.addObject("userNewExp", new UserExpediente());
        //mav.addObject("dptos", dptos);
        //mav.addObject("municipios",municipios);
        mav.setViewName("/administrador/userManager");
        return mav;
    }


	@RequestMapping("/usuarioDesactive")
	public String disableUser(@RequestParam("disable") String name) {
    	AppUser userUpdate= userServices.findOne(name);
		userUpdate.setEnabled(false);
    	userServices.insert(userUpdate);
		return "redirect:/administarUsuario";
	}
	@RequestMapping("/usuarioActive")
	public String enableUser(@RequestParam("enabled") String name) {
		AppUser userUpdate= userServices.findOne(name);
		userUpdate.setEnabled(true);
		userServices.insert(userUpdate);
		return "redirect:/administarUsuario";
	}
}


//https://stackoverflow.com/questions/24802681/org-springframework-validation-beanpropertybindingresult-exception