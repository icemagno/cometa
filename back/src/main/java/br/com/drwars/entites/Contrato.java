package br.com.drwars.entites;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_contratos_id_contrato_seq")
    @SequenceGenerator(name = "tb_contratos_id_contrato_seq", sequenceName = "tb_contratos_id_contrato_seq", allocationSize = 1)
    @Column(name = "id_contrato")
    private Long id;

    @Column(name = "documento")
    private byte[] documento;

    @ManyToOne
    @JoinColumn(name="id_compra",referencedColumnName = "id_compra")
    private Compra compra;
}
