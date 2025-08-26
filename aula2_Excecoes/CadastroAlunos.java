import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroAlunos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> alunos = new ArrayList<>();
        
        System.out.print("Quantos alunos deseja cadastrar? ");
        int N = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer

        for (int i = 0; i < N; i++) {
            try {
                System.out.println("\nCadastro do " + (i + 1) + "º aluno:");

                // Nome do aluno
                System.out.print("Digite o nome do aluno: ");
                String nome = scanner.nextLine();
                if (nome.trim().isEmpty()) {
                    throw new NomeInvalidoException("Nome não pode ser vazio.");
                }

                // Nota do aluno
                double nota = 0;
                while (true) {
                    try {
                        System.out.print("Digite a nota do aluno (0 a 10): ");
                        String notaStr = scanner.nextLine();
                        nota = Double.parseDouble(notaStr);
                        if (nota < 0 || nota > 10) {
                            throw new NotaInvalidaException("Nota deve ser entre 0 e 10.");
                        }
                        break;  // Sair do loop se a nota for válida
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: A nota deve ser um número válido.");
                    } catch (NotaInvalidaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                // Cria e adiciona o aluno na lista
                alunos.add(new Aluno(nome, nota));

            } catch (NomeInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        // Exibe os alunos cadastrados
        System.out.println("\nAlunos cadastrados:");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        // Calcula a média geral da turma
        if (!alunos.isEmpty()) {
            double somaNotas = 0;
            Aluno maiorNota = alunos.get(0);
            Aluno menorNota = alunos.get(0);

            for (Aluno aluno : alunos) {
                somaNotas += aluno.getNota();
                if (aluno.getNota() > maiorNota.getNota()) {
                    maiorNota = aluno;
                }
                if (aluno.getNota() < menorNota.getNota()) {
                    menorNota = aluno;
                }
            }

            double mediaGeral = somaNotas / alunos.size();
            System.out.println("\nMédia geral da turma: " + mediaGeral);
            System.out.println("Aluno com maior nota: " + maiorNota);
            System.out.println("Aluno com menor nota: " + menorNota);
        } else {
            System.out.println("Nenhum aluno foi cadastrado.");
        }

        scanner.close();
    }
}