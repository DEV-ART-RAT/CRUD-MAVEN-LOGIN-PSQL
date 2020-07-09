package devART.uca.capas.controller;

import devART.uca.capas.domain.Dpto;
import devART.uca.capas.domain.Institucion;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.domain.Municipio;
import devART.uca.capas.repositories.InstitucionRepository;
import devART.uca.capas.service.DptoService;
import devART.uca.capas.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InstitucionController {

    @Autowired
    InstitucionRepository institucionRepository;

    @Autowired
    DptoService dptoService;

    @Autowired
    MunicipioService municipioService;


    @RequestMapping(value="/editarInstitucion")
    public ModelAndView editar(@RequestParam(value="id") Integer codigo)
    {
        ModelAndView mav = new ModelAndView();
        Institucion institucion=null;
        List<Dpto> dptos = null;
        List<Municipio> municipios=null;
        try {
            institucion = institucionRepository.findById(codigo).get();
            mav.addObject("institucion", institucion);
            mav.setViewName("/Administrador/modificarInstitucion");
            dptos = dptoService.findAll();
            municipios = municipioService.findAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("dptos", dptos);
        mav.addObject("municipios",municipios);
        return mav;
    }

    @RequestMapping(value = "/institucionLista", method = RequestMethod.GET)
    public ModelAndView listadoInstitucion() {
        ModelAndView mav = new ModelAndView();
        List<Institucion> instituciones = null;
        try {
            instituciones = institucionRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("instituciones", instituciones);
        mav.setViewName("/Administrador/editorInstituciones");
        return mav;
    }

    @RequestMapping("/ingresarInstitucion")
    public ModelAndView ingresarMateria() {
        ModelAndView mav = new ModelAndView();
        List<Dpto> dptos = null;
        List<Municipio> municipios=null;
        try {
            dptos = dptoService.findAll();
            municipios = municipioService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("dptos", dptos);
        mav.addObject("municipios",municipios);
        mav.addObject("institucion", new Institucion());
        mav.setViewName("/Administrador/ingresarInstitucion");
        return mav;
    }

    @RequestMapping("/validarIngresarInstitucion")
    public ModelAndView ingresarUsuarioVerificar(@RequestParam(value="codigo") Integer codigo,@ModelAttribute("institucion") @Valid Institucion institucion, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        //System.out.println(usery.getUserName() + userExp.getDpto().getNombre());
        List<Dpto> dptos = null;
        List<Municipio> municipios = null;
        try {
            dptos = dptoService.findAll();
            municipios = municipioService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("dptos", dptos);
        mav.addObject("municipios", municipios);
        if (result.hasErrors()) {

        } else {
            try {
                institucionRepository.save(institucion);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.addObject("institucion", new Institucion());
            mav.addObject("message", "Instituion Guardada!");
        }
        if(codigo.equals("")||codigo.equals(0)){
            mav.setViewName("/Administrador/ingresarInstitucion");
        }else {

            mav.setViewName("/Administrador/modificarInstitucion");
            mav.addObject("id",codigo);
            mav.addObject("institucion", institucion);
        }


        return mav;
    }


}
