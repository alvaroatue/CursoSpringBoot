package com.utn.springboot.billeteravirtual.entity;

import com.utn.springboot.billeteravirtual.types.EstadoUsuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 50)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_usuario")
    private EstadoUsuario estado;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate fechaRegistro;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id", unique = true)
    private DireccionEntity direccion;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<CuentaEntity> cuentas;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private List<NotificacionEntity> notificaciones;

    public UsuarioEntity() {
    }

    public UsuarioEntity(String nombre, String email, Integer edad) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            this.estado = EstadoUsuario.ACTIVO;
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public DireccionEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEntity direccion) {
        this.direccion = direccion;
    }
}
