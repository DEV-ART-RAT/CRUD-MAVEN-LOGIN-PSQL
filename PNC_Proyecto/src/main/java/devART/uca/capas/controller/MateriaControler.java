package devART.uca.capas.controller;

import devART.uca.capas.domain.Materia;
import devART.uca.capas.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MateriaControler {

    @Autowired
    MateriaService materiaService;

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
            mav.setViewName("/Administrador/ingresarMateriafake");
        }else{
            mav.setViewName("/Administrador/ingresarMateria");

        }
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

    @RequestMapping(value="/editarMateria")
    public ModelAndView editar(@RequestParam(value="id") String codigo)
    {
        ModelAndView mav = new ModelAndView();
        Materia materia=null;
        try {
            materia = materiaService.findOne(codigo);
            mav.addObject("materia", materia);
            mav.setViewName("/Administrador/modificarMateria");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping("/updateMateria")
    public ModelAndView guardarExpedientemodificado(@Valid @ModelAttribute Materia materia, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;
        if(!result.hasErrors()) {
            try {
                materiaService.insert(materia);
                materias = materiaService.findAll();

            }catch (Exception e) {
                e.printStackTrace();
            }
            mav.addObject("materias", materia);
            mav.addObject("message", "Materia Modificada!");
            mav.setViewName("/Administrador/modificarMateriafake");
        }else{
            mav.addObject("materias", materia);
            mav.setViewName("/Administrador/modificarMateria");

        }
        return mav;
    }

}

