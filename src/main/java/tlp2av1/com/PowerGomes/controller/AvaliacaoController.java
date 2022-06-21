package tlp2av1.com.PowerGomes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tlp2av1.com.PowerGomes.model.Avaliacao;
import tlp2av1.com.PowerGomes.model.Jogo;
import tlp2av1.com.PowerGomes.repository.AvaliacaoRepository;
import tlp2av1.com.PowerGomes.repository.JogoRepository;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping("/adicionar/{id}")
    public String adicionarAvaliacao(@PathVariable("id") long id, Model model){

        Optional<Jogo> jogoBanco = jogoRepository.findById(id);
		Jogo jogo = jogoBanco.get();


	    model.addAttribute("jogo", jogo);
        model.addAttribute("avaliacao", new Avaliacao());

        return "avaliacao";

    }

    @PostMapping("/salvar/{id}")
	public String salvarAvaliacao(@Valid Avaliacao avaliacao,BindingResult result,Model model,@PathVariable("id") long idJogo) {

		Optional<Jogo> jogoBanco = jogoRepository.findById(idJogo);
		Jogo jogo = jogoBanco.get();
		if (result.hasErrors()) {

            System.out.println("\n\n\n\n\n\n\n" + result.getAllErrors() + "\n\n\n\n\n\n\n");
            model.addAttribute("jogo", jogo);
            model.addAttribute("avaliacao", new Avaliacao());
			return "avaliacao";

		}


        Date data = new Date();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
        avaliacao.setDatareview(data);
		avaliacao.setJogo(jogo);
		avaliacaoRepository.save(avaliacao);

		return "redirect:/jogo/lista";
	}
}
