package orange.com.br.mercadolivre.compras;

import orange.com.br.mercadolivre.compras.enumeradores.GatewayPagamento;
import orange.com.br.mercadolivre.compras.enumeradores.StatusCompra;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.transacoes.Transacao;
import orange.com.br.mercadolivre.usuarios.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    private final String id = UUID.randomUUID().toString().replace("-","");

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne
    private Usuario comprador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GatewayPagamento gateway;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCompra status;

    @OneToMany(mappedBy = "compra")
    private final Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    private Compra() {
    }

    public Compra(Produto produto, Integer quantidade, BigDecimal valorTotal, Usuario comprador, GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.comprador = comprador;
        this.gateway = gateway;
        this.status = StatusCompra.INICIADA;
    }

    public String getId() { return id; }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Produto getProduto() { return produto; }

    public Integer getQuantidade() { return quantidade; }

    public BigDecimal getValorTotal(){ return valorTotal; }

    public Usuario getComprador() { return comprador; }

    public void alterarStatusCompra(StatusCompra novoStatus){
        this.status = novoStatus;
    }

}
