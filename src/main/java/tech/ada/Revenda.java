package tech.ada;

import java.util.Objects;

public record Revenda(
        Long id,
        String regiaoSigla,
        String estadoSigla,
        String municipio,
        String revenda,
        String cnpjDaRevenda
)  implements Comparable<Revenda> {
    public Revenda (PrecoCombustivel precoCombustivel){
        this(precoCombustivel.id(), precoCombustivel.regiaoSigla(), precoCombustivel.estadoSigla(),
                precoCombustivel.municipio(), precoCombustivel.revenda(), precoCombustivel.cnpjDaRevenda());
    }

    @Override
    public int compareTo(Revenda o) {
        if (this.revenda().equals(o.revenda()) && this.cnpjDaRevenda.equals(o.cnpjDaRevenda())) {
            return 0;
        }

        // caso não seja de tecnologia segue a regra padrão: por nome e id
        int resultado = this.revenda.compareTo(o.revenda());

        if (resultado == 0) {
            return this.cnpjDaRevenda.compareTo(o.cnpjDaRevenda());
        }

        return resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revenda revenda1 = (Revenda) o;
        return Objects.equals(revenda, revenda1.revenda) && Objects.equals(cnpjDaRevenda, revenda1.cnpjDaRevenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revenda, cnpjDaRevenda);
    }
}

