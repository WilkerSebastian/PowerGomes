package tlp2av1.com.PowerGomes.controller;

import java.util.Optional;

import javax.validation.Valid;

import tlp2av1.com.PowerGomes.orm.Jogo;
import tlp2av1.com.PowerGomes.repository.JogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/powergomes.com")
public class JogoController {
 
    @Autowired
	JogoRepository jogoRepository;

    @RequestMapping("/")
    public String  homeJogo(Model model){

        model.addAttribute("jogos" , jogoRepository.findAll());

        return "home";

    }

    @RequestMapping("/lista")
	public String listarJogo(Model model) {
		model.addAttribute("jogos", jogoRepository.findAll());
		
		return "lista";		
	}
	 
	@GetMapping("/adicionar")
	public String adicionarJogo(Model model) {
		model.addAttribute("jogo", new Jogo());
		return "formularioJogo";
	}
	
	@PostMapping("/salvar")
	public String salvarJogo(@Valid Jogo jogo, BindingResult result) {
		if (result.hasErrors()) {
			return "formularioJogo";
		}
		jogoRepository.save(jogo);
		return "redirect:/powergomes.com/lista";
	}

	@GetMapping("/deletar/jogo/{id}")
	public String deleteJogo(@PathVariable("id") long id, Model model) {
		Jogo jogo = jogoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		jogoRepository.delete(jogo);
	    return "redirect:/powergomes.com/lista";
	}

	@GetMapping("/editar/{id}")
	public String editarJogo(@PathVariable("id") long id, Model model) {
		Optional<Jogo> jogoAntigo = jogoRepository.findById(id);
		if (!jogoAntigo.isPresent()) {
            throw new IllegalArgumentException("Jogo inválido:" + id);
        } 
		Jogo jogo = jogoAntigo.get();
	    model.addAttribute("jogo", jogo);
	    return "alterar";
	}

	@PostMapping("/editar/{id}")
	public String edicaoJogo(@PathVariable("id") long id, @Valid Jogo jogo, BindingResult result){

		System.out.println("passou!");
		if (result.hasErrors()) {
	    	jogo.setId(id);
	        return "alterar";
	    }

		jogoRepository.save(jogo);

		return "redirect:/powergomes.com/lista";

	}

}
