package Moldeado
import scala.util.Random

enum Jugador(val y: Int):
  case sabuesos extends Jugador(1)
  case liebre extends Jugador (2)
def sortearTurno(): Jugador =
  val r = Random.nextInt(2)
  if r == 0 then Jugador.sabuesos
  else        Jugador.liebre