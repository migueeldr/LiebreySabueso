package Moldeado

import Moldeado.Jugador.liebre

def bucleJuego(tablero: TableroJuego, estado: Estado): Jugador=
  TableroClasicoLyS.pintarTablero(estado)
  if (estado.turno == liebre) then
    println("Liebre tus movimientos son:"+ MovimientoLiebre.movimientosPosibles(tablero, estado))
  else
    println("Sabueso tus movimientos son:"+ MovimientoSabueso.movimientosPosibles(tablero, estado))
  val eleccion = scala.io.StdIn.readLine().toInt 
  