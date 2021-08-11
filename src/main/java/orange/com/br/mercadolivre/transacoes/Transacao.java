package orange.com.br.mercadolivre.transacoes;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.compras.enumeradores.GatewayPagamento;
import orange.com.br.mercadolivre.transacoes.enumeradores.StatusTransacao;

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
    private final StatusTransacao statusCompra;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final GatewayPagamento gateway;

    @Column(nullable = false, updatable = false)
    private final Instant momentoTransacao = Instant.now();

    @ManyToOne
    private Compra compra;

    public Transacao(@NotNull Compra compra, @NotNull String statusCompra, @NotNull GatewayPagamento gateway) {
        this.compra = compra;
        this.statusCompra = tratarRetornoGateway(statusCompra);
        this.gateway = gateway;
    }

    public StatusTransacao getStatusCompra() { return statusCompra; }

    public String getId() { return id; }

    public GatewayPagamento getGateway() { return gateway; }

    public Instant getMomentoTransacao() { return momentoTransacao; }

    public Compra getCompra() { return compra; }

    private StatusTransacao tratarRetornoGateway(String retorno){
        if (retorno.equals("1") || retorno.equals("Sucesso")){
            return StatusTransacao.SUCESSO;
        }
        return StatusTransacao.ERRO;
    }

}
