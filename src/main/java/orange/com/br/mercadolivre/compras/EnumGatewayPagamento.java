package orange.com.br.mercadolivre.compras;

import orange.com.br.mercadolivre.pagamentos.formaspagamento.Financeiro;
import orange.com.br.mercadolivre.pagamentos.formaspagamento.PagSeguro;
import orange.com.br.mercadolivre.pagamentos.formaspagamento.Paypal;

public enum EnumGatewayPagamento {


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

