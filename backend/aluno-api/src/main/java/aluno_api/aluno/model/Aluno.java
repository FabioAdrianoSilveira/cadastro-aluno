package aluno_api.aluno.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Uso da biblioteca Lombok para criar um construtor vazio "por baixo dos panos"
@NoArgsConstructor

// Uso da biblioteca Lombok para criar Getters e Setters "por baixo dos panos"
@Getter
@Setter

@Entity // Indica ao hibernate que essa classe corresponderá a uma tabela do banco
public class Aluno {
    @Id // Marca o atributo abaixo da notação como a PRIMARY KEY da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) // Define que o atributo abaixo da notação deve ser gerado automaticamente
    private @Nullable Integer id; // @Nullable indica que o valor do atributo pode ser nulo na hora de fazer um POST

    @NotBlank(message = "O nome do aluno é obrigatório")
    private String nome;
    @NotBlank(message = "O uso de um e-mail válido é obrigatório")
    @Email
    private String email;
    @NotBlank(message = "O uso de um CPF válido é obrigatório")
    @CPF
    private String cpf;
    @NotNull(message = "Passagem de uma data passada é obrigatório")
    @Past
    private LocalDate dtNasc;
    @NotNull
    private Boolean ativo;
    @NotNull(message = "Passagem de um valor positivo é obrigatório")
    @Positive
    private float altura;

    public Aluno(String nome, String email, String cpf, LocalDate dtNasc, Boolean ativo, float altura) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.ativo = ativo;
        this.altura = altura;
    }
}
