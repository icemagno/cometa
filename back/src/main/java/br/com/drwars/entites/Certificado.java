package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_certificados")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_certificados_id_certificado_seq")
    @SequenceGenerator(name = "tb_certificados_id_certificado_seq", sequenceName = "tb_certificados_id_certificado_seq", allocationSize = 1)
    @Column(name = "id_certificado")
    private Long id;


    @ManyToOne
    @JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
    private Empresa empresa;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "equivalencia")
    private Long equivalencia;

    @ManyToOne
    @JoinColumn(name="id_empresa_certificadora",referencedColumnName = "id_empresa")
    private Empresa empresaCertificadora;

    @Column(name = "nm_certificado")
    private String numero;

}
