package school.sptech;
import school.sptech.Vacina;
import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.VacinaInvalidaException;
import school.sptech.exception.VacinaNaoEncontradaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio {
    private String nome;
    private List<Vacina> vacinas = new ArrayList<>();

    public Laboratorio() {
        this.vacinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<school.sptech.Vacina> getVacinas() {
        return vacinas;
    }

    public void adicionarVacinas(Vacina vacina) throws VacinaInvalidaException {
        try {
            if(vacina == null ||
                vacina.getCodigo() == null || vacina.getCodigo().isEmpty() ||
                vacina.getNome() == null || vacina.getNome().isEmpty() ||
                vacina.getTipo() == null || vacina.getTipo().isEmpty() ||
                vacina.getPreco() == null || vacina.getPreco() <= 0 ||
                vacina.getEficacia() == null || vacina.getEficacia() < 0 || vacina.getEficacia() > 5 ||
                vacina.getDataLancamento() == null || vacina.getDataLancamento().isAfter(LocalDate.now()))
            {
                throw new VacinaInvalidaException();
            }

            this.vacinas.add(vacina);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Vacina buscarVacinaPorCodigo(String codigo) throws ArgumentoInvalidoException, VacinaNaoEncontradaException {
        try {
            if(codigo == null || codigo.isEmpty() || codigo.isBlank()) {
                throw new ArgumentoInvalidoException();
            }

            for(Vacina vacina : vacinas) {
                if(vacina.getCodigo().equals(codigo)) {
                    return vacina;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        throw new VacinaNaoEncontradaException();
    }

    public void removerVacinaPorCodigo(String codigo) throws  ArgumentoInvalidoException, VacinaNaoEncontradaException{
        try {
            if(codigo == null || codigo.isEmpty() || codigo.isBlank()) {
                throw new ArgumentoInvalidoException();
            }

            for(Vacina vacina : vacinas) {
                if(vacina.getCodigo().equals(codigo)) {
                    vacinas.remove(vacina);
                    return;
                }
            }

            throw new VacinaNaoEncontradaException();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void buscarVacinaComMelhorEficacia() throws VacinaNaoEncontradaException {
        try {
            List<Vacina> vacinasEncontradas = new ArrayList<>();

            for(Vacina vacina : vacinas) {
                vacinasEncontradas.add(vacina);
            }

            if(vacinasEncontradas.isEmpty()) {
                throw new VacinaNaoEncontradaException();
            }

            int n = vacinasEncontradas.size();
            boolean trocou;

            for(int i = 0; i < n - 1; i++) {
                trocou = false;

                for(int j = 0; j < n - i - 1; j ++) {
                    if(vacinasEncontradas.get(j).getEficacia() > vacinasEncontradas.get(j + 1).getEficacia()) {

                        Vacina temp = vacinasEncontradas.get(j);
                        vacinasEncontradas.set(j, vacinasEncontradas.get(j+ 1));
                        vacinasEncontradas.set(j + 1, temp);
                        trocou = true;
                    }
                }

                if(!trocou) {
                    break;
                }
            }

            String mensagem = "";
            for(Vacina vacina : vacinasEncontradas) {
                mensagem += vacina.getEficacia() + " ";
            }
            System.out.println(mensagem);

            //deixando apenas os com maior eficácia
            for(int i = 0; i <= vacinasEncontradas.size(); i++) {
                if(!vacinasEncontradas.get(i).getEficacia().equals(vacinasEncontradas.getLast().getEficacia())) {
                    vacinasEncontradas.remove(vacinasEncontradas.get(i));
                    i--;
                }
            }

            //deixando no array apenas o com data mais recente
            while(vacinasEncontradas.size() > 1) {
                if(vacinasEncontradas.getFirst().getDataLancamento().isBefore(vacinasEncontradas.getLast().getDataLancamento())) {
                    vacinasEncontradas.remove(vacinasEncontradas.getFirst());
                } else {
                    vacinasEncontradas.remove(vacinasEncontradas.getLast());
                }
            }

            mensagem = "";
            for(Vacina vacina : vacinasEncontradas) {
                mensagem += vacina.getEficacia() + " ";
            }
            System.out.println(mensagem);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Vacina> buscarVacinaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        ArrayList<Vacina> vacinas = new ArrayList<>();

        try {
            if (dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)) {
                throw new ArgumentoInvalidoException();
            }

            for(Vacina vacina : vacinas) {
                LocalDate dataVacina = vacina.getDataLancamento();

                if(dataVacina.isAfter(dataInicio) && dataVacina.isBefore(dataFim)) {
                    vacinas.add(vacina);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return vacinas;
    }
}
