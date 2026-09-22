package aluno_api.aluno.model;

import org.springframework.data.repository.CrudRepository;

/* 
    Interface responsável por gerar automaticamente operações SQL

    EXPLICAÇÃO
    Crud repository recebe 2 atributos, a entidade que ele vai gerenciar e o tipo da PRIMARY KEY dessa entidade
    CrudRepository<Entidade, KeyType>

    Esse tipo de interface roda operações SQL automaticamente através de chamads de métodos padrão, por exemplo:
    save(class) -> INSERT / UPDATE
    findById(id) -> SELECT ... WHERE id = ?
    findAll() -> SELECT * FROM class
    deleteById(id) -> DELETE FROM class WHERE id = ?
*/
public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

}
