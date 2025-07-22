package controllers;

import models.Maquina;

import java.util.*;

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> stack = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() > umbral) {
                stack.push(m);
            }
        }
        return stack;
    }

    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        return new TreeSet<>(pila);
    }

    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            int riesgo = m.getRiesgo();
            mapa.putIfAbsent(riesgo, new LinkedList<>());
            mapa.get(riesgo).add(m);
        }
        return mapa;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int maxCantidad = 0;
        int maxRiesgo = 0;
        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int riesgo = entry.getKey();
            int cantidad = entry.getValue().size();
            if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > maxRiesgo)) {
                maxCantidad = cantidad;
                maxRiesgo = riesgo;
            }
        }
        Queue<Maquina> grupo = mapa.get(maxRiesgo);
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : grupo) {
            resultado.push(m);
        }
        return resultado;
    }
}
