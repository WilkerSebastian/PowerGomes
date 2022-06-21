package tlp2av1.com.PowerGomes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tlp2av1.com.PowerGomes.model.Avaliacao;
import tlp2av1.com.PowerGomes.model.Jogo;
import tlp2av1.com.PowerGomes.repository.AvaliacaoRepository;
import tlp2av1.com.PowerGomes.repository.JogoRepository;

@Controller
public class HomeController {

    @Autowired
	JogoRepository jogoRepository;

    @Autowired
	AvaliacaoRepository avaliacaoRepository;

    @RequestMapping("/")
    public String  homeJogo(Model model){

        List<Jogo> jogos = jogoRepository.findAll();
		List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

		model.addAttribute("jogos", jogos);
		model.addAttribute("avaliacoes", avaliacoes);

        return "home";

    }
    
}
