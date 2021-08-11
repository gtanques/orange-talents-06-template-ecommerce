package orange.com.br.mercadolivre.produtos.opinioes;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OpinioesUtil {

    private Set<Opiniao> opinioes;

    public OpinioesUtil(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapearOpinioes) {
        return this.opinioes.stream()
                .map(funcaoMapearOpinioes)
                .collect(Collectors.toSet());
    }

    public double media() {
        Set<Integer> notas = mapeiaOpinioes(Opiniao::getNota);
        return notas.stream()
                .mapToInt(nota -> nota)
                .average()
                .orElse(0.0);
    }

    public int total() {
        return opinioes.size();
    }
}
