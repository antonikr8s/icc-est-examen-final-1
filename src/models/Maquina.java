package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Maquina implements Comparable<Maquina> {
    private String nombre;
    private String ip;
    private List<Integer> codigos;

    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;

        calcularSubred();
        calcularRiesgo();
    }

    private void calcularSubred() {

        String[] partes = ip.split("\\.");
        if (partes.length == 4) {
            this.subred = Integer.parseInt(partes[2]);
        } else {
            this.subred = 0;
        }
    }

    private void calcularRiesgo() {

        int sumaDiv5 = 0;
        for (int c : codigos) {
            if (c % 5 == 0) {
                sumaDiv5 += c;
            }
        }

        Set<Character> caracteresUnicos = new HashSet<>();
        for (char ch : nombre.toCharArray()) {
            if (ch != ' ') {
                caracteresUnicos.add(ch);
            }
        }
        int numUnicos = caracteresUnicos.size();

        this.riesgo = sumaDiv5 * numUnicos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        return subred;
    }

    public int getRiesgo() {
        return riesgo;
    }

    @Override
    public String toString() {
        return nombre + " - " + ip + " - Riesgo: " + riesgo + " - Subred: " + subred;
    }

    @Override
    public int compareTo(Maquina o) {
        if (this.subred != o.subred) {

            return Integer.compare(o.subred, this.subred);
        }

        return this.nombre.compareTo(o.nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Maquina))
            return false;
        Maquina m = (Maquina) o;
        return this.subred == m.subred && this.nombre.equals(m.nombre);
    }

    @Override
    public int hashCode() {
        return 31 * subred + nombre.hashCode();
    }
}
