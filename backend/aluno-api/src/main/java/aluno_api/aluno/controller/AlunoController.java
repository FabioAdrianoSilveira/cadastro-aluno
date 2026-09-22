package aluno_api.aluno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aluno_api.aluno.model.Aluno;
import aluno_api.aluno.model.AlunoRepository;
import aluno_api.aluno.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Tag(name = "aluno-api", description = "API para manter alunos.")
@Log4j2 
@RequestMapping(path = "/aluno-api") // Define o endpoint base para todas as rotas da API

@RestController // Indica ao Spring que essa será uma classe controller REST
public class AlunoController {
    // Injeta a camada de serviço no código para execução das regras de negócio
    @Autowired
    private AlunoService alunoService;

    // Injeta o Repository no código para executar regras de negócio
    @Autowired
    private AlunoRepository alunoRepository;

    @Operation(summary = "Recuperar alunos", description = "Retornar uma coleção de alunos.")
    @GetMapping("/alunos")
    public @ResponseBody Iterable<Aluno> getAll() {
        log.info("getAll()");

        return alunoRepository.findAll();
    }
    
}
