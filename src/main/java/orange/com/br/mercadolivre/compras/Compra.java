package orange.com.br.mercadolivre.compras;

import orange.com.br.mercadolivre.compras.enumeradores.GatewayPagamento;
import orange.com.br.mercadolivre.compras.enumeradores.StatusCompra;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    private Usuario comprador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GatewayPagamento gateway;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCompra status;

    @Deprecated
    private Compra() {
    }

    public Compra(Produto produto, Integer quantidade, Usuario comprador, GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
        this.status = StatusCompra.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

}
