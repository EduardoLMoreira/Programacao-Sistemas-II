package ps2.restapidb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(EmpresaRepo empresaRepo, EstudanteRepo estudanteRepo, VagaRepo vagaRepo) {
        return args -> {
            // Empresas
            Empresa e1 = empresaRepo.save(new Empresa(null, "Empresa Alfa LTDA", "12.345.678/0001-90", "contato@empresa-alfa.com"));
            Empresa e2 = empresaRepo.save(new Empresa(null, "Beta Comércio ME", "98.765.432/0001-10", "beta@comercio.com"));
            Empresa e3 = empresaRepo.save(new Empresa(null, "Gamma Serviços S.A.", "11.222.333/0001-44", "servicos@gamma.com"));
            Empresa e4 = empresaRepo.save(new Empresa(null, "Delta Engenharia", "22.333.444/0001-55", "contato@deltaeng.com"));
            Empresa e5 = empresaRepo.save(new Empresa(null, "Epsilon Digital", "33.444.555/0001-66", "email@epsilondigital.com"));

            // Estudantes (data no formato YYYY-MM-DD)
            estudanteRepo.save(new Estudante(null,"Ana Paula Souza","ana.souza@email.com",
                    LocalDate.parse("2002-03-15"), 2020));
            estudanteRepo.save(new Estudante(null,"Carlos Henrique Lima","carlos.lima@email.com",
                    LocalDate.parse("2001-10-22"), 2019));
            estudanteRepo.save(new Estudante(null,"Fernanda Oliveira","fernanda.oliveira@email.com",
                    LocalDate.parse("2003-07-05"), 2021));
            estudanteRepo.save(new Estudante(null,"Lucas Pereira","lucas.pereira@email.com",
                    LocalDate.parse("2002-04-11"), 2020));
            estudanteRepo.save(new Estudante(null,"Gabriela Martins","gabriela.martins@email.com",
                    LocalDate.parse("2001-12-25"), 2019));
            estudanteRepo.save(new Estudante(null,"Rafael Costa","rafael.costa@email.com",
                    LocalDate.parse("2000-09-13"), 2018));
            estudanteRepo.save(new Estudante(null,"Juliana Silva","juliana.silva@email.com",
                    LocalDate.parse("2002-06-18"), 2020));
            estudanteRepo.save(new Estudante(null,"Marcos Vinícius","marcos.vinicius@email.com",
                    LocalDate.parse("2003-01-30"), 2021));
            estudanteRepo.save(new Estudante(null,"Camila Azevedo","camila.azevedo@email.com",
                    LocalDate.parse("2001-11-08"), 2019));
            estudanteRepo.save(new Estudante(null,"Felipe Cardoso","felipe.cardoso@email.com",
                    LocalDate.parse("2000-08-27"), 2018));

            // Vagas
            vagaRepo.save(new Vaga(null,"Desenvolvedor Java","Atuação em projetos backend com Java e Spring. Experiência desejada em APIs REST.",
                    LocalDate.parse("2025-10-01"), true, e1));
            vagaRepo.save(new Vaga(null,"Analista de Suporte Técnico","Suporte a clientes, resolução de chamados e participação em treinamentos internos.",
                    LocalDate.parse("2025-09-27"), true, e2));
            vagaRepo.save(new Vaga(null,"Engenheiro de Software","Desenvolvimento de soluções para sistemas corporativos, integração e automação.",
                    LocalDate.parse("2025-10-03"), false, e3));
            vagaRepo.save(new Vaga(null,"Analista de Dados","Manipulação e análise de grandes volumes de dados. Conhecimentos de SQL e Python.",
                    LocalDate.parse("2025-09-18"), true, e4));
            vagaRepo.save(new Vaga(null,"Designer Digital","Criação de materiais gráficos, UX/UI e participação em campanhas de marketing.",
                    LocalDate.parse("2025-09-30"), false, e5));
            vagaRepo.save(new Vaga(null,"Consultor de Projetos","Elaboração e acompanhamento de projetos empresariais e treinamentos.",
                    LocalDate.parse("2025-10-06"), true, e1));
            vagaRepo.save(new Vaga(null,"Programador Full Stack","Desenvolvimento de aplicações web frontend e backend com foco em automação.",
                    LocalDate.parse("2025-10-04"), true, e2));
        };
    }
}