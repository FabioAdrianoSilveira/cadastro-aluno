package aluno_api.aluno.dto;

import java.time.LocalDate;

import aluno_api.aluno.model.Aluno;

// Entidade utiizada para transportar dados no código sem expor diretamente a classe Aluno do model
public record AlunoDTO(Integer id, String nome, String email, String cpf, LocalDate dtNasc, Boolean ativo, float altura) {
    // Converte um objeto Aluno para um objeto AlunoDTO e retorna o objeto convertido
    public static AlunoDTO from(Aluno a) {
        AlunoDTO dto = new AlunoDTO(a.getId(), a.getNome(), a.getEmail(), a.getCpf(), a.getDtNasc(), a.getAtivo(), a.getAltura());
    
        return dto;
    }
}
