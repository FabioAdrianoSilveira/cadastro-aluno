package aluno_api.aluno.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aluno_api.aluno.dto.AlunoDTO;
import aluno_api.aluno.model.Aluno;
import aluno_api.aluno.model.AlunoRepository;
import aluno_api.aluno.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Operation(summary = "Criar um aluno com parâmetros", description = "Retorna uma mensagem.")
    @PostMapping(path = "/alunos/param")
    public ResponseEntity<String> createString(@RequestParam String nome, @RequestParam String email,
            @RequestParam String cpf, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtNasc,
            @RequestParam Boolean ativo, @RequestParam float altura) {
        log.info("createString( " + nome + ", " + email + "," + cpf + "," + dtNasc + "," + ativo + "," + altura + " )");

        try {
            Aluno a = new Aluno(nome, email, cpf, dtNasc, ativo, altura);

            alunoService.save(a);

            return new ResponseEntity<>("Aluno criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Criar aluno com objeto.", description = "Retorna o objeto criado.")
    @PostMapping(path = "/alunos")
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) {

        log.info("create( " + alunoDTO + " )");

        try {
            Aluno a = (Aluno) alunoService.save(new Aluno(alunoDTO.nome(), alunoDTO.email(), alunoDTO.cpf(),
                    alunoDTO.dtNasc(), alunoDTO.ativo(), alunoDTO.altura()));

            return new ResponseEntity<>(AlunoDTO.from(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Atualizar aluno com objeto.", description = "Retorna uma mensagem.")
    @PutMapping("/alunos/{id}")
    public ResponseEntity<String> update(@RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {

        log.info("update( " + alunoDTO + ", Id " + id + " )");

        Optional<Aluno> alunoData = alunoService.findById(id);

        if (alunoData.isPresent()) {
            Aluno a = alunoData.get();
            a.setNome(alunoDTO.nome());
            a.setEmail(alunoDTO.email());

            alunoService.save(a);

            return new ResponseEntity<>("Aluno alterado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Não foi possível encontrar o Aluno.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Exclui um aluno por Id.", description = "Retorna uma mensagem.")
	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {

		log.info("delete( Id " + id + " )");

		try {
			alunoService.deleteById(id);

			return new ResponseEntity<>("Aluno excluído com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível excluir o Aluno.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
