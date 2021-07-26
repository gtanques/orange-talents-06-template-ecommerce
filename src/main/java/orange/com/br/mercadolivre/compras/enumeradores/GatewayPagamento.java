package orange.com.br.mercadolivre.compras.enumeradores;

import orange.com.br.mercadolivre.pagamento.Financeiro;
import orange.com.br.mercadolivre.pagamento.formaspagamento.PagSeguro;
import orange.com.br.mercadolivre.pagamento.formaspagamento.Paypal;

public enum GatewayPagamento {


    PAGSEGURO {
        @Override
        public Financeiro financeiro() {
            return new PagSeguro();
        }
    },

    PAYPAL {
        @Override
        public Financeiro financeiro() {
            return new Paypal();
        }
    };

    abstract public Financeiro financeiro();
    }

