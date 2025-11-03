package Moldeado

import Moldeado.Posicion

case class Estado(liebre: Posicion, sabuesos: Set[Posicion], turno: Jugador):
  def ocupadas: Set[Posicion] =
    sabuesos + liebre
