package school.sptech;
import school.sptech.Vacina;
import school.sptech.Laboratorio;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vacina vacina1 = new Vacina("01", "a", "a", 20.0, 1.0, LocalDate.of(2025, 6, 20));
        Vacina vacina2 = new Vacina("02", "b", "b", 50.0, 3.0, LocalDate.of(2025, 7, 20));
        Vacina vacina3 = new Vacina("03", "c", "c", 40.0, 3.0, LocalDate.of(2025, 8, 20));
        Vacina vacina4 = new Vacina("04", "d", "d", 90.0, 2.0, LocalDate.of(2025, 9, 20));

        Laboratorio laboratorio = new Laboratorio();
        //System.out.println(laboratorio.getVacinas());

        laboratorio.adicionarVacinas(vacina1);
        laboratorio.adicionarVacinas(vacina2);
        laboratorio.adicionarVacinas(vacina3);
        laboratorio.adicionarVacinas(vacina4);
        //System.out.println(laboratorio.getVacinas());
        // metodo adicionar vacina funcionando, dando erro ao não bater com as métricas

        laboratorio.buscarVacinaPorCodigo("01");
        //retornando vacina quando encontrada funcionando
        //também dando erro nas duas exceções

        //System.out.println(laboratorio.getVacinas());
        laboratorio.removerVacinaPorCodigo("01");
        //System.out.println(laboratorio.getVacinas());
        //metodo funcionando, removendo vacina por codigo e caindo nas exeções

        /*List<Vacina> vacinas = laboratorio.buscarVacinaPorPeriodo(LocalDate.of(2025, 8, 20), LocalDate.of(2025, 10, 20));
        String mensagem = "";
        for(Vacina vacina : vacinas) {
            mensagem += vacina.getDataLancamento() + " ";
        }
        System.out.println(mensagem);*/

        //laboratorio.buscarVacinaComMelhorEficacia();
    }
}