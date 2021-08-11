package orange.com.br.mercadolivre.pagamentos.transacoes;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.compras.EnumGatewayPagamento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    private  String id = UUID.randomUUID().toString().replace("-","");

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final EnumStatusTransacao statusCompra;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final EnumGatewayPagamento gateway;

    @Column(nullable = false, updatable = false)
    private final Instant momentoTransacao = Instant.now();

    @ManyToOne
    private Compra compra;

    public Transacao(@NotNull Compra compra, @NotNull String statusCompra, @NotNull EnumGatewayPagamento gateway) {
        this.compra = compra;
        this.statusCompra = tratarRetornoGateway(statusCompra);
        this.gateway = gateway;
    }

    public EnumStatusTransacao getStatusCompra() { return statusCompra; }

    public String getId() { return id; }

    public EnumGatewayPagamento getGateway() { return gateway; }

    public Instant getMomentoTransacao() { return momentoTransacao; }

    public Compra getCompra() { return compra; }

    private EnumStatusTransacao tratarRetornoGateway(String retorno){
        if (retorno.equals("1") || retorno.equals("Sucesso")){
            return EnumStatusTransacao.SUCESSO;
        }
        return EnumStatusTransacao.ERRO;
    }

}
