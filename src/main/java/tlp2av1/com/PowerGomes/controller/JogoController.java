package tlp2av1.com.PowerGomes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import tlp2av1.com.PowerGomes.model.Avaliacao;
import tlp2av1.com.PowerGomes.model.Jogo;
import tlp2av1.com.PowerGomes.repository.AvaliacaoRepository;
import tlp2av1.com.PowerGomes.repository.JogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jogo")
public class JogoController {
 
    @Autowired
	JogoRepository jogoRepository;

	@Autowired
	AvaliacaoRepository avaliacaoRepository;

    @RequestMapping("/lista")
	public String listarJogo(Model model) {

		List<Jogo> jogos = jogoRepository.findAll();
		List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

		model.addAttribute("jogos", jogos);
		model.addAttribute("avaliacoes", avaliacoes);
		
		return "lista";		
	}
	 
	@GetMapping("/adicionar")
	public String adicionarJogo(Model model) {
		model.addAttribute("jogo", new Jogo());
		return "formularioJogo";
	}
	
	@PostMapping("/salvar")
	public String salvarJogo(@Valid Jogo jogo, BindingResult result , RedirectAttributes attributes) {
		if (result.hasErrors()) {
			System.out.println("\n\n\n\n\nerros: " + result.getFieldErrors() + "\n\n\n\n\n");
			return "formularioJogo";
		}
		jogoRepository.save(jogo);
		return "redirect:/jogo/lista";
	}

	@GetMapping("/deletar/{id}")
	public String deleteJogo(@PathVariable("id") long id, Model model) {
		Jogo jogo = jogoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		jogoRepository.delete(jogo);
	    return "redirect:/jogo/lista";
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
