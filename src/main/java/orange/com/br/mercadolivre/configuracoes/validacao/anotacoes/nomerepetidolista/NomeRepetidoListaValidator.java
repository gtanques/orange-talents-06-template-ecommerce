package orange.com.br.mercadolivre.configuracoes.validacao.anotacoes.nomerepetidolista;

import orange.com.br.mercadolivre.produtos.caracteristicas.CaracteristicaProdutoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NomeRepetidoListaValidator implements ConstraintValidator<NomeRepetidoListaValid, List<CaracteristicaProdutoRequest>> {

    @Override
    public boolean isValid(List<CaracteristicaProdutoRequest> listaCaracteristicas, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> nomesRepetidos = new HashSet<>();
        for (CaracteristicaProdutoRequest c : listaCaracteristicas) {
            if (!nomesRepetidos.add(c.getNome())) {
                return false;
            }
        }

        return true;
    }

}
