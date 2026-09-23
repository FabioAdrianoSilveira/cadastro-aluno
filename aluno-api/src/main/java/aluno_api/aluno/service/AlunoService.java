package aluno_api.aluno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aluno_api.aluno.model.Aluno;
import aluno_api.aluno.model.AlunoRepository;

@Service // Indica ao Spring que essa será uma classe de serviço
public class AlunoService {
    // Injeta a classe Repository no código para execução das operações de banco de
    // dados
    @Autowired
    private AlunoRepository alunoRepository;

    // Recupera todos os registros da tabela aluno
    public Iterable<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    // Salva um aluno no banco de dados
    public Aluno save(Aluno a) {
        return alunoRepository.save(a);
    }

    // Recupera um registro específico da tabela aluno
    public Optional<Aluno> findById(Integer id) {
        return alunoRepository.findById(id);
    }

    // Deleta um registro da tabela aluno pelo id
    public void deleteById(Integer id) throws Exception {
        try {
            if (alunoRepository.findById(id).isEmpty()) {
                throw new Exception("Aluno inexistente.");
            }
            alunoRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
