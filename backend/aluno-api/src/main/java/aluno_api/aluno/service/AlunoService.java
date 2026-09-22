package aluno_api.aluno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aluno_api.aluno.model.Aluno;
import aluno_api.aluno.model.AlunoRepository;

@Service // Indica ao Spring que essa será uma classe de serviço
public class AlunoService {
    // Injeta a classe Repository no código para execução das operações de banco de dados
    @Autowired
    private AlunoRepository alunoRepository;

    // Recupera todos os registros da tabela aluno
    public Iterable<Aluno> getAll() {
        return alunoRepository.findAll();
    }

}
